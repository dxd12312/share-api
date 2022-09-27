package com.dxd.notice.service.impl;

import com.dxd.notice.domain.entity.Notice;
import com.dxd.notice.repository.NoticeRepository;
import com.dxd.notice.service.NoticeService;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class NoticeServiceImpl implements NoticeService {
    @Resource
    private NoticeRepository noticeRepository;

    @Override
    public Notice getLatestNotice() {
        Sort sort = Sort.by("createTime").descending();
        return noticeRepository.findByShowFlag(true, sort).get(0);
    }
}
