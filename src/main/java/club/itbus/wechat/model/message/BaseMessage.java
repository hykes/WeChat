package club.itbus.wechat.model.message;

import lombok.Data;

import java.io.Serializable;

/**
 * Desc:被动回复消息基类
 * author:HeHaiYang
 * Date:15/12/21
 */
@Data
public class BaseMessage implements Serializable{

    private static final long serialVersionUID = 2309472159964099833L;

    // 接收方帐号（收到的OpenID）
    private String ToUserName;  
    // 开发者微信号
    private String FromUserName;  
    // 消息创建时间（整型）
    private Long CreateTime;
    // 消息类型
    private String MsgType;

}
