package com.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.utils.AuthService;
import com.utils.Base64Util;
import com.utils.FileUtil;
import com.utils.GsonUtils;
import com.utils.HttpUtil;

import java.util.*;

/**
* api�ĵ�
*/
public class FaceMatch {

    /**
    * ��Ҫ��ʾ���������蹤����
    * FileUtil,Base64Util,HttpUtil,GsonUtils���
    * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
    * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
    * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
    * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
    * ����
    */
    public static String match() {
        // ����url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
        try {

            byte[] bytes1 = FileUtil.readFileByBytes("C:/Users/Li/Desktop/1.jpg");
            byte[] bytes2 = FileUtil.readFileByBytes("C:/Users/Li/Desktop/2.png");
            String image1 = Base64Util.encode(bytes1);
            String image2 = Base64Util.encode(bytes2);

            List<Map<String, Object>> images = new ArrayList<>();

            Map<String, Object> map1 = new HashMap<>();
            map1.put("image", image1);
            map1.put("image_type", "BASE64");
//            map1.put("face_type", "LIVE");
//            map1.put("quality_control", "LOW");
//            map1.put("liveness_control", "NORMAL");

            Map<String, Object> map2 = new HashMap<>();
            map2.put("image", image2);
            map2.put("image_type", "BASE64");
//            map2.put("face_type", "LIVE");
//            map2.put("quality_control", "LOW");
//            map2.put("liveness_control", "NORMAL");

            images.add(map1);
            images.add(map2);

            String param = GsonUtils.toJson(images);

            // ע�������Ϊ�˼򻯱���ÿһ������ȥ��ȡaccess_token�����ϻ���access_token�й���ʱ�䣬 �ͻ��˿����л��棬���ں����»�ȡ��
            String accessToken = AuthService.getAuth();

            String result = HttpUtil.post(url, accessToken, "application/json", param);
            System.out.println(result);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        FaceMatch.match();
    }
}
