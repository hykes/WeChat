package club.itbus.wechat.model;

import club.itbus.wechat.model.message.*;
import club.itbus.wechat.util.MessageUtil;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Desc:消息响应中心
 * author:HeHaiYang
 * Date:15/12/22
 */
public class MessageResponse {
	
	/**
	 * 创建文本消息
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param content
	 * @return xml
	 */
	public static String getTextMessage(String fromUserName, String toUserName, String content) {

		TextMessage textMessage = new TextMessage();
		textMessage.setToUserName(fromUserName);
		textMessage.setFromUserName(toUserName);
		textMessage.setCreateTime(new Date().getTime());
		textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
		textMessage.setContent(content);
		return MessageUtil.textMessageToXml(textMessage);
	}

	/**
	 * 创建图文消息
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param articles
	 * @param map
     * @return xml
     */
	public static String getNewsMessage(String fromUserName, String toUserName, List<Article> articles, Map<String, Object> map) {
		
		NewsMessage newsMessage = new NewsMessage();
		newsMessage.setToUserName(fromUserName);
		newsMessage.setFromUserName(toUserName);
		newsMessage.setCreateTime(new Date().getTime());
		newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);
		// 设置图文消息个数
		newsMessage.setArticleCount(articles.size());
		// 设置图文消息包含的图文集合
		newsMessage.setArticles(articles);
		newsMessage.setTitle((String) map.get("Title"));
		newsMessage.setDescription((String) map.get("Description"));
		newsMessage.setPicUrl((String) map.get("PicUrl"));
		newsMessage.setUrl((String) map.get("Url"));
		// 将图文消息对象转换成xml字符串
		return MessageUtil.newsMessageToXml(newsMessage);
	}

	/**
	 * 创建音乐消息
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param map
     * @return xml
     */
	public static String getMusicMessage(String fromUserName, String toUserName, Map<String, Object> map){

		MusicMessage musicMessage = new MusicMessage();
		musicMessage.setToUserName(fromUserName);
		musicMessage.setFromUserName(toUserName);
		musicMessage.setCreateTime(new Date().getTime());
		musicMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_MUSIC);
		musicMessage.setMusicURL((String) map.get("MusicURL"));
		musicMessage.setDescription((String) map.get("Description"));
		musicMessage.setHQMusicUrl((String) map.get("HQMusicUrl"));
		musicMessage.setThumbMediaId((Integer) map.get("ThumbMediaId"));
		return MessageUtil.musicMessageToXml(musicMessage);
	}

	/**
	 * 创建图片消息
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param mediaId
     * @return xml
     */
	public static String getImageMessage(String fromUserName, String toUserName, Integer mediaId){

		ImageMessage imageMessage = new ImageMessage();
		imageMessage.setToUserName(fromUserName);
		imageMessage.setFromUserName(toUserName);
		imageMessage.setCreateTime(new Date().getTime());
		imageMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_IMAGE);
		imageMessage.setMediaId(mediaId);
		return MessageUtil.imageMessageToXml(imageMessage);
	}

	/**
	 * 创建语音消息
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param mediaId
     * @return xml
     */
	public static String getVoiceMessage(String fromUserName, String toUserName, Integer mediaId){

		VoiceMessage voiceMessage = new VoiceMessage();
		voiceMessage.setToUserName(fromUserName);
		voiceMessage.setFromUserName(toUserName);
		voiceMessage.setCreateTime(new Date().getTime());
		voiceMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VOICE);
		voiceMessage.setMediaId(mediaId);
		return MessageUtil.voiceMessageToXml(voiceMessage);
	}

	/**
	 * 创建视频消息
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param map
     * @return xml
     */
	public static String getVideoMessage(String fromUserName, String toUserName, Map<String, Object> map){

		VideoMessage videoMessage = new VideoMessage();
		videoMessage.setToUserName(fromUserName);
		videoMessage.setFromUserName(toUserName);
		videoMessage.setCreateTime(new Date().getTime());
		videoMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_VIDEO);
		videoMessage.setTitle((String) map.get("Title"));
		videoMessage.setDescription((String) map.get("Description"));
		videoMessage.setMediaId((Integer) map.get("MediaId"));
		return MessageUtil.videoMessageToXml(videoMessage);
	}

}