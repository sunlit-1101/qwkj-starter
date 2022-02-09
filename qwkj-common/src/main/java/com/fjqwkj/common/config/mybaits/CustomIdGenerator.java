package com.fjqwkj.common.config.mybaits;

import com.baomidou.mybatisplus.core.incrementer.IdentifierGenerator;
import com.fjqwkj.common.utils.uid.UidGenUtil;
import org.springframework.stereotype.Component;

/**
 * 自定义MyBaits-Plus主键生成器
 * @version 1.0
 * @author: WangZ
 * @date: 2020/11/8 20:45
 * @description: 自定义MyBaits-Plus主键生成器
 */

@Component
public class CustomIdGenerator implements IdentifierGenerator {
    /**
     * 使用uid-generator生成Id
     * @param entity 实体
     * @return id
     */
    @Override
    public Number nextId(Object entity) {
        return UidGenUtil.getUid();
    }

}
