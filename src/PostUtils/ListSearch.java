package PostUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import db.Db_search;

public class ListSearch extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * 前端请求书籍信息列表，通过这里返回
	 * 书名
	 * 作者
	 * 关键词（标签）
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		response.setContentType("application/json;charset=utf-8");//在浏览器起作用，决定浏览器编码,防止出现中文乱码
        request.setCharacterEncoding("utf-8");//设置接收浏览器或数据库数据编码方式，防止接收中文为乱码
       
        /**
         * search_type 0:书名，1：作者 2：标签词
         */
		String search_type = request.getParameter("search_type");
		String input_content = request.getParameter("input_content");
		/**
		 * 搜索结果列表返回
		 */
		List<Map<String, Object>> result=Db_search.BookSearch(input_content,search_type);
		
		

	}

}
