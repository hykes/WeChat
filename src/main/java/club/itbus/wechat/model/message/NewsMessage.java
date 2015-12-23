package club.itbus.wechat.model.message;

import club.itbus.wechat.model.message.Article;
import club.itbus.wechat.model.message.BaseMessage;
import lombok.Data;

import java.util.List;

/**
 * Desc:图文消息(news)
 * author:HeHaiYang
 * Date:15/12/21
 */
@Data
public class NewsMessage extends BaseMessage {
	
	// 图文消息个数，限制为10条以内 
    private int ArticleCount;  
    // 多条图文消息信息，默认第一个item为大图,注意，如果图文数超过10，则将会无响应
    private List<Article> Articles;

    // 图文消息标题
    private String Title;

    // 图文消息描述
    private String Description;

    // 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
    private String PicUrl;

    // 点击图文消息跳转链接
    private String Url;

    /**
     * 图文消息XML数据包
     *
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[news]]></MsgType>
     * <ArticleCount>2</ArticleCount>
     * <Articles>
     * <item>
     * <Title><![CDATA[title1]]></Title>
     * <Description><![CDATA[description1]]></Description>
     * <PicUrl><![CDATA[picurl]]></PicUrl>
     * <Url><![CDATA[url]]></Url>
     * </item>
     * <item>
     * <Title><![CDATA[title]]></Title>
     * <Description><![CDATA[description]]></Description>
     * <PicUrl><![CDATA[picurl]]></PicUrl>
     * <Url><![CDATA[url]]></Url>
     * </item>
     * </Articles>
     * </xml>
     *
     */

}
