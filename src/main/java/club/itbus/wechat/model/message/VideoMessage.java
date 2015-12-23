package club.itbus.wechat.model.message;

import club.itbus.wechat.model.message.BaseMessage;
import lombok.Data;

/**
 * Desc:视频消息(video)
 * author:HeHaiYang
 * Date:15/12/21
 */
@Data
public class VideoMessage extends BaseMessage {
	
	// 通过素材管理接口上传多媒体文件，得到的id
    private Integer MediaId;

    // 视频消息的标题
    private String Title;

    // 视频消息的描述
    private String Description;

    /**
     * 视频消息XML数据包
     *
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[video]]></MsgType>
     * <Video>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * <Title><![CDATA[title]]></Title>
     * <Description><![CDATA[description]]></Description>
     * </Video>
     * </xml>
     *
     */

}
