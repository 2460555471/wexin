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
 * 微信工具类
 */
public  class WechatUtil 
{
	public  static  String Token="";
	private  static String APPID = "wx7831b20e5c5d334d";
	private  static String APPSECRET = "d4624c36b6795d1d99dcf0547af5443d";
	private  static String ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=APPID&secret=APPSECRET";
	//上传多媒体文件接口,分别有图片，语音，视频，和缩略图。
	private  static String UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE";
	//建立菜单的接口
	private  static  String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN";
	//查询自定义菜单接口接口
	private  static String QUERY_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get?access_token=ACCESS_TOKEN";
	//删除菜单接口
	private  static String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete?access_token=ACCESS_TOKEN";
	//获取用户基本信息接口
	private  static String GetUserInfo = "https://api.weixin.qq.com/cgi-bin/user/info?access_token=ACCESS_TOKEN&openid=OPENID&lang=zh_CN";
	//从code获取用户的基本信息的接口
	private static String infoFrocodeUrl="https://api.weixin.qq.com/sns/oauth2/access_token?appid=APPID&secret=SECRET&code=CODE&grant_type=authorazation_code";
	
	/**
	 * 从微信客户端传过来的code获得用户的各项基本信息，返回UserInfor封装的对象
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
	 * 获取openid用户的信息的接口，返回UserInfor用户类
	 */
	public  static UserInfor GetUserInformation(String openid) throws ParseException, IOException
	{
		String url = GetUserInfo.replace("ACCESS_TOKEN", Token).replace("OPENID", openid);
		UserInfor user=new UserInfor();
		//注意JSONObject对象使用方法
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
	 * get请求url返回JSONObject对象消息
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
	 * POST请求，返回JSONObject对象
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
	 * 文件上传
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
			throw new IOException("文件不存在");
		}
		String url = UPLOAD_URL.replace("ACCESS_TOKEN", accessToken).replace("TYPE",type);
		URL urlObj = new URL(url);
		//连接
		HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
		con.setRequestMethod("POST"); 
		con.setDoInput(true);
		con.setDoOutput(true);
		con.setUseCaches(false); 
		//设置请求头信息
		con.setRequestProperty("Connection", "Keep-Alive");
		con.setRequestProperty("Charset", "UTF-8");
		//设置边界
		String BOUNDARY = "----------" + System.currentTimeMillis();
		con.setRequestProperty("Content-Type", "multipart/form-data; boundary=" + BOUNDARY);
		StringBuilder sb = new StringBuilder();
		sb.append("--");
		sb.append(BOUNDARY);
		sb.append("\r\n");
		sb.append("Content-Disposition: form-data;name=\"file\";filename=\"" + file.getName() + "\"\r\n");
		sb.append("Content-Type:application/octet-stream\r\n\r\n");
		byte[] head = sb.toString().getBytes("utf-8");
		//获得输出流
		OutputStream out = new DataOutputStream(con.getOutputStream());
		//输出表头
		out.write(head);
		//文件正文部分
		//把文件已流文件的方式 推入到url中
		DataInputStream in = new DataInputStream(new FileInputStream(file));
		int bytes = 0;
		byte[] bufferOut = new byte[1024];
		while ((bytes = in.read(bufferOut)) != -1) {
			out.write(bufferOut, 0, bytes);
		}
		in.close();
		//结尾部分
		byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");//定义最后数据分隔线
		out.write(foot);
		out.flush();
		out.close();
		StringBuffer buffer = new StringBuffer();
		BufferedReader reader = null;
		String result = null;
		try {
			//定义BufferedReader输入流来读取URL的响应
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
	 * 获取accessToken使得WechatUtil的全局变量改变并返回AccessToken对象。
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
	 * 设计组装菜单样式，返回Menu整体菜单样式，被createMenu()函数调用
	 * @return
	 */
	public  static Menu initMenu(){
		Menu menu = new Menu();	//Menu就是button数组
		ClickButton button11 = new ClickButton();
		button11.setName("click菜单");
		button11.setType("click");
		button11.setKey("11");
		
		ViewButton button21 = new ViewButton();
		button21.setName("view菜单");
		button21.setType("view");
		button21.setUrl("http://www.imooc.com");
		
		ClickButton button31 = new ClickButton();
		button31.setName("扫码事件");
		button31.setType("scancode_push");
		button31.setKey("31");
		
		ClickButton button32 = new ClickButton();
		button32.setName("地理位置");
		button32.setType("location_select");
		button32.setKey("32");
		
		Button button = new Button();
		button.setName("菜单");
		button.setSub_button(new Button[]{button31,button32});
		
		menu.setButton(new Button[]{button11,button21,button});
		return menu;
	}
	
	/** 
     *输入 JAVA对象转换成String形式的JSON字符串 
     * @param obj 
     * @return 
     * @throws MapperException 
     */   
    public static String Object2Json(Object obj)
    {  
        JSONObject json = JSONObject.fromObject(obj);		//将java对象转换为json对象  
        String str = json.toString();					//将json对象转换为字符串          
       // System.out.println(str);
        return str;  
    } 
    
    /***
     * createMenu()为建立菜单的函数，返回返回码
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
	 * 查询和删除菜单的接口
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
