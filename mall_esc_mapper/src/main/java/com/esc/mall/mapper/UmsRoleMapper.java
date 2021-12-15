package com.esc.mall.mapper;

import com.esc.mall.model.UmsRole;
import com.esc.mall.model.UmsRoleExample;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

public interface UmsRoleMapper {
    long countByExample(UmsRoleExample example);

    int deleteByExample(UmsRoleExample example);

    int deleteByPrimaryKey(Long id);

    int insert(UmsRole record);

    int insertSelective(UmsRole record);

    List<UmsRole> selectByExampleWithRowbounds(UmsRoleExample example, RowBounds rowBounds);

    List<UmsRole> selectByExample(UmsRoleExample example);

    UmsRole selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    int updateByExample(@Param("record") UmsRole record, @Param("example") UmsRoleExample example);

    int updateByPrimaryKeySelective(UmsRole record);

    int updateByPrimaryKey(UmsRole record);
}