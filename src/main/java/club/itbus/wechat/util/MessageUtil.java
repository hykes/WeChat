package club.itbus.wechat.util;


import club.itbus.wechat.model.message.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import javax.servlet.http.HttpServletRequest;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Desc:微信XML解析工具
 * author:HeHaiYang
 * Date:15/12/21
 */
public class MessageUtil {
	
	/** 
     * 返回消息类型:文本
     */  
    public static final String RESP_MESSAGE_TYPE_TEXT = "text";

    /**
     * 返回消息类型:图文
     */
    public static final String RESP_MESSAGE_TYPE_NEWS = "news";

    /** 
     * 返回消息类型:音乐
     */  
    public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

    /**
     * 返回消息类型:图片
     */
    public static final String RESP_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 返回消息类型:语音
     */
    public static final String RESP_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 返回消息类型:视频
     */
    public static final String RESP_MESSAGE_TYPE_VIDEO = "video";

    /**
     * ==============================================================
     */

    /** 
     * 请求消息类型:文本
     */  
    public static final String REQ_MESSAGE_TYPE_TEXT = "text";  
  
    /** 
     * 请求消息类型:图片
     */  
    public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

    /**
     * 请求消息类型:语音
     */
    public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

    /**
     * 请求消息类型:视频
     */
    public static final String REQ_MESSAGE_TYPE_VIDEO = "video";

    /**
     * 请求消息类型:小视频
     */
    public static final String REQ_MESSAGE_TYPE_SHORT_VIDEO = "shortvideo";

    /**
     * 请求消息类型:地理位置
     */
    public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

    /** 
     * 请求消息类型:链接
     */  
    public static final String REQ_MESSAGE_TYPE_LINK = "link";  

    /** 
     * 请求消息类型:事件
     */  
    public static final String REQ_MESSAGE_TYPE_EVENT = "event";

    /**
     * ==============================================================
     */

    /** 
     * 事件类型:subscribe(订阅)
     */  
    public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";  
  
    /** 
     * 事件类型:unsubscribe(取消订阅)
     */  
    public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

    /**
     * 事件类型:SCAN(扫描带参数二维码事件*已关注*)
     */
    public static final String EVENT_TYPE_SCAN = "SCAN";

    /** 
     * 事件类型:CLICK(点击菜单拉取消息时的事件推送)
     */  
    public static final String EVENT_TYPE_CLICK = "CLICK";

    /**
     * 事件类型:VIEW(点击菜单跳转链接时的事件推送)
     */
    public static final String EVENT_TYPE_VIEW = "VIEW";

    /**
     * 事件类型:LOCATION(上报地理位置事件)
     */
    public static final String EVENT_TYPE_LOCATION = "LOCATION";

    /**
     * 事件类型:scancode_push(自定义菜单事件:扫码推事件)
     */
    public static final String EVENT_TYPE_SCANCODE_PUSH = "scancode_push";

    /**
     * 事件类型:scancode_waitmsg(自定义菜单事件:扫码推事件且弹出“消息接收中”提示框)
     */
    public static final String EVENT_TYPE_SCANCODE_WAITMSG = "scancode_waitmsg";

    /**
     * 事件类型:pic_sysphoto(自定义菜单事件:弹出系统拍照发图)
     */
    public static final String EVENT_TYPE_PIC_SYSPHOTO = "pic_sysphoto";

    /**
     * 事件类型:pic_photo_or_album(自定义菜单事件:弹出拍照或者相册发图)
     */
    public static final String EVENT_TYPE_PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";

    /**
     * 事件类型:pic_weixin(自定义菜单事件:弹出微信相册发图器)
     */
    public static final String EVENT_TYPE_PIC_WEIXIN = "pic_weixin";

    /**
     * 事件类型:location_select(自定义菜单事件:弹出地理位置选择器)
     */
    public static final String EVENT_TYPE_LOCATION_SELECT = "location_select";

    /**
     * ==============================================================
     */

    /** 
     * 解析微信发来的请求（XML） 
     *  
     * @param request 
     * @return 
     * @throws Exception 
     */  
    @SuppressWarnings("unchecked")  
    public static Map<String, String> parseXml(HttpServletRequest request) throws Exception {
        // 将解析结果存储在HashMap中  
        Map<String, String> map = new HashMap<String, String>();  
  
        // 从request中取得输入流  
        InputStream inputStream = request.getInputStream();  
        // 读取输入流  
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素  
        Element root = document.getRootElement();
        // 得到根元素的所有子节点  
        List<Element> elementList = root.elements();
  
        // 遍历所有子节点  
        for (Element e : elementList)
            map.put(e.getName(), e.getText());  
  
        // 释放资源  
        inputStream.close();  
        inputStream = null;  
  
        return map;  
    }  
  
    /** 
     * 文本消息对象转换成xml 
     *  
     * @param textMessage 文本消息对象 
     * @return xml 
     */  
    public static String textMessageToXml(TextMessage textMessage) {
        xstream.alias("xml", textMessage.getClass());  
        return xstream.toXML(textMessage);  
    }

    /**
     * 图文消息对象转换成xml
     *
     * @param newsMessage 图文消息对象
     * @return xml
     */
    public static String newsMessageToXml(NewsMessage newsMessage) {
        xstream.alias("xml", newsMessage.getClass());
        xstream.alias("item", new Article().getClass());
        return xstream.toXML(newsMessage);
    }

    /** 
     * 音乐消息对象转换成xml 
     *  
     * @param musicMessage 音乐消息对象 
     * @return xml 
     */
    public static String musicMessageToXml(MusicMessage musicMessage) {
        xstream.alias("xml", musicMessage.getClass());  
        return xstream.toXML(musicMessage);  
    }

    /**
     * 图片消息对象转换成xml
     *
     * @param imageMessage
     * @return xml
     */
    public static String imageMessageToXml(ImageMessage imageMessage) {
        xstream.alias("xml", imageMessage.getClass());
        return xstream.toXML(imageMessage);
    }

    /**
     * 语音消息对象转换成xml
     *
     * @param voiceMessage
     * @return xml
     */
    public static String voiceMessageToXml(VoiceMessage voiceMessage) {
        xstream.alias("xml", voiceMessage.getClass());
        return xstream.toXML(voiceMessage);
    }

    /**
     * 视频消息对象转换成xml
     *
     * @param videoMessage
     * @return xml
     */
    public static String videoMessageToXml(VideoMessage videoMessage) {
        xstream.alias("xml", videoMessage.getClass());
        return xstream.toXML(videoMessage);
    }

    /** 
     * 扩展xstream，使其支持CDATA块
     *
     */
    private static XStream xstream = new XStream(new XppDriver() {
        public HierarchicalStreamWriter createWriter(Writer out) {
            return new PrettyPrintWriter(out) {
                // 对所有xml节点的转换都增加CDATA标记  
                boolean cdata = true;  
  
                @SuppressWarnings("unchecked")  
                public void startNode(String name, Class clazz) {  
                    super.startNode(name, clazz);
//                	super.startNode(name);
                }  
  
                protected void writeText(QuickWriter writer, String text) {
                    if (cdata) {  
                        writer.write("<![CDATA[");  
                        writer.write(text);  
                        writer.write("]]>");  
                    } else {  
                        writer.write(text);  
                    }  
                }  
            };  
        }  
    });
}
