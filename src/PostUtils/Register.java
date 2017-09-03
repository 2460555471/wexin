package PostUtils;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jdbc.JdbcUtils;

import net.sf.json.JSONObject;

import util.CheckUtil;

public class Register extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request,response);
	}
	
	
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException 
			{

		response.setContentType("application/json;charset=utf-8");//������������ã��������������,��ֹ������������
        request.setCharacterEncoding("utf-8");//���ý�������������ݿ����ݱ��뷽ʽ����ֹ��������Ϊ����
       
        /**
         * �ɼ�����������
         */
        String user_id=String.valueOf(new Date().getTime());
		String openid = request.getParameter("openid");
		String wechat_num = request.getParameter("wechat_num");
		String nickname = request.getParameter("nickname");
		String head_img_url = request.getParameter("head_img_url");
		/**
		 * ת��Ϊ���ı���,�Ա���д���
		 */
		String user_real_name=null;//=new String(request.getParameter("user_real_name").getBytes("gbk"));
		Boolean  sex;// =request.getParameter("sex").equals("0")?false:true;
		String user_birthday = request.getParameter("user_birthday");
		String user_idcard = request.getParameter("user_idcard");
		String user_phonenum = request.getParameter("user_phonenum");
		
		/**
		 * ��֤�룬��д�����ݿ�
		 */
		String cherifynum = request.getParameter("cherifynum");
		
		
		openid="ewegagdzgae";
		wechat_num="15010051958";
		nickname=new String("����ɶ�ط�".getBytes("gbk"));
		head_img_url="asfgeg";		
		String user="����";
		user_real_name=new String(user.getBytes("gbk"));
		sex=false;
		user_birthday="1994-12-30";
		user_idcard="210623199412301879";
		user_phonenum="15010051958";
		
		
		
		boolean flag = false;
        if(true)
        {
        	JdbcUtils db=new JdbcUtils();
        	String sql="INSERT INTO `library`.`user` ("+
        			  "`user_id`,"+
        			  "`openid`,"+
        			  "`wechat_num`,"+
        			  "`nickname`,"+
        			  "`sex`,"+
        			  "`user_phonenum`,"+
        			  "`user_real_name`,"+
        			  "`head_img_url`,"+
        			  "`user_idcard`,"+
        			  "`user_birthday`"+
        			") values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    		List<Object> params = new ArrayList<Object>();
    		params.add(user_id);
    		params.add(openid);
    		params.add(wechat_num);
    		params.add(nickname);
    		params.add(sex);
    		params.add(user_phonenum);
    		params.add(user_real_name);
    		params.add(head_img_url);
    		params.add(user_idcard);
    		params.add(user_birthday);
    		try {
    		    flag = db.updateByPreparedStatement(sql, params);
    			System.out.println(flag);
    			
    		} catch (SQLException e) {
    			e.printStackTrace();
    		}
        		
        }
        /**
         * ���������Ƿ�������
         */
        JdbcUtils db2=new JdbcUtils();
		String sql2 = "select * from user";
		
		List<Map<String, Object>> list = null;
		try {
			list = db2.findModeResult(sql2, null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		for(int i=0;i<list.size();i++)
		{
			System.out.println("     "+list.get(i).get("user_real_name")+"  "+list.get(i).get("user_id"));
		}
		
		/**
		 * ����success��ʶ��˵��ע��ɹ�
		 */
        try {
        	
             JSONObject jb = new JSONObject();
             String info=(flag==true)?"success":"fail";
             jb.put("name", info);
             System.out.println(jb.toString());
             response.getWriter().write(jb.toString());
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        
			}

}
