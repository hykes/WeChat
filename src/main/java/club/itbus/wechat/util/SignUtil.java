package club.itbus.wechat.util;

import club.itbus.wechat.constants.Constants;
import com.google.common.base.Charsets;
import com.google.common.base.Joiner;
import com.google.common.hash.Hashing;
import lombok.extern.slf4j.Slf4j;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

/**
 * Desc:请求校验工具
 * author:HeHaiYang
 * Date:15/12/21
 */
@Slf4j
public class SignUtil {

    /** 
     * 验证签名 
     *  
     * @param signature 
     * @param timestamp 
     * @param nonce 
     * @return 
     */  
    public static boolean checkSignature(String signature, String timestamp, String nonce) {  
        String[] params = new String[] { Constants.TOKEN, timestamp, nonce };
        // 将token、timestamp、nonce三个参数进行字典序排序,拼接成一个字符串进行sha1加密

        String tmpStr = generateSign(sortParams(params));
        log.debug("加密排序后的字符串："+tmpStr);

        // 将sha1加密后的字符串可与signature对比，标识该请求来源于微信  
        return tmpStr != null ? tmpStr.equals(signature.toUpperCase()) : false;  
    }

    /**
     * 参数排序
     * @param params
     * @return
     */
    private static String sortParams(String[] params) {
        Arrays.sort(params);
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < params.length; i++) {
            result.append(params[i]);
        }
        return result.toString();
    }

    /**
     * sha1加密
     * @param toVerify
     * @return
     */
    private static String generateSign(String toVerify){
        return Hashing.sha1().newHasher(16)
                .putString(toVerify, Charsets.UTF_8).hash().toString().toUpperCase();
    }
    
}  

