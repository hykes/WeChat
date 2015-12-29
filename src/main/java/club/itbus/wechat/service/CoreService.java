package club.itbus.wechat.service;

import club.itbus.wechat.model.MessageResponse;
import club.itbus.wechat.service.MenuClickService;
import club.itbus.wechat.util.MessageUtil;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * Desc:微信消息处理核心服务
 * author:HeHaiYang
 * Date:15/12/21
 */
@Slf4j
public class CoreService {

    /**
     * 处理微信发来的请求
     *
     * @param request
     * @return xml
     */
    public static String processRequest(HttpServletRequest request) {
        String respMessage = null;
        try {
            // 默认返回的文本消息内容
            String respContent = "请求处理异常，请稍候尝试！";

            // xml请求解析
            // 调用消息工具类MessageUtil解析微信发来的xml格式的消息，解析的结果放在HashMap里
            Map<String, String> requestMap = MessageUtil.parseXml(request);
            // 发送方帐号（open_id）
            String fromUserName = requestMap.get("FromUserName");
            // 公众帐号
            String toUserName = requestMap.get("ToUserName");
            // 消息类型
            String msgType = requestMap.get("MsgType");

            log.info("fromUserName is:" + fromUserName + " toUserName is:" + toUserName + " msgType is:" + msgType);

            if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) { // 文本消息

                return MessageResponse.getTextMessage(fromUserName, toUserName, "hello!"+requestMap.get("Content"));
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) { // 图片消息

                return MessageResponse.getTextMessage(fromUserName, toUserName, "你发来的是图片");
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) { // 语音消息
//                // 开启微信声音识别
//                String recvMessage = requestMap.get("Recognition");
//                if (recvMessage != null) {
//                    respContent = recvMessage;
//                } else {
//                    respContent = "您说的太模糊了，能不能重新说下呢？";
//                }
                return MessageResponse.getTextMessage(fromUserName, toUserName, "你发来的是语音");
            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VIDEO)) { // 视频消息

            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_SHORT_VIDEO)) { // 小视频消息

            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) { // 地理位置消息

            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) { // 链接消息

            } else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) { // 事件推送
                String eventType = requestMap.get("Event");// 事件类型
                if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) { // 订阅
                    respContent = "欢迎关注XXX！";
                    return MessageResponse.getTextMessage(toUserName, fromUserName, respContent);
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) { // 取消订阅
                    // TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
                } else if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION)) {

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_VIEW)) {

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) { // 自定义菜单点击事件

                    String eventKey = requestMap.get("EventKey");// 事件KEY值，与创建自定义菜单时指定的KEY值对应
                    log.info("eventKey is:" + eventKey);
                    return MenuClickService.getClickResponse(eventKey, fromUserName, toUserName);

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_SCANCODE_PUSH)) {

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_SCANCODE_WAITMSG)) {

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_PIC_SYSPHOTO)) { // 拍照功能

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_PIC_PHOTO_OR_ALBUM)) {

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_PIC_WEIXIN)) {

                } else if (eventType.equals(MessageUtil.EVENT_TYPE_LOCATION_SELECT)) {

                }
            } else {
                return MessageResponse.getTextMessage(fromUserName, toUserName, respContent);
            }
        } catch (Exception e) {
            log.error("failed to process request, cause:{}", Throwables.getStackTraceAsString(e));
        }
        return respMessage;
    }

}
