package util;

import menu.Button;
import menu.ClickButton;
import menu.Menu;
import menu.ViewButton;
import net.sf.json.JSONObject;



import BasicElementMessage.AccessToken;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.List;
import java.util.Map;


import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import Usermanage.UserInfor;


/**
 * ΢�Ź�����
 */
public  class WechatUtil 
{
	public  static  String Token="";
	private  static String APPID = "wx7831b20e5c5d334d";
	private  static String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";
	private  static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//�ϴ���ý���ļ��ӿ�,�ֱ���ͼƬ����������Ƶ��������ͼ��
	private  static String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	//�����˵��Ľӿ�
	private  static  String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	//��ѯ�Զ���˵��ӿڽӿ�
	private  static String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	//ɾ���˵��ӿ�
	private  static String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	//��ȡ�û�������Ϣ�ӿ�
	private  static String GetUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	//��code��ȡ�û��Ļ�����Ϣ�Ľӿ�
	private static String infoFrocodeUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorazation_code";
	
	/**
	 * ��΢�ſͻ��˴�������code����û��ĸ��������Ϣ������UserInfor��װ�Ķ���
	 */
	public static UserInfor getUserInfoFromCode(String code) throws ParseException, IOException
	{

			String url = infoFrocodeUrl.replace("ACCESS_TOKEN", Token).replace("APPID", APPID).replace("SECRET", APPSECRET).replace("CODE", code);
			
			JSONObject jsonObject = doGetStr(url);
			String opendid=null;
			if(jsonObject != null)
			{
				opendid=jsonObject.getString("openid");
			}
			UserInfor user=GetUserInformation(opendid);
			
		return user;
	}
	/***
	 * ��ȡopenid�û�����Ϣ�Ľӿڣ�����UserInfor�û���
	 */
	public  static UserInfor GetUserInformation(String openid) throws ParseException, IOException
	{
		String url = GetUserInfo.replace("ACCESS_TOKEN", Token).replace("OPENID", openid);
		UserInfor user=new UserInfor();
		//ע��JSONObject����ʹ�÷���
		JSONObject jsonObject = doGetStr(url);
		if(jsonObject != null)
		{
			user.setOpenid(jsonObject.getString("openid"));
			user.setCity(jsonObject.getString("city"));
			user.setHeadimgurl(jsonObject.getString("headimgurl"));
			user.setNickname(jsonObject.getString("nickname"));
			user.setSex(jsonObject.getInt("sex"));
			user.setProvince(jsonObject.getString("province"));	
		}
		return user;
	}
			
     /**
	 * get����url����JSONObject������Ϣ
	 * @param url
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doGetStr(String url) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet(url);
		JSONObject jsonObject = null;
		HttpResponse httpResponse = client.execute(httpGet);
		HttpEntity entity = httpResponse.getEntity();
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}
	
	/**
	 * POST���󣬷���JSONObject����
	 * @param url
	 * @param outStr
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static JSONObject doPostStr(String url,String outStr) throws ParseException, IOException{
		DefaultHttpClient client = new DefaultHttpClient();
		HttpPost httpost = new HttpPost(url);
		httpost.setEntity(new StringEntity(outStr,"UTF-8"));

		HttpResponse response = client.execute(httpost);
		
		JSONObject jsonObject = null;
		String result = EntityUtils.toString(response.getEntity(),"UTF-8");
		
		
		
		jsonObject = JSONObject.fromObject(result);
		return jsonObject;
	}
	
	/**
	 * �ļ��ϴ�
	 * @param filePath
	 * @param accessToken
	 * @param type
	 * @return
	 */
	public static  String upload(String filePath, String accessToken,String type) throws IOException, NoSuchAlgorithmException, NoSuchProviderException, KeyManagementException 
	{
		File file = new File(filePath);
		if (!file.exists() || !file.isFile()) 
		{
			throw new IOException("�ļ�������");
		}
		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		URL urlObj = new URL(url);
		//����
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 
		//��������ͷ��Ϣ
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		//���ñ߽�
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		//��������
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//�����ͷ
		out.write(head);
		//�ļ����Ĳ���
		//���ļ������ļ��ķ�ʽ ���뵽url��
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		//��β����
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//����������ݷָ���
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//����BufferedReader����������ȡURL����Ӧ
			reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String line = null;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			if (result == null) {
				result = buffer.toString();
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				reader.close();
			}
		}
		JSONObject jsonObj = JSONObject.fromObject(result);
		System.out.println(jsonObj);
		String typeName = "media_id";
		if(!"image".equals(type)){
			typeName = type + "_media_id";
		}

		String mediaId = jsonObj.getString(typeName);
		return mediaId;
	}

	
	
	/**
	 * ��ȡaccessTokenʹ��WechatUtil��ȫ�ֱ����ı䲢����AccessToken����
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public static  AccessToken getAccessToken() throws ParseException, IOException
	{
		AccessToken token = new AccessToken();
		String url = ACCESS_TOKEN_URL.replace("APPID", APPID).replace("APPSECRET", APPSECRET);
		JSONObject jsonObject = doGetStr(url);
		if(jsonObject!=null){
			token.setToken(jsonObject.getString("access_token"));
			token.setExpiresIn(jsonObject.getInt("expires_in"));
		}
		Token=token.getToken();
		return token;
	}
	
	/**
	 * �����װ�˵���ʽ������Menu����˵���ʽ����createMenu()��������
	 * @return
	 */
	public  static Menu initMenu(){
		Menu menu = new Menu();	//Menu����button����
		ClickButton button11 = new ClickButton();
		button11.setName("click�˵�");
		button11.setType("click");
		button11.setKey("11");
		
		ViewButton button21 = new ViewButton();
		button21.setName("view�˵�");
		button21.setType("view");
		button21.setUrl("http://www.imooc.com");
		
		ClickButton button31 = new ClickButton();
		button31.setName("ɨ���¼�");
		button31.setType("scancode_push");
		button31.setKey("31");
		
		ClickButton button32 = new ClickButton();
		button32.setName("����λ��");
		button32.setType("location_select");
		button32.setKey("32");
		
		Button button = new Button();
		button.setName("�˵�");
		button.setSub_button(new Button[]{button31,button32});
		
		menu.setButton(new Button[]{button11,button21,button});
		return menu;
	}
	
	/** 
     *���� JAVA����ת����String��ʽ��JSON�ַ��� 
     * @param obj 
     * @return 
     * @throws MapperException 
     */   
    public static String Object2Json(Object obj)
    {  
        JSONObject json = JSONObject.fromObject(obj);		//��java����ת��Ϊjson����  
        String str = json.toString();					//��json����ת��Ϊ�ַ���          
       // System.out.println(str);
        return str;  
    } 
    
    /***
     * createMenu()Ϊ�����˵��ĺ��������ط�����
     * @param token
     * @param menu
     */
	public  static int createMenu() throws ParseException, IOException{
		String menu =Object2Json(initMenu());		
		int result = 0;
		String url = CREATE_MENU_URL.replace("ACCESS_TOKEN", Token);
		JSONObject jsonObject = doPostStr(url, menu);
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
	
	
	/**
	 * ��ѯ��ɾ���˵��Ľӿ�
	 * @return
	 * @throws ParseException
	 * @throws IOException
	 */
	public  static JSONObject queryMenu() throws ParseException, IOException{
		String url = QUERY_MENU_URL.replace("ACCESS_TOKEN", Token);
		JSONObject jsonObject = doGetStr(url);
		return jsonObject;
	}
	
	public  static int deleteMenu() throws ParseException, IOException{
		String url = DELETE_MENU_URL.replace("ACCESS_TOKEN", Token);
		JSONObject jsonObject = doGetStr(url);
		int result = 0;
		if(jsonObject != null){
			result = jsonObject.getInt("errcode");
		}
		return result;
	}
	
}