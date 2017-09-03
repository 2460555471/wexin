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

/**
 * 中图法分类的检索
 */
public class ChineseClassfySearch extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		response.setContentType("application/json;charset=utf-8");
        request.setCharacterEncoding("utf-8");
       
		String ChineseClassfyNum = request.getParameter("ChineseClassfyNum");
		/**
		 * 搜索结果列表返回
		 */
		List<Map<String, Object>> result=Db_search.classfyNumSearch(ChineseClassfyNum);
		
		
		
	}
}
