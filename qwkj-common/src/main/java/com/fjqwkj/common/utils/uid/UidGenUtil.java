package com.fjqwkj.common.utils.uid;


import com.fjqwkj.uid.service.UidGenerator;
import com.fjqwkj.common.utils.spring.SpringUtils;

/**
 * 生成与解析ID
 * @version 1.0
 * @author: WangZ
 * @date: 2020/11/5 17:30
 */
public class UidGenUtil {

    private static final UidGenerator UID_GENERATOR;
    static {
        UID_GENERATOR = SpringUtils.getBean("uidGenerator");
    }

    /**
     * 生成唯一ID
     * @return: {@link long}
     * @author: WangZ
     * @time: 2020/11/5 17:34
     */
    public static long getUid(){
        return UID_GENERATOR.getUID();
    }

    /**
     * 解析唯一ID
     * @param uid 唯一ID
     * @return: {@link String}
     * @author: WangZ
     * @time: 2020/11/5 17:34
     */
    public static String parseUid(long uid){
        return UID_GENERATOR.parseUID(uid);
    }

}
