package club.itbus.wechat.model.message;

import club.itbus.wechat.model.message.BaseMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * Desc:文本消息(text)
 * author:HeHaiYang
 * Date:15/12/21
 */
@Data
public class TextMessage extends BaseMessage implements Serializable{

    private static final long serialVersionUID = 6927068033720909436L;

    // 文本消息内容
    private String Content;

    /**
     * 回复文本消息XML数据包
     *
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[text]]></MsgType>
     * <Content><![CDATA[你好]]></Content>
     * </xml>
     *
     */

}
