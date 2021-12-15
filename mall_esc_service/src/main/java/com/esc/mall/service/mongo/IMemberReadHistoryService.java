package com.esc.mall.service.mongo;

import com.esc.mall.document.MemberReadHistory;

import java.util.List;

/**
 * 会员商品浏览记录 接口层
 *
 * @author jiaorun
 * @date 2021/12/14 14:20
 **/
public interface IMemberReadHistoryService {

    /**
     * 生成浏览记录
     *
     * @param memberReadHistory
     * @return int
     * @author jiaorun
     * @data 2021/12/14 14:24
     */
    int create(MemberReadHistory memberReadHistory);

    /**
     * 根据会员Id浏览记录列表
     *
     * @param memberId
     * @return java.util.List<com.esc.mall.document.MemberReadHistory>
     * @author jiaorun
     * @data 2021/12/14 15:07
     */
    List<MemberReadHistory> list(Long memberId);

    /**
     * 删除浏览记录
     *
     * @param ids
     * @return int
     * @author jiaorun
     * @data 2021/12/14 15:08
     */
    int delete(List<String> ids);
}
