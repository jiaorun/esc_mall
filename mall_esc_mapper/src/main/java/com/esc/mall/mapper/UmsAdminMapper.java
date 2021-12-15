package com.esc.mall.mapper;

import com.esc.mall.model.UmsAdmin;
import com.esc.mall.model.UmsAdminExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;
import org.springframework.stereotype.Component;

@Component
public interface UmsAdminMapper {
    long countByExample(UmsAdminExample example);

    int deleteByExample(UmsAdminExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsAdmin record);

    int insertSelective(UmsAdmin record);

    List<UmsAdmin> selectByExampleWithRowbounds(UmsAdminExample example, RowBounds rowBounds);

    List<UmsAdmin> selectByExample(UmsAdminExample example);

    UmsAdmin selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example);

    int updateByExample(@Param("record") UmsAdmin record, @Param("example") UmsAdminExample example);

    int updateByPrimaryKeySelective(UmsAdmin record);

    int updateByPrimaryKey(UmsAdmin record);
}