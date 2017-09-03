
package DoubanApi;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.util.Scanner;

import org.apache.http.ParseException;

import net.sf.json.JSONArray;
import net.sf.json.JSONException;
import net.sf.json.JSONObject;

public class IsbnSearchMan {
	

	public static String strTrim(String str)
	{
		return str.substring(2, str.length()-2);
	}
	public static Book GetBookInfo(String str) throws UnsupportedEncodingException
	{
		JSONObject json = JSONObject.fromObject(str);  
		return GetBookInformation(json);
	}
	
	public  static Book GetBookInformation(JSONObject jsonObject) throws UnsupportedEncodingException 
	{
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
	
	public static void main(String[] args) throws ParseException, IOException, URISyntaxException 
	{
		Scanner  cin=new Scanner(System.in);
		String str=cin.nextLine();
		/**
		 * 送来json的字符串，然后返回对应的结果
		 */
		Book b1=IsbnSearchMan.GetBookInfo(str);
		b1.Print();
		
	}

}



