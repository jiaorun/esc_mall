package com.esc.mall.api.common;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 通用Dao层查询
 * @author jiaorun
 * @date 2021/08/16 17:01
 **/
@Mapper
@Component
public interface BaseMallDaoInterface<T> {

    /**
     * 通过关键字查询列表(分页)
     * @author jiaorun
     * @date 2021/08/16 17:12
     * @param o
     * @return java.util.List<T>
     */
    List<T> selectListByKeyword(Object o);
}
