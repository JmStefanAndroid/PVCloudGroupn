package pv.com.pvcloudgo.http;

import com.google.gson.internal.$Gson$Types;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;


public abstract class BaseCallback<T> {


    public Type mType;

    static Type getSuperclassTypeParameter(Class<?> subclass) {
        Type superclass = subclass.getGenericSuperclass();
        if (superclass instanceof Class) {
            throw new RuntimeException("Missing type parameter.");
        }
        ParameterizedType parameterized = (ParameterizedType) superclass;
        return $Gson$Types.canonicalize(parameterized.getActualTypeArguments()[0]);
    }


    public BaseCallback() {
        mType = getSuperclassTypeParameter(getClass());
    }


    public abstract void onBeforeRequest(Request request);


    public abstract void onFailure(Request request, Exception e);


    /**
     * 请求成功时调用此方法
     *
     * @param response
     */
    public abstract void onResponse(Response response);

    /**
     * 状态码大于200，小于300 时调用此方法
     *
     * @param response
     * @param t
     * @throws IOException
     */
    public abstract void onSuccess(Response response, T t);

    /**
     * 状态码400，404，403，500等时调用此方法
     *
     * @param response
     * @param code
     * @param e
     */
    public abstract void onError(Response response, int code, Exception e);
    /**
     * 自定义服务器的异常信息回调
     *
     * @param response
     * @param code
     */
    public abstract void onServerError(Response response, int code,  String errmsg);

    /**
     * Token 验证失败。状态码401,402,403 等时调用此方法
     *
     * @param response
     * @param code
     */
    public abstract void onTokenError(Response response, int code);


}