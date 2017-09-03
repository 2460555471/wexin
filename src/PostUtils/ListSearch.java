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
	 * ǰ�������鼮��Ϣ�б�ͨ�����ﷵ��
	 * ����
	 * ����
	 * �ؼ��ʣ���ǩ��
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)throws ServletException, IOException 
	{
		response.setContentType("application/json;charset=utf-8");//������������ã��������������,��ֹ������������
        request.setCharacterEncoding("utf-8");//���ý�������������ݿ����ݱ��뷽ʽ����ֹ��������Ϊ����
       
        /**
         * search_type 0:������1������ 2����ǩ��
         */
		String search_type = request.getParameter("search_type");
		String input_content = request.getParameter("input_content");
		/**
		 * ��������б���
		 */
		List<Map<String, Object>> result=Db_search.BookSearch(input_content,search_type);
		
		

	}

}
