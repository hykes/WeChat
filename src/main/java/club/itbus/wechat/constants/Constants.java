package club.itbus.wechat.constants;

/**
 * Desc:常量
 * author:HeHaiYang
 * Date:15/12/19
 */
public class Constants {
	/**
	 * APPID
	 */
	public static String APPID = "wxe6915cbc0aa9ea1f";

	/**
	 * SECRET
	 */
	public static String SECRET = "63cc94d0b6ebc33ed02ec29c0b030b14";

	/**
	 * 微信access键
	 */
	public static String ACCESS_TOKEN_KEY = "WECHAT_ACCESS_TOKEN_KEY";

	/**
	 * 获取ACCESS_TOKEN接口
	 */
	public static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	/**
	 * ACCESS_TOKEN有效时间(单位：ms)
	 */
	public static int EFFECTIVE_TIME = 720000;
	/**
	 * conf.properties文件路径
	 */
	public static String CONF_PROPERTIES_PATH = "WEB-INF/app.properties";
	/**
	 * 微信接入token ，用于验证微信接口
	 */
	public static String TOKEN = "abc123";


}
