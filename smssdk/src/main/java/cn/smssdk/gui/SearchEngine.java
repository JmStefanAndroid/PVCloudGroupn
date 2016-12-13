package cn.smssdk.gui;

import static com.mob.tools.utils.R.getRawRes;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.zip.GZIPInputStream;

import cn.smssdk.utils.SMSLog;
import com.mob.tools.utils.Hashon;

import android.content.Context;

public class SearchEngine {
	private static final String DB_FILE = "smssdk_pydb";
	private static HashMap<String, Object> hanzi2Pinyin;
	private boolean caseSensitive;
	private ArrayList<SearchIndex> index;

	public static void prepare(final Context context, final Runnable afterPrepare) {
		Runnable act = new Runnable() {
			public void run() {
				synchronized (DB_FILE) {
					if (hanzi2Pinyin == null || hanzi2Pinyin.size() <= 0) {
						try {
							int resId = getRawRes(context, DB_FILE);
							if (resId <= 0) {
								hanzi2Pinyin = new HashMap<String, Object>();
							} else {
								InputStream is = context.getResources().openRawResource(resId);
								GZIPInputStream gzis = new GZIPInputStream(is);
								InputStreamReader isr = new InputStreamReader(gzis);
								BufferedReader br = new BufferedReader(isr);
								String json = br.readLine();
								br.close();
								hanzi2Pinyin = new Hashon().fromJson(json);
							}
						} catch (Throwable t) {
							SMSLog.getInstance().w(t);
							hanzi2Pinyin = new HashMap<String, Object>();
						}
					}

					if (afterPrepare != null) {
						afterPrepare.run();
					}
				}
			}
		};
		if (afterPrepare != null) {
			new Thread(act).start();
		} else {
			act.run();
		}
	}

	public void setCaseSensitive(boolean caseSensitive) {
		this.caseSensitive = caseSensitive;
	}

	public void setIndex(ArrayList<String> index) {
		this.index = new ArrayList<SearchIndex>();
		for (String i : index) {
			this.index.add(new SearchIndex(i, hanzi2Pinyin));
		}
	}

	public ArrayList<String> match(String token) {
		ArrayList<String> res = new ArrayList<String>();
		if (index == null) {
			return res;
		}

		for (SearchIndex si : index) {
			if (si.match(token, caseSensitive)) {
				res.add(si.getText());
			}
		}
		return res;
	}

	private static class SearchIndex {
		private String text;
		private ArrayList<String> pinyin;
		private ArrayList<String> firstLatters;

		public SearchIndex(String text, HashMap<String, Object> hanzi2Pinyin) {
			this.text = text;
			this.pinyin = new ArrayList<String>();
			firstLatters = new ArrayList<String>();
			createPinyinList(hanzi2Pinyin);
		}

		private void createPinyinList(HashMap<String, Object> hanzi2Pinyin) {
			if (hanzi2Pinyin != null && hanzi2Pinyin.size() > 0) {
				char[] cArr = text.toCharArray();
				ArrayList<String[]> pinyin = new ArrayList<String[]>();
				for (char c : cArr) {
					String s = String.valueOf(c);
					@SuppressWarnings("unchecked")
					ArrayList<HashMap<String, Object>> yins
							= (ArrayList<HashMap<String, Object>>) hanzi2Pinyin.get(s);
					int size = yins == null ? 0 : yins.size();
					String[] py = new String[size];
					for (int i = 0; i < size; i++) {
						String yin = (String) yins.get(i).get("yin");
						if ("none".equals(yin)) {
							yin = "";
						}
						py[i] = yin;
					}
					pinyin.add(py);
				}

				HashSet<String> pyRes = new HashSet<String>();
				HashSet<String> flRes = new HashSet<String>();
				toPinyinArray("", "", pyRes, flRes, pinyin);
				this.pinyin.addAll(pyRes);
				firstLatters.addAll(flRes);
			}
		}

		private void toPinyinArray(String base, String firstLatter, HashSet<String> pyRes,
				HashSet<String> flRes, ArrayList<String[]> pys) {
			if (pys.size() > 0) {
				String[] py = pys.get(0);
				ArrayList<String[]> cpys = new ArrayList<String[]>();
				cpys.addAll(pys);
				cpys.remove(0);
				for (String y : py) {
					if (y.length() > 0) {
						toPinyinArray(base + y, firstLatter + y.charAt(0), pyRes, flRes, cpys);
					} else {
						toPinyinArray(base, firstLatter, pyRes, flRes, cpys);
					}
				}
			} else {
				pyRes.add(base);
				flRes.add(firstLatter);
			}
		}

		public String getText() {
			return text;
		}

		private boolean match(String token, boolean caseSensitive) {
			if (token == null || token.trim().length() <= 0) {
				return true;
			}

			String keyToSearch = token;
			if (!caseSensitive) {
				keyToSearch = token.toLowerCase();
			}

			if (text != null && text.toLowerCase().contains(keyToSearch)) {
				return true;
			}

			for (String py : pinyin) {
				if (py.contains(keyToSearch)) {
					return true;
				}
			}

			for (String fl : firstLatters) {
				if (fl.contains(keyToSearch)) {
					return true;
				}
			}

			return false;
		}

		public String toString() {
			HashMap<String, Object> map = new HashMap<String, Object>();
			map.put("text", text);
			map.put("pinyin", pinyin);
			map.put("firstLatters", firstLatters);
			return map.toString();
		}

	}

}
