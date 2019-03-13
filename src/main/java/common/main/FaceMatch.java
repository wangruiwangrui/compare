package common.main;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.baidu.aip.face.MatchRequest;

import common.pojo.Constants;
import common.util.Base64Util;
import common.util.FileUtil;

/**
 * 
 * @author 
 * SDK文档
 *
 */

public class FaceMatch {

	public static final String APP_ID = Constants.APPID;
	public static final String API_KEY = Constants.APIKEY;
	public static final String SECRET_KEY = Constants.SECRETKEY;
	
	
	public static List sample() throws IOException {
	    String image1 = "C:/Users/Li/Desktop/1.jpg";
	    String image2 = "C:/Users/Li/Desktop/2.png";
	    
	    byte[] bytes1 = FileUtil.readFileByBytes(image1);
	    String photo1 = Base64Util.encode(bytes1);
	    
	    byte[] bytes2 = FileUtil.readFileByBytes(image2);
	    String photo2 = Base64Util.encode(bytes2);
	    
	    
	    
	    // image1/image2也可以为url或facetoken, 相应的imageType参数需要与之对应。
	    MatchRequest req1 = new MatchRequest(photo1, "BASE64");
	    MatchRequest req2 = new MatchRequest(photo2, "BASE64");
	    ArrayList<MatchRequest> requests = new ArrayList<MatchRequest>();
	    requests.add(req1);
	    requests.add(req2);

	    return requests;
	}

	
	
	public static void main(String[] args) throws IOException {
		
		String url = "https://aip.baidubce.com/rest/2.0/face/v3/match";
		
		AipFace client = new AipFace(APP_ID,API_KEY,SECRET_KEY);
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(6000);
		
//		client.setHttpProxy("proxy_host", proxy_port);
		
        JSONObject res = client.match(sample());
        System.out.println(res.toString(2));
	
	}
}
