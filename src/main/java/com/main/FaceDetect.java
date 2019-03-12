package com.main;

import java.util.*;

import com.util.AuthService;
import com.util.Base64Util;
import com.util.FileUtil;
import com.util.GsonUtils;
import com.util.HttpUtil;



/**
 * ������������Է���
 */
public class FaceDetect {

    /**
     * ��Ҫ��ʾ���������蹤����
     * FileUtil,Base64Util,HttpUtil,GsonUtils请从
     * https://ai.baidu.com/file/658A35ABAB2D404FBF903F64D47C1F72
     * https://ai.baidu.com/file/C8D81F3301E24D2892968F09AE1AD6E2
     * https://ai.baidu.com/file/544D677F5D4E4F17B4122FBD60DB82B3
     * https://ai.baidu.com/file/470B3ACCA3FE43788B5A963BF0B625F3
     * ����
     */
    public static String detect() {
    	// ����url
        String url = "https://aip.baidubce.com/rest/2.0/face/v3/detect";
        try {
            Map<String, Object> map = new HashMap<>();
            byte[] bytes = FileUtil.readFileByBytes("C:/Users/Li/Desktop/2.png");
            String photo = Base64Util.encode(bytes);
            map.put("image", photo);
            map.put("face_field", "faceshape,facetype");
            map.put("image_type", "BASE64");

            String param = GsonUtils.toJson(map);
            //6a527297a9e45a1da228c223429191ac

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
        FaceDetect.detect();
    }
}