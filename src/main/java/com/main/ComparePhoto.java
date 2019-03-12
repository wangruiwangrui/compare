package com.main;

import com.alibaba.fastjson.JSONException;
import com.baidu.aip.face.AipFace;

import com.baidu.aip.face.MatchRequest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.util.Base64Util;
import com.util.FileUtil;



/**
 *
 * @author
 * 人脸对比
 */
public class ComparePhoto {

    public static List sample() throws JSONException, IOException {
        byte[] bytes = FileUtil.readFileByBytes("C:/Users/Li/Desktop/1.jpg");
        String photo1 = Base64Util.encode(bytes);
        byte[] bytes2 = FileUtil.readFileByBytes("C:/Users/Li/Desktop/2.png");
        String photo2 = Base64Util.encode(bytes2);
        String image1 = photo1;
        String image2 = photo2;

        // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
        MatchRequest req1 = new MatchRequest(image1, "BASE64");
        MatchRequest req2 = new MatchRequest(image2, "BASE64");
        ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
        requests.add(req1);
        requests.add(req2);
        return requests;
    }
  //设置APPID/AK/SK
    public static final String APP_ID = "15639373";
    public static final String API_KEY = "78cfIUtvRmsC407YpXz0uzAa";
    public static final String SECRET_KEY = "376Nxmf8GYGqrk3rVcpRHGc6xS9j8ZiY";

    public static void main(String[] args) throws JSONException, IOException {

    	// 初始化一个AipFace
        AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

     // 可选：设置代理服务器地址, http和socket二选一，或者均不设置
//      client.setHttpProxy("proxy_host", proxy_port);  // 设置http代理
//      client.setSocketProxy("proxy_host", proxy_port);  // 设置socket代理

      // 可选：设置log4j日志输出格式，若不设置，则使用默认配置
      // 也可以直接通过jvm启动参数设置此环境变量
//      System.setProperty("aip.log4j.conf", "path/to/your/log4j.properties");

      // 调用接口
        JSONObject res = client.match(sample());
        System.out.println(res.toString(2));

    }
}
