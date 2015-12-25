package club.itbus.wechat.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @RestController注解作用：为该controller类下的所有方法添加@ResponseBody注解
 */
@RestController
@RequestMapping
public class SimpleController {

    @Value("${appName}")
    private String appName;

    @Value("${description}")
    private String description;

    @RequestMapping(method = RequestMethod.GET)
    public Map<String,Object> index(HttpServletRequest request, HttpServletResponse response) {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("message","你好");
        map.put("appName", appName);
        map.put("description", description);
        return map;
    }


}