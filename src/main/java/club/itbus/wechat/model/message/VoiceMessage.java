package club.itbus.wechat.model.message;

import club.itbus.wechat.model.message.BaseMessage;
import lombok.Data;

/**
 * Desc:语音消息(voice)
 * author:HeHaiYang
 * Date:15/12/21
 */
@Data
public class VoiceMessage extends BaseMessage {
	
	// 通过素材管理接口上传多媒体文件，得到的id
    private Integer MediaId;

    /**
     * 语音消息XML数据包
     *
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[voice]]></MsgType>
     * <Voice>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * </Voice>
     * </xml>
     *
     */

}
