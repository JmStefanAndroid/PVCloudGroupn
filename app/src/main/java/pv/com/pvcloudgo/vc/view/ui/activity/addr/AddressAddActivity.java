package pv.com.pvcloudgo.vc.view.ui.activity.addr;

import android.content.res.AssetManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.squareup.okhttp.Response;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import pv.com.pvcloudgo.R;
import pv.com.pvcloudgo.app.App;
import pv.com.pvcloudgo.model.city.XmlParserHandler;
import pv.com.pvcloudgo.model.city.model.CityModel;
import pv.com.pvcloudgo.model.city.model.DistrictModel;
import pv.com.pvcloudgo.model.city.model.ProvinceModel;
import pv.com.pvcloudgo.http.SpotsCallBack;
import pv.com.pvcloudgo.model.base.BaseRespMsg;
import pv.com.pvcloudgo.vc.base.BaseActivity;
import pv.com.pvcloudgo.vc.widget.ClearEditText;
import pv.com.pvcloudgo.vc.widget.PVToolBar;
import pv.com.pvcloudgo.utils.Contants;


public class AddressAddActivity extends BaseActivity {


    private  OptionsPickerView  mCityPikerView; //https://github.com/saiwu-bigkoo/Android-PickerView


    @Bind(R.id.txt_address)
     TextView mTxtAddress;

    @Bind(R.id.edittxt_consignee)
     ClearEditText mEditConsignee;

    @Bind(R.id.edittxt_phone)
     ClearEditText mEditPhone;

    @Bind(R.id.edittxt_add)
     ClearEditText mEditAddr;

    @Bind(R.id.toolbar)
     PVToolBar mToolBar;


    private List<ProvinceModel> mProvinces;
    private ArrayList<ArrayList<String>> mCities = new ArrayList<ArrayList<String>>();
    private ArrayList<ArrayList<ArrayList<String>>> mDistricts = new ArrayList<ArrayList<ArrayList<String>>>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address_add);
        ButterKnife.bind(this);


        initToolbar();
        init();
    }

    private void initToolbar(){

        mToolBar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        mToolBar.setRightButtonOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                createAddress();
            }
        });

    }
    private void init() {

        initProvinceDatas();

        mCityPikerView = new OptionsPickerView(this);

        mCityPikerView.setPicker((ArrayList) mProvinces,mCities,mDistricts,true);
        mCityPikerView.setTitle("选择城市");
        mCityPikerView.setCyclic(false,false,false);
        mCityPikerView.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String addresss = mProvinces.get(options1).getName() +"  "
                        + mCities.get(options1).get(option2)+"  "
                        + mDistricts.get(options1).get(option2).get(options3);
                mTxtAddress.setText(addresss);

            }
        });





    }



    protected void initProvinceDatas()
    {

        AssetManager asset = getAssets();
        try {
            InputStream input = asset.open("province_data.xml");
            // 创建一个解析xml的工厂对象
            SAXParserFactory spf = SAXParserFactory.newInstance();
            // 解析xml
            SAXParser parser = spf.newSAXParser();
            XmlParserHandler handler = new XmlParserHandler();
            parser.parse(input, handler);
            input.close();
            // 获取解析出来的数据
            mProvinces = handler.getDataList();

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {

        }

        if(mProvinces !=null){

            for (ProvinceModel p :mProvinces){

               List<CityModel> cities =  p.getCityList();
               ArrayList<String> cityStrs = new ArrayList<>(cities.size()); //城市List


               for (CityModel c :cities){

                   cityStrs.add(c.getName()); // 把城市名称放入 cityStrs


                   ArrayList<ArrayList<String>> dts = new ArrayList<>(); // 地区 List

                   List<DistrictModel> districts = c.getDistrictList();
                   ArrayList<String> districtStrs = new ArrayList<>(districts.size());

                   for (DistrictModel d : districts){
                       districtStrs.add(d.getName()); // 把城市名称放入 districtStrs
                   }
                   dts.add(districtStrs);


                  mDistricts.add(dts);
               }

               mCities.add(cityStrs); // 组装城市数据

            }
        }



    }



    @OnClick(R.id.ll_city_picker)
    public void showCityPickerView(View view){
        mCityPikerView.show();
    }


    public void createAddress(){


        String consignee = mEditConsignee.getText().toString();
        String phone = mEditPhone.getText().toString();
        String address = mTxtAddress.getText().toString() + mEditAddr.getText().toString();


        Map<String,Object> params = new HashMap<>(1);
        params.put("user_id", App.getInstance().getUser().getId());
        params.put("consignee",consignee);
        params.put("phone",phone);
        params.put("addr",address);
        params.put("zip_code","000000");

        mHttpHelper.post(Contants.API.ADDRESS_CREATE, params, new SpotsCallBack<BaseRespMsg>(this) {


            @Override
            public void onSuccess(Response response, BaseRespMsg baseRespMsg) {
                if(baseRespMsg.getStatus() == BaseRespMsg.STATUS_SUCCESS){
                    setResult(RESULT_OK);
                    finish();

                }
            }

            @Override
            public void onError(Response response, int code, Exception e) {

            }

            @Override
            public void onServerError(Response response, int code, String errmsg) {

            }
        });

    }





}
