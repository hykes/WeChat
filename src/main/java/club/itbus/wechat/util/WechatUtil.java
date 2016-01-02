package club.itbus.wechat.util;

import club.itbus.wechat.constants.Constants;
import com.github.kevinsawicki.http.HttpRequest;
import com.google.common.base.Objects;
import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Desc:公众平台通用接口工具
 * author:HeHaiYang
 * Date:15/12/21
 */
@Slf4j
@Component
public class WechatUtil {

    // 普通消息模版
    private String CUSTOM_MESSAGE_TEMPLATE = "{\"touser\":\"%1$s\",\"msgtype\":\"text\",\"text\":{\"content\":\"%2$s\"}}";

    // 欢迎消息模版
    private String WELCOME_MESSAGE_TEMPLATE = "{ \"touser\": \"%1$s\",\"msgtype\": \"news\", \"news\": { \"articles\":[ { \"title\": \"欢迎" +
            "关注XXX~点击查看XXX介绍！\", \"description\": \"欢迎关注XXX~点击查看XXX介绍！\", \"url\": \"http: //www.baidu.com\",\"picurl\": \"http: //www.baidu.com/icon.png\"}]}}";



    // 获取 OpenID api
    private final String getOpenIdUrl = "https://api.weixin.qq.com/sns/oauth2/access_token";

    // 获取 用户基础信息 api
    private final String getUserInfoUrl = "https://api.weixin.qq.com/cgi-bin/user/info";

    // 获取 用户详细信息 api
    private final String getFullUserInfoUrl = "https://api.weixin.qq.com/sns/userinfo?lang=zh_CN";



    // 发送客服消息给用户
    private final String sendMessage = "https://api.weixin.qq.com/cgi-bin/message/custom/send";

    private static final JsonMapper jsonMapper = JsonMapper.JSON_NON_DEFAULT_MAPPER;

    /**
     * 发送微信GET请求(默认不带上access_token)
     *
     * @param url 请求url
     * @return map结果集
     */
    public Map<String, Object> get(String url){
        return get(url, Boolean.FALSE);
    }

    /**
     * 发送微信GET请求
     *
     * @param url 请求url
     * @param withAccessToken 是否要带上access_token
     * @return map结果集
     */
    public Map<String, Object> get(String url, boolean withAccessToken){
        Map<String, Object> mapResp = doGet(url, withAccessToken);
        log.info("[weixin]: do get request({}), and get response({}).", url, mapResp);
        Object errcode = mapResp.get("errcode");
        if (errcode != null && !"0".equals(errcode.toString()) && withAccessToken){
            if (Objects.equal("40003", errcode)){
                // token失效
                doGetAccessToken();
                return doGet(url, withAccessToken);
            }
            log.error("[weixin]: failed to do wx request({}), cause: {}", url, mapResp);
        }
        return mapResp;
    }

    private Map<String, Object> doGet(String url, boolean withAccessToken){
        String requestUrl = withAccessToken ? addAccessToken(url) : url;
        String jsonResp = HttpRequest.get(requestUrl).body();
        Map<String, Object> mapResp = jsonMapper.fromJson(jsonResp, Map.class);
        return mapResp;
    }

    /**
     * 发送微信POST请求(默认不带上access_token)
     *
     * @param url 请求url
     * @return map结果集
     */
    public Map<String, Object> post(String url){
        return post(url, Boolean.FALSE);
    }

    /**
     * 发送微信POST请求
     *
     * @param url 请求url
     * @param withAccessToken 发送请求是是否带上access_token
     * @return map结果集
     */
    public Map<String, Object> post(String url, boolean withAccessToken){
        return post(url, null, withAccessToken);
    }

    /**
     * 发送微信POST请求
     *
     * @param url             请求url
     * @param data            请求的 request body
     * @param withAccessToken 发送请求是是否带上access_token
     * @return map结果集
     */
    public Map<String, Object> post(String url, String data, boolean withAccessToken) {
        Map<String, Object> mapResp = doPost(url, data, withAccessToken);
        Object errcode = mapResp.get("errcode");
        if (errcode != null && !"0".equals(errcode.toString()) && withAccessToken){
            if (Objects.equal("41001", String.valueOf(errcode)) || Objects.equal("42001", String.valueOf(errcode))) {
                // 41001 缺少access_token参数，42001 token过期
                doGetAccessToken();
                return doPost(url, data, withAccessToken);
            }
            log.error("[weixin]: failed to do wx request({}), cause: {}", url, mapResp);
        }
        return mapResp;
    }

    private Map<String, Object> doPost(String url, String data, boolean withAccessToken){
        String requestUrl = withAccessToken ? addAccessToken(url) : url;
        HttpRequest request = HttpRequest.post(requestUrl);
        if (!Strings.isNullOrEmpty(data)) {
            request.send(data);
        }

        String jsonResp = request.body();
        Map<String, Object> mapResp = jsonMapper.fromJson(jsonResp, Map.class);
        log.info("[weixin]: do post request({}), and get response({}).", url, mapResp);
        return mapResp;
    }

    /**
     * 链接中加上access_token
     *
     * @param url 请求url
     * @return 添加access_token后的url
     */
    private String addAccessToken(String url) {
        StringBuilder requestUrl = new StringBuilder(url);
        if (requestUrl.indexOf("?") != -1){
            requestUrl.append("&access_token=").append(getAccessToken());
        } else {
            requestUrl.append("?access_token=").append(getAccessToken());
        }
        return requestUrl.toString();
    }

    /**
     * 获取访问token
     *
     * @return access_token
     */

    public String getAccessToken() {
        String token =null;

        if (Strings.isNullOrEmpty(token)){
            token = doGetAccessToken();
        }
        return token;
    }

    /**
     * 获取access_token
     *
     * @return access_token
     */
    public String doGetAccessToken() {
        StringBuilder requestUrl = new StringBuilder(Constants.GET_ACCESS_TOKEN_URL);
        requestUrl.append("&appid=").append(Constants.APPID)
                .append("&secret=").append(Constants.SECRET);
        Map<String, Object> mapResp = doGet(requestUrl.toString(), Boolean.FALSE);
        if (isError(mapResp)){
            log.error("failed to get wx access token, cause: {}", mapResp);
        } else {
            String token = String.valueOf(mapResp.get("access_token"));
            Integer expires;
            try {
                expires = Integer.valueOf(String.valueOf(mapResp.get("expires_in")));
            } catch (NumberFormatException e){
                expires = 3600;
            }
            doSaveToken(token, expires);
            return token;
        }
        return null;
    }

    private void doSaveToken(final String token, final Integer expires) {
        doSaveToken(Constants.ACCESS_TOKEN_KEY, token, expires);
    }

    private void doSaveToken(final String key, final String token, final Integer expires) {

    }

    public String getServerIp(){
        return doGet(Constants.GET_SERVER_IP_URL, true).toString();
    }

    public Boolean isError(Map<String, Object> result) {
        return result.get("errcode") != null && !"0".equals(result.get("errcode").toString());
    }

    /**
     * 获取微信用户的openid
     *
     * @param code 认证后的code值
     * @return 获取成功openid存放在map中, 反之errcode为错误码
     */
    public Map<String, Object> getOpenId(String code){
        StringBuilder url = new StringBuilder(getOpenIdUrl);
        url.append("?appid=").append(Constants.APPID)
                .append("&secret=").append(Constants.SECRET)
                .append("&code=").append(code)
                .append("&grant_type=authorization_code");
        Map<String, Object> mapResp = get(url.toString());

        // 保存应用token
        if (mapResp.containsKey("access_token")) {
            String token = String.valueOf(mapResp.get("access_token"));
            Integer expires;
            try {
                expires = Integer.valueOf(String.valueOf(mapResp.get("expires_in")));
            } catch (NumberFormatException e) {
                expires = 3600;
            }
            doSaveToken(token, expires);
        }
        return mapResp;
    }

    /**
     * 获取微信用户信息, 若用户未关注公众号, 将获取不到用户信息
     *
     * @param openId 用户openid
     * @return 微信用户信息, 若用户未关注公众号, 将获取不到用户信息
     */
    public Map<String, Object> getUserInfo(String openId) {
        return doPost(getUserInfoUrl + "?openid=" + openId, null, Boolean.TRUE);
    }
    public Map<String, Object> getFullUserInfo(String openId){
        StringBuilder urlBuilder = new StringBuilder(getFullUserInfoUrl)
                .append("&openid=").append(openId)
                .append("&access_token=").append(getAccessToken());
        return doPost(urlBuilder.toString(), null, Boolean.FALSE);
    }

    /**
     * 获取短连接
     *
     * @param longUrl 传入的长链接
     */
    public Map<String, Object> shortURL(String longUrl) {
        return post(Constants.GET_SHORT_URL, String.format(Constants.SHORT_URL_TEMPLATE, longUrl), Boolean.TRUE);
    }

    /**
     * 发送普通消息给用户
     *
     * @param openId
     * @param message
     * @return
     */
    public Map<String, Object> sendMessage(String openId, String message) {
        return post(sendMessage, String.format(CUSTOM_MESSAGE_TEMPLATE, openId, message), Boolean.TRUE);
    }

    /**
     * 发送欢迎消息给用户
     *
     * @param openId
     * @return
     */
    public  Map<String, Object> sendWelcomeMessage(String openId) {
        return post(sendMessage, String.format(WELCOME_MESSAGE_TEMPLATE, openId), Boolean.TRUE);
    }

}

