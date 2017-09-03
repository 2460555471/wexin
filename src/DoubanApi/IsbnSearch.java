package DoubanApi;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Scanner;


import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;


public class IsbnSearch {
	
	
	private  static String ISBNSearchURL = "https://api.douban.com/v2/book/isbn/ISBN";
	
	/**
	 * 解决anthor     ["张小娴"]的问题
	 */
	public static String strTrim(String str)
	{
		return str.substring(2, str.length()-2);
	}
//	public static Book GetBookInfo(String str)throws JSONException, ParseException, IOException
//	{
//		JSONObject json = JSONObject.fromObject(str);  
//		return GetBookInformation(json);
//	}
	
	/**
	 * JSONObject的解析
	 */
	public  static Book GetBookInformation(String isbn) throws ParseException, IOException, URISyntaxException
	{
		String url = ISBNSearchURL.replace("ISBN", isbn);
		JSONObject jsonObject = doGetStr(url);
		Book book=new Book();
		if(jsonObject != null)
		{
			book.setBook_ISBN(jsonObject.getString("isbn10"));
			
			/**
			 * 获取作者
			 */
			book.setBook_author(strTrim(jsonObject.getString("author")));	
			book.setBook_abstract(new String(jsonObject.getString("summary").getBytes("gbk")));	
			book.setBook_name(jsonObject.getString("title"));		
			book.setBook_pic_url(jsonObject.getString("image"));
			book.setBook_press_name(jsonObject.getString("publisher"));
			book.setBook_press_year(jsonObject.getString("pubdate"));		
			book.setBook_price(jsonObject.getString("price"));
			/**
			 * tags
			 */
			JSONArray array=jsonObject.getJSONArray("tags");
			StringBuffer s=new StringBuffer();
			for(int i=0;i<array.size();i++)
			{
				JSONObject tagjson = array.getJSONObject(i);
				s=s.append(tagjson.getString("name"));
				s=s.append("|");
			}
			book.setBook_keywords(s.toString());				
		}
		return book;
	}
	
	/**
	 * Get接口
	 */
	public static  JSONObject doGetStr(String url) throws ParseException, IOException, URISyntaxException{
		
		HttpClient client = new DefaultHttpClient();
		//HttpGet httpGet = new HttpGet(url);

//		httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64; rv:53.0) Gecko/20100101 Firefox/53.0");
//		httpGet.setHeader("Accept"," text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8");  
//		httpGet.setHeader("Accept-Language","zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");  
//		httpGet.setHeader("Host","api.douban.com");   
//		httpGet.setHeader("Accept-Encoding","gzip, deflate, br");  
//		httpGet.setHeader("Cookie", "bid=cawvya88qBc");  
//		httpGet.setHeader("Connection", "keep-alive");   
//		httpGet.setHeader("Upgrade-Insecure-Requests", "1");  
		client = WebClientDevWrapper.wrapClient(client);
		HttpGet request = new HttpGet();
		request.setURI(new URI(url));
		HttpResponse response = client.execute(request);
	
		
		//HttpResponse httpResponse = WebClientDevWrapper.wrapClient(client).execute(httpGet);
		HttpEntity entity = response.getEntity();
		JSONObject jsonObject = null;
		if(entity != null){
			String result = EntityUtils.toString(entity,"UTF-8");
			jsonObject = JSONObject.fromObject(result);
		}
		return jsonObject;
	}
	
	public static void main(String[] args) throws ParseException, IOException, URISyntaxException 
	{
//		Scanner  cin=new Scanner(System.in);
//		String str=cin.nextLine();
//		Book b1=IsbnSearch.GetBookInfo(str);
//		b1.Print();
		
		String isbn="978-7-5442-5860-9";
		Book b=IsbnSearch.GetBookInformation(isbn);
		b.Print();
	}

}

//730121569X

