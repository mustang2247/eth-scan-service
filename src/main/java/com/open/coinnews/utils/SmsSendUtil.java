package com.open.coinnews.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: 田雨
 * @Date: 2018/3/20 14:34
 * @Description:
 */
public class SmsSendUtil {
    public static final String charset = "utf-8";
    // 用户平台API账号(非登录账号,示例:N1234567)
    public static String account = "I0557032";
    // 用户平台API密码(非登录密码)
    public static String pswd = "rsAj0819de2c3d";
    // 接口url
    public static String smsSingleRequestServerUrl = "http://intapi.253.com/send/json";
    // 短信内容
    public static String msg = "【18区】";
    //状态报告
    public static String report= "true";

    public static void sendSmsMessage(String phone, String content) {
        Map<String,String> resultMap = new HashMap<String,String>();
        String msgContent = msg + content;
        String requestJson = "{\"account\":\""+ account + "\",\"password\":\""+ pswd + "\",\"msg\":\""+ msgContent + "\",\"mobile\":\""+ phone +"\"}";
        //System.out.println("before request string is: " + requestJson);
        sendSmsByPost(smsSingleRequestServerUrl, requestJson);
        //System.out.println("response after request result is :" + response);
    }

    public static String sendSmsByPost(String path, String postContent) {
        URL url = null;
        try {
            url = new URL(path);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setRequestMethod("POST");// 提交模式
            httpURLConnection.setConnectTimeout(10000);//连接超时 单位毫秒
            httpURLConnection.setReadTimeout(10000);//读取超时 单位毫秒
            // 发送POST请求必须设置如下两行
            httpURLConnection.setDoOutput(true);
            httpURLConnection.setDoInput(true);
            httpURLConnection.setRequestProperty("Charset", "UTF-8");
            httpURLConnection.setRequestProperty("Content-Type", "application/json");

            httpURLConnection.connect();
            OutputStream os=httpURLConnection.getOutputStream();
            os.write(postContent.getBytes("UTF-8"));
            os.flush();

            StringBuilder sb = new StringBuilder();
            int httpRspCode = httpURLConnection.getResponseCode();
            if (httpRspCode == HttpURLConnection.HTTP_OK) {
                // 开始获取数据
                BufferedReader br = new BufferedReader(
                        new InputStreamReader(httpURLConnection.getInputStream(), "utf-8"));
                String line = null;
                while ((line = br.readLine()) != null) {
                    sb.append(line);
                }
                br.close();
                return sb.toString();

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
