package pv.com.pvcloudgo.vc.adapter;

import android.content.Context;

import java.util.List;

import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.model.bean.ChargeRule;


/**
 */
public class ChargeItemAdapter extends SimpleAdapter<ChargeRule> {



    public ChargeItemAdapter(Context context, List<ChargeRule> datas) {
        super(context, R.layout.item_charge_phone, datas);
    }

    @Override
    protected void convert(BaseViewHolder viewHoder, ChargeRule item) {

        viewHoder.getTextView(R.id.item_c_price_tv).setText(String.valueOf(item.getChongzhiPrice()));
        viewHoder.getTextView(R.id.item_c_saleprice_tv).setText("售价 ￥"+item.getPrice());
        viewHoder.getTextView(R.id.item_c_v_tv).setText("V值 "+item.getV()+"V");
    }



}
