package club.itbus.wechat.controller;

import club.itbus.wechat.constants.Constants;
import club.itbus.wechat.service.CoreService;
import club.itbus.wechat.util.SignUtil;
import club.itbus.wechat.util.WechatUtil;
import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Desc:微信验证/回复入口
 * author:HeHaiYang
 * Date:15/12/19
 */
@Slf4j
@Controller
@RequestMapping("/api/wechat")
public class WeChatController {

    @RequestMapping(value="/signature", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public String sign(HttpServletRequest request) {
        try {
            String signature = request.getParameter("signature"); // 微信加密签名
            String timestamp = request.getParameter("timestamp"); // 时间戳
            String nonce = request.getParameter("nonce");// 随机数
            String echostr = request.getParameter("echostr");// 随机字符串

            // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
            if (SignUtil.checkSignature(signature, timestamp, nonce)) {
                return echostr;
            } else {
                log.error("不是微信服务器发来的请求,请小心!");
                return null;
            }
        } catch (Exception e) {
            log.error("failed to check signature, cause:{}", Throwables.getStackTraceAsString(e));
            return null;
        }
    }

    @RequestMapping(value="/signature",method = RequestMethod.POST)
    @ResponseBody
    public String getWeChatMessage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        // 将请求、响应的编码均设置为UTF-8（防止中文乱码）
        request.setCharacterEncoding("UTF-8");  //微信服务器POST消息时用的是UTF-8编码，在接收时也要用同样的编码，否则中文会乱码；
        response.setCharacterEncoding("UTF-8"); //在响应消息（回复消息给用户）时，也将编码方式设置为UTF-8，原理同上；
        // 初始化配置文件
        String respMessage = CoreService.processRequest(request);//调用CoreService类的processRequest方法接收、处理消息，并得到处理结果；

        // 响应消息
        // 调用response.getWriter().write()方法将消息的处理结果返回给用户
        return respMessage;
    }

    @RequestMapping(value="/access",method = RequestMethod.GET)
    @ResponseBody
    public String getAccess(HttpServletRequest request, HttpServletResponse response) {
        WechatUtil wechatUtil= new WechatUtil();
        return wechatUtil.getAccessToken();
    }

    @RequestMapping(value="/ip",method = RequestMethod.GET)
    @ResponseBody
    public String getIp(HttpServletRequest request, HttpServletResponse response) {
        WechatUtil wechatUtil= new WechatUtil();
        return wechatUtil.getServerIp();
    }

    @RequestMapping(value="/create-menu",method = RequestMethod.GET)
    @ResponseBody
    public String createMenu(HttpServletRequest request, HttpServletResponse response) {
        WechatUtil wechatUtil= new WechatUtil();
        String json = "{\n" +
                "     \"button\":[\n" +
                "     {\t\n" +
                "          \"type\":\"click\",\n" +
                "          \"name\":\"今日歌曲\",\n" +
                "          \"key\":\"V1001_TODAY_MUSIC\"\n" +
                "      },\n" +
                "      {\n" +
                "           \"name\":\"菜单\",\n" +
                "           \"sub_button\":[\n" +
                "           {\t\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"搜索\",\n" +
                "               \"url\":\"http://www.soso.com/\"\n" +
                "            },\n" +
                "            {\n" +
                "               \"type\":\"view\",\n" +
                "               \"name\":\"视频\",\n" +
                "               \"url\":\"http://v.qq.com/\"\n" +
                "            }\n" +
                "       }]\n" +
                " }";
        return wechatUtil.post(Constants.CREATE_MENU_URL, json, true).toString();
    }

    @RequestMapping(value="/create-ticket",method = RequestMethod.GET)
    @ResponseBody
    public String getTicket(HttpServletRequest request, HttpServletResponse response) {
        WechatUtil wechatUtil= new WechatUtil();
        String json= "{\"expire_seconds\": 604800, \"action_name\": \"QR_SCENE\", \"action_info\": {\"scene\": {\"scene_id\": 123}}}";
        return wechatUtil.post(Constants.CREATE_QRCODE_URL, json, true).toString();
    }

    @RequestMapping(value="/qrcode",method = RequestMethod.GET)
    @ResponseBody
    public String getQRcode(HttpServletRequest request, HttpServletResponse response) {
        WechatUtil wechatUtil= new WechatUtil();
        String ticket=request.getParameter("abc");
        return wechatUtil.get(Constants.CREATE_QRCODE_URL+ticket).toString();
    }

    @RequestMapping(value="/short2long",method = RequestMethod.GET)
    @ResponseBody
    public String getShowUrl(HttpServletRequest request, HttpServletResponse response) {
        WechatUtil wechatUtil= new WechatUtil();
        String url=request.getParameter("url");
        return wechatUtil.shortURL(url).toString();
    }

}
