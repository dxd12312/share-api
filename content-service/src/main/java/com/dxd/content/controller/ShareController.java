package com.dxd.content.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.fastjson.JSONObject;
import com.dxd.content.common.ResponseResult;
import com.dxd.content.common.ResultCode;
import com.dxd.content.domain.dto.ShareDto;
import com.dxd.content.domain.entity.Share;
import com.dxd.content.domain.entity.User;
import com.dxd.content.openfegin.UserService;
import com.dxd.content.service.ShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
@RequestMapping(value = "/shares")

public class ShareController {

    @Resource
    private ShareService shareService;

    @Resource
    private UserService userService;

    @GetMapping("{id}")
    @SentinelResource(value = "getUserById")
    public ResponseResult getUserById(@PathVariable Integer id) {
        String result = shareService.getNumber(2025);
        log.info(result);
        if ("BLOCKED".equals(result)) {
            return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
        }
        Share share = shareService.findById(id);
        Integer userId = share.getUserId();
        ResponseResult res = userService.getUser(userId);
        String jsonString = JSONObject.toJSONString(res.getData());
        JSONObject obj = JSONObject.parseObject(jsonString);
        User user = JSONObject.toJavaObject(obj, User.class);
        System.out.println(user);

        ShareDto shareDto = ShareDto.builder().share(share).nickname(user.getNickname()).avatar(user.getAvatar()).build();
        return ResponseResult.success(shareDto);
    }

    public ResponseResult getUserAllBlock(BlockException exception) {
        log.info("接口被限流");
        log.info(exception.toString());
        return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
    }


    @GetMapping("/all")
    @SentinelResource(value = "getUserAll")
    public ResponseResult getUserAll() {
        String result = shareService.getNumber(2022);
        log.info(result);
        if ("BLOCKED".equals(result)) {
            return ResponseResult.failure(ResultCode.INTERFACE_EXCEED_LOAD);
        }
//
        return ResponseResult.success(shareService.findAll());


    }
}
