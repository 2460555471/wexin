package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import Usermanage.UserInfor;
import util.CheckUtil;
import util.MessageUtil;
import util.WechatUtil;

public class WechatServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
/**
 * 接口适配使用，后面用不上
 */
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		String signature = req.getParameter("signature");
		String timestamp = req.getParameter("timestamp");
		String nonce = req.getParameter("nonce");
		String echostr = req.getParameter("echostr");
		PrintWriter out = resp.getWriter();
		if(CheckUtil.checkSignature(signature, timestamp, nonce)){
			out.print(echostr);//成功匹配的话返回echostr的值给微信公众平台
		}
	}
	
	
	/**
	 * 消息的接收与响应
	 */
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)throws ServletException, IOException 
	{
		req.setCharacterEncoding("UTF-8");
		
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = resp.getWriter();
		try
		{
			Map<String, String> map = MessageUtil.xmlToMap(req);
			String fromUserName = map.get("FromUserName");
			String toUserName = map.get("ToUserName");
			String msgType = map.get("MsgType");
			String content = map.get("Content");
			
			String message = null;
//			if(MessageUtil.MESSAGE_TEXT.equals(msgType))//接受文本消息
//			{
//				if("?".equals(content)||"？".equals(content))
//				{
////					//调用接口输出用户的信息
////					UserInfor user=WechatUtil .GetUserInformation(WechatUtil.Token,fromUserName);
////					String str=WechatUtil.Object2Json(user);
////					message = MessageUtil.initText(toUserName, fromUserName,str);
//					message="输出帮助信息";
//				}
//			}//接受文本消息end		
//			else if(MessageUtil.MESSAGE_EVNET.equals(msgType))//事件消息的处理
//			{
//				String eventType = map.get("Event");
//				
//				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType))//订阅消息事件
//				{
//					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());//欢迎信息界面
//					//数据库添加该用户，该用户若已注册，其is为真
//				}
//				
//				else if(MessageUtil.MESSAGE_UNSUBSCRIBE.equals(eventType))
//				{
//					//数据库删除该用户，若该用户已注册，其is为假
//				}
//				
//				else if(MessageUtil.MESSAGE_CLICK.equals(eventType))//CLICK事件处理
//				{
//					String EventKey = map.get("EventKey");
//					if(EventKey.equals(""))
//					{
//						
//					}
//					else if(EventKey.equals(""))
//					{
//						
//					}
//				}//CLICK事件处理end
//				
//				else if(MessageUtil.MESSAGE_VIEW.equals(eventType))
//				{
//					
//				}
//				
//				else if(MessageUtil.MESSAGE_SCANCODE.equals(eventType))
//				{
//					
//
//				}
//			}//事件消息的处理end
//			

			out.print(message);//向客户端输出xml格式的返回数据
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
