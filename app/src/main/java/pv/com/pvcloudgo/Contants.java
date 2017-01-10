package pv.com.pvcloudgo;


public class Contants {



    public static final String  COMPAINGAIN_ID="compaigin_id";
    public static final String  WARE="ware";

    public static final String USER_JSON="user_json";
    public static final String TOKEN="token";

    public  static final String DES_KEY="123456";

    public  static final int REQUEST_CODE=0;
    public  static final int REQUEST_CODE_PAYMENT=1;

    public static class API{


        public static final String BASE_URL="http://www.qdhdn.cn:8080/";

        public static final String CAMPAIGN_HOME=BASE_URL + "campaign/recommend";

        public static final String BANNER=BASE_URL +"banner/query";
        public static final String HOME=BASE_URL +"app/index";
        public static final String MINE=BASE_URL +"app/user/index";


        public static final String WARES_HOT=BASE_URL + "wares/hot";
        public static final String WARES_LIST=BASE_URL +"wares/list";
        public static final String findByRoot=BASE_URL +"app/productType/findByRoot";
        public static final String WARES_CAMPAIN_LIST=BASE_URL +"wares/campaign/list";
        public static final String WARES_DETAIL=BASE_URL +"wares/detail.html";

        public static final String CATEGORY_LIST=BASE_URL +"category/list";

        public static final String LOGIN=BASE_URL +"app/userLogin";
        public static final String productTypeList=BASE_URL +"app/productTypeList";
        public static final String LOGOUT=BASE_URL +"app/user/logout";
        public static final String GETCODE=BASE_URL +"app/registSendSMS";
        public static final String REG=BASE_URL +"app/userRegist";
        public static final String VERIFYPHONE=BASE_URL +"app/userRegistVerTelPhone";
        public static final String VERIFYCODE=BASE_URL +"app/userRegistVerYZM";

        public static final String FINDPSWDSENDCODE=BASE_URL +"app/forgetSendSMS";

        public static final String USER_DETAIL=BASE_URL +"user/get?id=1";

        public static final String ORDER_CREATE=BASE_URL +"/order/create";
        public static final String ORDER_COMPLEPE=BASE_URL +"/order/complete";
        public static final String ORDER_LIST=BASE_URL +"order/list";

        public static final String ADDRESS_LIST=BASE_URL +"addr/list";
        public static final String ADDRESS_CREATE=BASE_URL +"addr/create";
        public static final String ADDRESS_UPDATE=BASE_URL +"addr/update";

        public static final String FAVORITE_LIST=BASE_URL +"favorite/list";
        public static final String FAVORITE_CREATE=BASE_URL +"favorite/create";




    }
}
