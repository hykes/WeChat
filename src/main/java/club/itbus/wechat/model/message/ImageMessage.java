package club.itbus.wechat.model.message;

import club.itbus.wechat.model.message.BaseMessage;
import lombok.Data;

import java.io.Serializable;

/**
 * Desc:图片消息(image)
 * author:HeHaiYang
 * Date:15/12/21
 */
@Data
public class ImageMessage extends BaseMessage implements Serializable{

    private static final long serialVersionUID = 4748848530096622403L;

    // 通过素材管理接口上传多媒体文件，得到的id
    private Integer MediaId;

    /**
     * 图片消息XML数据包
     *
     * <xml>
     * <ToUserName><![CDATA[toUser]]></ToUserName>
     * <FromUserName><![CDATA[fromUser]]></FromUserName>
     * <CreateTime>12345678</CreateTime>
     * <MsgType><![CDATA[image]]></MsgType>
     * <Image>
     * <MediaId><![CDATA[media_id]]></MediaId>
     * </Image>
     * </xml>
     *
     */

}
