package com.dxd.notice.service;


import com.dxd.notice.domain.entity.Notice;

public interface NoticeService {
    /**
     * 获取最新通知
     *
     * @return notice
     */
    Notice getLatestNotice();
}
