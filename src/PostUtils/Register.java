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

		response.setContentType("application/json;charset=utf-8");//在浏览器起作用，决定浏览器编码,防止出现中文乱码
        request.setCharacterEncoding("utf-8");//设置接收浏览器或数据库数据编码方式，防止接收中文为乱码
       
        /**
         * 采集到各项数据
         */
        String user_id=String.valueOf(new Date().getTime());
		String openid = request.getParameter("openid");
		String wechat_num = request.getParameter("wechat_num");
		String nickname = request.getParameter("nickname");
		String head_img_url = request.getParameter("head_img_url");
		/**
		 * 转换为中文编码,性别进行处理
		 */
		String user_real_name=null;//=new String(request.getParameter("user_real_name").getBytes("gbk"));
		Boolean  sex;// =request.getParameter("sex").equals("0")?false:true;
		String user_birthday = request.getParameter("user_birthday");
		String user_idcard = request.getParameter("user_idcard");
		String user_phonenum = request.getParameter("user_phonenum");
		
		/**
		 * 验证码，不写入数据库
		 */
		String cherifynum = request.getParameter("cherifynum");
		
		
		openid="ewegagdzgae";
		wechat_num="15010051958";
		nickname=new String("侯沂啥地方".getBytes("gbk"));
		head_img_url="asfgeg";		
		String user="侯沂";
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
         * 检验数据是否插入调试
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
		 * 返回success标识符说明注册成功
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
