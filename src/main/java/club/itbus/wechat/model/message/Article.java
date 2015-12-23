package club.itbus.wechat.model.message;

import lombok.Data;

/**
 * Desc:文章实体类
 * author:HeHaiYang
 * Date:15/12/21
 */
@Data
public class Article {
    // 图文消息名称
    private String Title;
    // 图文消息描述
    private String Description;
    // 图片链接，支持JPG、PNG格式，较好的效果为大40*320
    private String PicUrl;
    // 点击图文消息跳转链接
    private String Url;

}
