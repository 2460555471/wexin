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
 * �ӿ�����ʹ�ã������ò���
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
			out.print(echostr);//�ɹ�ƥ��Ļ�����echostr��ֵ��΢�Ź���ƽ̨
		}
	}
	
	
	/**
	 * ��Ϣ�Ľ�������Ӧ
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
//			if(MessageUtil.MESSAGE_TEXT.equals(msgType))//�����ı���Ϣ
//			{
//				if("?".equals(content)||"��".equals(content))
//				{
////					//���ýӿ�����û�����Ϣ
////					UserInfor user=WechatUtil .GetUserInformation(WechatUtil.Token,fromUserName);
////					String str=WechatUtil.Object2Json(user);
////					message = MessageUtil.initText(toUserName, fromUserName,str);
//					message="���������Ϣ";
//				}
//			}//�����ı���Ϣend		
//			else if(MessageUtil.MESSAGE_EVNET.equals(msgType))//�¼���Ϣ�Ĵ���
//			{
//				String eventType = map.get("Event");
//				
//				if(MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType))//������Ϣ�¼�
//				{
//					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());//��ӭ��Ϣ����
//					//���ݿ���Ӹ��û������û�����ע�ᣬ��isΪ��
//				}
//				
//				else if(MessageUtil.MESSAGE_UNSUBSCRIBE.equals(eventType))
//				{
//					//���ݿ�ɾ�����û��������û���ע�ᣬ��isΪ��
//				}
//				
//				else if(MessageUtil.MESSAGE_CLICK.equals(eventType))//CLICK�¼�����
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
//				}//CLICK�¼�����end
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
//			}//�¼���Ϣ�Ĵ���end
//			

			out.print(message);//��ͻ������xml��ʽ�ķ�������
		} catch (DocumentException e) {
			e.printStackTrace();
		}finally{
			out.close();
		}
	}
}
