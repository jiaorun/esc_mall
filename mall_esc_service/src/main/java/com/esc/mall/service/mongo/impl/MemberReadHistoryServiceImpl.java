package com.esc.mall.service.mongo.impl;

import com.esc.mall.document.MemberReadHistory;
import com.esc.mall.repository.MemberReadHistoryRepository;
import com.esc.mall.service.mongo.IMemberReadHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 会员浏览商品记录 业务实现层
 *
 * @author jiaorun
 * @date 2021/12/14 14:25
 **/
@Service
@Transactional
public class MemberReadHistoryServiceImpl implements IMemberReadHistoryService {

    private final MemberReadHistoryRepository memberReadHistoryRepository;

    @Autowired
    public MemberReadHistoryServiceImpl(MemberReadHistoryRepository memberReadHistoryRepository) {
        this.memberReadHistoryRepository = memberReadHistoryRepository;
    }

    @Override
    public int create(MemberReadHistory memberReadHistory) {
        memberReadHistory.setId(null);  //这里将ID设置为null，mongodb会分配一个随机ID
        memberReadHistory.setCreateTime(new Date());
        memberReadHistoryRepository.save(memberReadHistory);
        return 1;
    }

    @Override
    public List<MemberReadHistory> list(Long memberId) {
        return memberReadHistoryRepository.findByMemberIdOrderByCreateTimeDesc(memberId);
    }

    @Override
    public int delete(List<String> ids) {
        List<MemberReadHistory> list = new ArrayList<>();
        if (!CollectionUtils.isEmpty(ids)) {
            for (String id : ids) {
                MemberReadHistory memberReadHistory = new MemberReadHistory();
                memberReadHistory.setId(id);
                list.add(memberReadHistory);
            }
        }
        memberReadHistoryRepository.deleteAll(list);
        return ids.size();
    }
}
