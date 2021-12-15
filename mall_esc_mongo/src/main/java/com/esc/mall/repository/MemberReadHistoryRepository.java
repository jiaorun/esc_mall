package com.esc.mall.repository;

import com.esc.mall.document.MemberReadHistory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 会员浏览商品历史repository
 *
 * @author jiaorun
 * @date 2021/12/14 12:28
 **/
@Component
public interface MemberReadHistoryRepository extends MongoRepository<MemberReadHistory, String> {

    /**
     * 根据会员ID按照创建时间倒序获取浏览记录
     *
     * @param memberId
     * @return java.util.List<com.esc.mall.document.MemberReadHistory>
     * @author jiaorun
     * @data 2021/12/14 14:17
     */
    List<MemberReadHistory> findByMemberIdOrderByCreateTimeDesc(Long memberId);
}
