package com.esc.mall.api.common;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 通用Dao层
 *
 * @author jiaorun
 * @date 2021/08/16 17:01
 **/
@Mapper
@Component
public interface BaseMallDaoInterface<T> {

    /**
     * 通过关键字查询列表
     *
     * @param o
     * @return java.util.List<T>
     * @author jiaorun
     * @data 2021/11/5 16:22
     */
    List<T> selectListByKeyword(Object o);
}
