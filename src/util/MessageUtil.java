package util;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import BasicElementMessage.Image;
import BasicElementMessage.ImageMessage;
import BasicElementMessage.News;
import BasicElementMessage.NewsMessage;
import BasicElementMessage.TextMessage;

import com.thoughtworks.xstream.XStream;

/**
 * �ı���Ϣ�ķ�װ�࣬ͼ����Ϣ�ķ�װ��ͼƬ��Ϣ�ķ�װ�� xml��ʽ��request����תΪmap����
 * @author Stephen
 *
 */
public class MessageUtil {	
	
	public static final String MESSAGE_TEXT = "text";
	public static final String MESSAGE_NEWS = "news";
	public static final String MESSAGE_IMAGE = "image";
	public static final String MESSAGE_VOICE = "voice";
	public static final String MESSAGE_MUSIC = "music";
	public static final String MESSAGE_VIDEO = "video";
	public static final String MESSAGE_LINK = "link";
	public static final String MESSAGE_LOCATION = "LOCATION";
	public static final String MESSAGE_EVNET = "event";
	public static final String MESSAGE_SUBSCRIBE = "subscribe";
	public static final String MESSAGE_UNSUBSCRIBE = "unsubscribe";
	public static final String MESSAGE_CLICK = "CLICK";
	public static final String MESSAGE_VIEW = "VIEW";
	public static final String MESSAGE_SCANCODE= "scancode_push";
	
	/**
	 * xml��ʽ��request����תΪmap����
	 * @param request
	 * @return
	 * @throws IOException
	 * @throws DocumentException
	 */
	public static Map<String, String> xmlToMap(HttpServletRequest request) throws IOException, DocumentException
	{
		Map<String, String> map = new HashMap<String, String>();
		SAXReader reader = new SAXReader();	
		InputStream ins = request.getInputStream();
		Document doc = reader.read(ins);	
		Element root = doc.getRootElement();
		List<Element> list =root.elements();
		for(Element e : list){
			map.put(e.getName(), e.getText());
		}
		ins.close();
		return map;
	}
	
	/**
	 * ���뷢�ͷ������շ��ͷ������ݷ����ı���Ϣxml����
	 * @param toUserName
	 * @param fromUserName
	 * @param content
	 * @return
	 */
	public static String initText(String toUserName,String fromUserName,String content){
		TextMessage text = new TextMessage();
		text.setFromUserName(toUserName);
		text.setToUserName(fromUserName);
		text.setMsgType(MessageUtil.MESSAGE_TEXT);
		text.setCreateTime(new Date().getTime());
		text.setContent(content);
		return textMessageToXml(text);
	}
	
/**
 * ���ں����������Զ���Ļ�ӭ��Ϣ��������Ϣ��String���ַ������ɱ�initText����Ϊcontent
 * @return
 */
	public static String menuText()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("��ӭ����עʯ�ʹ�ѧ�Ļ��ﻥ��ƽ̨���밴�ղ˵���ʾ���в�����\n\n");
		sb.append("1������\n");
		sb.append("2������\n");
		sb.append("3������\n\n");
		sb.append("�ظ��������˲˵���");
		return sb.toString();
	}

	/**
	 * ͼ����Ϣ����װ  newsList�����е�ÿһ��Ӧ��ʵ�����������ʽ
	 * //		List<News> newsList = new ArrayList<News>();
	 * 
	 * //		News news = new News();
		//		news.setTitle("Ľ��������");
		//		news.setDescription("Ľ�����Ǵ�ֱ�Ļ�����IT�������ѧϰ��վ���Զ�����Ƶ�̡̳����߱�̹��ߡ�ѧϰ�ƻ����ʴ�����Ϊ������ɫ�������������ҵ���õĻ���������ţ�ˣ�Ҳ����ͨ����ѵ����߹�����Ƶ�γ�ѧϰ�������ȵĻ�����IT������Ľ�����γ̺���ǰ�˿�����PHP��Html5��Android��iOS��Swift��ITǰ�ؼ������ԣ����������γ̡�ʵ�ð������߼������������ͣ��ʺϲ�ͬ�׶ε�ѧϰ��Ⱥ��");
		//		news.setPicUrl("http://zapper.tunnel.mobi/Weixin/image/imooc.jpg");
		//		news.setUrl("www.imooc.com");
		 * 
		//		newsList.add(news);
	 */
	public static String initNewsMessage(String toUserName,String fromUserName,List<News> newsList){
		String message = null;
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MESSAGE_NEWS);
		newsMessage.setArticles(newsList);
		newsMessage.setArticleCount(newsList.size());
		message = newsMessageToXml(newsMessage);
		return message;
	}
	
	/**
	 * ��װͼƬ��Ϣ
	 * @param toUserName
	 * @param fromUserName
	 * @return
	 * eg:MediaId="JTH8vBl0zDRlrrn2bBnMleySuHjVbMhyAo0U2x7kQyd1ciydhhsVPONbnRrKGp8m"
	 */
	public static String initImageMessage(String toUserName,String fromUserName ,String MediaId){
		String message = null;
		Image image = new Image();
		image.setMediaId(MediaId);
		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setFromUserName(toUserName);
		imageMessage.setToUserName(fromUserName);
		imageMessage.setMsgType(MESSAGE_IMAGE);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setImage(image);
		message = imageMessageToXml(imageMessage);
		return message;
	}
	
	////////////////////////��������Ϊ�����ú���
	
	
	/**
	 * �� initText()��װ�ı���Ϣ����
	 * ��TextMessage�ı���Ϣ����תΪxml����
	 * @param textMessage
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	/**
	 * ����+����ͼ����ϢNewsMessage����תΪxml����
	 * @param newsMessage
	 * @return
	 */
	public static String newsMessageToXml(NewsMessage newsMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", newsMessage.getClass());
		xstream.alias("item", new News().getClass());
		return xstream.toXML(newsMessage);
	}
	
	/**
	 * ͼƬ��Ϣ����תΪxml����
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	

}

