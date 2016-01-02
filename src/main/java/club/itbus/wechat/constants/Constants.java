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
	 * 存储微信access键
	 */
	public static String ACCESS_TOKEN_KEY = "WECHAT_ACCESS_TOKEN_KEY";

	/**
	 * 获取ACCESS_TOKEN接口 get
	 * appid
	 * secret
	 */
	public static String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential";

	/**
	 * ACCESS_TOKEN有效时间(单位：ms)
	 */
	public static Integer EFFECTIVE_TIME = 720000;

	/**
	 * 获取微信服务器IP get
	 * access_token
	 */
	public static String GET_SERVER_IP_URL = "https://api.weixin.qq.com/cgi-bin/getcallbackip";

	/**
	 * =========================自定义菜单============================================
	 */

	/**
	 * 创建自定义菜单 post
	 * access_token
	 */
	public static String CREATE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/create";

	/**
	 * 查询自定义菜单 get
	 * access_token
	 */
	public static String GET_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/get";

	/**
	 * 删除自定义菜单 get
	 * access_token
	 */
	public static String DELETE_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delete";

	/**
	 * 获取自定义菜单配置接口 get
	 * access_token
	 */
	public static String GET_MENU_INFO_URL = "https://api.weixin.qq.com/cgi-bin/get_current_selfmenu_info";

	/**
	 * =========================自定义个性菜单============================================
	 */

	/**
	 * 创建自定义个性菜单接口 post
	 * access_token
	 */
	public static String CREATE_CONDITIONAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/addconditional";

	/**
	 * 删除自定义个性菜单接口 post
	 * access_token
	 */
	public static String DELETE_CONDITIONAL_MENU_URL = "https://api.weixin.qq.com/cgi-bin/menu/delconditional";

	/**
	 * 测试个性化菜单匹配结果 post
	 * access_token
	 */
	public static String TRYMATCH_CONDITIONAL_MENU_URL= "https://api.weixin.qq.com/cgi-bin/menu/trymatch";


	/**
	 * =========================二维码============================================
	 */

	/**
	 * 二维码请求接口 post
	 * access_token
	 */
	public static String CREATE_QRCODE_URL ="https://api.weixin.qq.com/cgi-bin/qrcode/create";

	/**
	 * 二维码显示地址 get
	 * ticket
	 */
	public static String GET_QRCODE_URL ="https://mp.weixin.qq.com/cgi-bin/showqrcode?ticket=";


	/**
	 * =========================短网址============================================
	 */

	/**
	 * 短网址模版
 	 */
	public static String SHORT_URL_TEMPLATE = "{\"action\":\"long2short\",\"long_url\":\"%1$s\"}";

	/**
	 * 获取短网址接口 post
	 * access_token
	 */
	public static String GET_SHORT_URL = "https://api.weixin.qq.com/cgi-bin/shorturl";





	/**
	 * conf.properties文件路径
	 */
	public static String CONF_PROPERTIES_PATH = "WEB-INF/app.properties";
	/**
	 * 微信接入token ，用于验证微信接口
	 */
	public static String TOKEN = "abc123";


}
