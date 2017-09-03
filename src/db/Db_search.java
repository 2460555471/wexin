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
			result=Db_search.TabNameSearch(input_content);//��ǩ������
		}
		return result;
	}
	/**
	 * ���������ؼ��ʲ��ҷ����б�
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
	 * �����������ؼ��ʲ��ң������鼮�����б���
	 * @param content
	 * @return
	 */
	public static List<Map<String, Object>> AuthorNameSearch(String content)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	/**
	 * ���ձ�ǩ���ؼ��ʲ��ң������鼮�����б���
	 * @param content
	 * @return
	 */
	public static List<Map<String, Object>> TabNameSearch(String content)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	/**
	 * ����ISBN����ĳ���飬����ĳһ������ľ�����Ϣ��book����Ҫ����
	 */
	
	public static Book searchOneIsbn(String isbn)
	{
		Book book=new Book();
		
		return book;
	}
	
	
	/**
	 * �û�����ؼ��ʣ�like�������ݿ����ѱ����ؼ���ƥ��Ĺؼ���
	 * ����ƥ�䣬���ؼ�����ص����ѹؼ���
	 * style:0����
	 * 1��������
	 * 2����ǩ��
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
	 * ��Ա�ǩ����ز�ѯ
	 */
	private static List<String> reletetags(String content) {
		
		return null;
	}

	private static List<String> releteauthorname(String content) {
		
		return null;
	}
	
	/**
	 * �������ѱ�ƥ������ԱȽ�ǿ��5���ؼ��ʣ��������Ĺؼ���
	 * @param content
	 * @return
	 */
	private static List<String> reletebookname(String content) {
		
		return null;
	}
	
	
	/**
	 * ������ͼ������ŷ����鼮�б�
	 */
	public static List<Map<String, Object>> classfyNumSearch(String content)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	
	/**
	 * ���û����Ի��Ƽ�ͼ���б�ͼ�����ݲ�����5��
	 */
	public static List<Map<String, Object>> Recommend(String userid)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	
	/**
	 * ��ѯ�û�������ʷ�͵�ǰ���ڽ��ĵ����
	 * �����û���������
	 */
	public static List<Map<String, Object>> userBorrowHis(String userid)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	/**
	 * ��ѯ�û���ǰԤԼ���
	 */
	public static List<Map<String, Object>> userOrder(String userid)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
	/**
	 * ��ѯ�û�ϲ���ղ�ͼ���
	 */
	public static List<Map<String, Object>> userSave(String userid)
	{
		List<Map<String, Object>> list = null;
		return list;
	}
	
    
}
