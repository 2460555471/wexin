package db;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import DoubanApi.Book;
import jdbc.JdbcUtils;

public class Db_search {
	
	public static List<Map<String, Object>> BookSearch(String input_content,String search_type)
	{
		List<Map<String, Object>> result=null;
		if(search_type.equals("0"))
		{
			result=Db_search.BookNameSearch(input_content);
		}
		else if(search_type.equals("1"))
		{
			result=Db_search.AuthorNameSearch(input_content);
		}
		else if(search_type.equals("2"))
		{
			result=Db_search.TabNameSearch(input_content);//标签名查找
		}
		return result;
	}
	/**
	 * 按照书名关键词查找返回列表
	 * @param content
	 * @return
	 */
	public static List<Map<String, Object>> BookNameSearch(String content)
	{
//		JdbcUtils db=new JdbcUtils();
//		String sql = "select * from user";
//		
		List<Map<String, Object>> list = null;
//		try {
//			list = db.findModeResult(sql, null);
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
		return list;
	}
	
	/**
	 * 按照作者名关键词查找，返回书籍的所有表项
	 * @param content
	 * @return
	 */
	public static List<Map<String, Object>> AuthorNameSearch(String content)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	/**
	 * 按照标签名关键词查找，返回书籍的所有表项
	 * @param content
	 * @return
	 */
	public static List<Map<String, Object>> TabNameSearch(String content)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	/**
	 * 按照ISBN查找某本书，返回某一本书书的具体信息，book类需要完善
	 */
	
	public static Book searchOneIsbn(String isbn)
	{
		Book book=new Book();
		
		return book;
	}
	
	
	/**
	 * 用户输入关键词，like查找数据库热搜表，返回几个匹配的关键词
	 * 搜索匹配，返回几条相关的热搜关键词
	 * style:0书名
	 * 1：作者名
	 * 2：标签名
	 */
	public static List<String> releteHotSearch(String content,String style)
	{
		List<String> contentlist=new ArrayList<String>();
		if(style.equals("0"))
			contentlist=reletebookname(content);
		else if(style.equals("1"))
			contentlist=releteauthorname(content);
		else if(style.equals("2"))
			contentlist=reletetags(content);		
		return contentlist;
	}
	/**
	 * 针对标签的相关查询
	 */
	private static List<String> reletetags(String content) {
		
		return null;
	}

	private static List<String> releteauthorname(String content) {
		
		return null;
	}
	
	/**
	 * 查找热搜表匹配相关性比较强的5个关键词，是书名的关键性
	 * @param content
	 * @return
	 */
	private static List<String> reletebookname(String content) {
		
		return null;
	}
	
	
	/**
	 * 根据中图法分类号返回书籍列表
	 */
	public static List<Map<String, Object>> classfyNumSearch(String content)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	
	/**
	 * 向用户个性化推荐图书列表，图书内容不超过5个
	 */
	public static List<Map<String, Object>> Recommend(String userid)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	
	/**
	 * 查询用户借书历史和当前正在借阅的情况
	 * 返回用户借书的情况
	 */
	public static List<Map<String, Object>> userBorrowHis(String userid)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	/**
	 * 查询用户当前预约情况
	 */
	public static List<Map<String, Object>> userOrder(String userid)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	/**
	 * 查询用户喜爱收藏图书表
	 */
	public static List<Map<String, Object>> userSave(String userid)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
    
}
