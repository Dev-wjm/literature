package com.literature.common;

import com.alibaba.fastjson.JSONObject;
import com.sun.deploy.net.HttpResponse;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.springframework.validation.BindingResult;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class JsonApi {
    /**
     * 状态
     */
    private int status;
    /**
     * 错误信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    public JsonApi() {
        status = 0;
        msg = "请求成功";
    }

    public JsonApi(int status) {
        this.status = status;
    }

    public JsonApi(String msg) {
        this.status = 1;
        this.msg = msg;
    }

    public JsonApi(int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    //通用错误
//    public JsonApi(ErrorMng.ErrorCode code) {
//        this.status = code.getErrorID();
//        this.msg = code.getErrorMsg();
//    }


//    public JsonApi(BaseException be) {
//        ErrorMng.ErrorCode code = be.getErrorCode();
//        this.status = code.getErrorID();
//        this.msg = code.getErrorMsg();
//    }

//    public JsonApi(Exception e) {
//        this.status = 1;
//        this.msg = ExceptionUtils.getStackMsg(e);
//
//    }

//    public JsonApi(BindingResult bindingResult) {
//        this.status = 1;
//        this.data = ErrorUtils.getBindingResultErrorMessage(bindingResult);
//    }

    public JsonApi(Object data) {
        this.status = 0;
        this.data = data;
    }

    public JsonApi(Object data, int status) {
        this.status = status;
        this.data = data;
    }

//    public JsonApi(Object data, ErrorMng.ErrorCode code) {
//        this.status = code.getErrorID();
//        this.data = data;
//    }

    public static String loadContent(String url) throws IOException {
        return loadContent(url, "utf-8");
    }

    public static String loadContent(String url, String charset) throws IOException {
        StringBuilder reader = new StringBuilder();
        URL urlObject = new URL(url);
        URLConnection uc = urlObject.openConnection();
        uc.setConnectTimeout(10000);//设置超时时间
        uc.setReadTimeout(10000);
        BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), charset));
        String inputLine = null;
        while ((inputLine = in.readLine()) != null) {
            reader.append(inputLine);
        }
        in.close();
        return reader.toString();
    }

    //通过Get方法获取Json
    public static JSONObject getJson(String url) throws IOException {
        return JSONObject.parseObject(loadContent(url));
    }

    //通过Post方法获取Json
//    public static JSONObject postJson(String url, String postData) {
//        DefaultHttpClient client = new DefaultHttpClient();
//        HttpPost httpPost = new HttpPost(url);
//        JSONObject jsonObject = null;
//        try {
//            httpPost.setEntity(new StringEntity(postData, "UTF-8"));
//            HttpResponse response = client.execute(httpPost);
//            String result = EntityUtils.toString(response.getEntity(), "UTF-8");
//            jsonObject = jsonObject.parseObject(result);
//        } catch (ClientProtocolException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        return jsonObject;
//    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public JsonApi(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }
}
