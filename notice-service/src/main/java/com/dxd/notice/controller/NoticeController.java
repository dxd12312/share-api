package com.dxd.notice.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dxd.notice.common.ResponseResult;
import com.dxd.notice.common.ResultCode;
import com.dxd.notice.service.NoticeService;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RefreshScope
@Slf4j
@RequestMapping(value = "/notices")
public class NoticeController {
    @Resource
    private NoticeService noticeService;

    @Value("${disableNoticeRequest:false}")
    private Boolean disableNotice;

    @GetMapping("/latest")
    @SentinelResource(value = "getNotice", blockHandler = "getNoticeBlock")
    public ResponseResult getNotice() {
        if (disableNotice) {
            log.info("暂停公告服务");
            return ResponseResult.failure(ResultCode.INTERFACE_FORBID_VISIT);
        }
        val notice = noticeService.getLatestNotice();
        if (notice != null) {
            return ResponseResult.success(notice);
        } else {
            return ResponseResult.failure(ResultCode.RESULT_CODE_DATA_NONE);
        }
//        return ResponseResult.success(noticeService.getLatestNotice());
    }


    public ResponseResult getNoticeBlock(BlockException exception) {
        log.info("接口被限流");
        log.info(exception.toString());
        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
    }
}
