package club.itbus.wechat.service;

/**
 * Desc:菜单点击事件处理接口
 * author:HeHaiYang
 * Date:15/12/21
 */
public class MenuClickService {

	/**
	 *
	 * @param fromUserName
	 * @param toUserName
	 * @param eventKey
     * @return
     */
	public static String getClickResponse(String fromUserName, String toUserName, String eventKey) {
		if(eventKey.equals("test")) {
			return "test";
		}
		return null;
	}

}
