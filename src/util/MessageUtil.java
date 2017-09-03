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
 * 文本消息的封装类，图文消息的封装，图片消息的封装， xml形式的request报文转为map集合
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
	 * xml形式的request报文转为map集合
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
	 * 输入发送方，接收方和发送内容返回文本消息xml报文
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
 * 可在函数里输入自定义的欢迎消息，返回消息的String大字符串，可被initText调用为content
 * @return
 */
	public static String menuText()
	{
		StringBuffer sb = new StringBuffer();
		sb.append("欢迎您关注石油大学的互帮互助平台，请按照菜单提示进行操作：\n\n");
		sb.append("1、介绍\n");
		sb.append("2、介绍\n");
		sb.append("3、词组\n\n");
		sb.append("回复？调出此菜单。");
		return sb.toString();
	}

	/**
	 * 图文消息的组装  newsList对象中的每一项应该实例化成下面格式
	 * //		List<News> newsList = new ArrayList<News>();
	 * 
	 * //		News news = new News();
		//		news.setTitle("慕课网介绍");
		//		news.setDescription("慕课网是垂直的互联网IT技能免费学习网站。以独家视频教程、在线编程工具、学习计划、问答社区为核心特色。在这里，你可以找到最好的互联网技术牛人，也可以通过免费的在线公开视频课程学习国内领先的互联网IT技术。慕课网课程涵盖前端开发、PHP、Html5、Android、iOS、Swift等IT前沿技术语言，包括基础课程、实用案例、高级分享三大类型，适合不同阶段的学习人群。");
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
	 * 组装图片消息
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
	
	////////////////////////下面内容为被调用函数
	
	
	/**
	 * 被 initText()组装文本消息调用
	 * 将TextMessage文本消息对象转为xml报文
	 * @param textMessage
	 */
	public static String textMessageToXml(TextMessage textMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", textMessage.getClass());
		return xstream.toXML(textMessage);
	}
	/**
	 * 单项+多项图文消息NewsMessage对象转为xml报文
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
	 * 图片消息对象转为xml报文
	 * @param imageMessage
	 * @return
	 */
	public static String imageMessageToXml(ImageMessage imageMessage){
		XStream xstream = new XStream();
		xstream.alias("xml", imageMessage.getClass());
		return xstream.toXML(imageMessage);
	}
	

}

