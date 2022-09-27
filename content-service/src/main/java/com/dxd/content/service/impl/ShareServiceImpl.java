package com.dxd.content.service.impl;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dxd.content.domain.entity.Share;
import com.dxd.content.repository.ShareRepository;
import com.dxd.content.service.ShareService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
* @author dxd
* @description 针对表【share(分享表)】的数据库操作Service实现
* @createDate 2022-09-06 17:56:54
*/
@Service
@Slf4j
public class ShareServiceImpl implements ShareService {

    @Resource
    private ShareRepository shareRepository;

    @Override
    public Share findById(Integer id) {
        return shareRepository.findById(id).orElse(null);
    }

    @Override
    public List<Share> findAll() {
        return shareRepository.findAll();
    }

    @SentinelResource(value = "getNumber",blockHandler = "blockHandlerGetNumber")
    @Override
    public String getNumber(int number) {
        return "number = {" + number + "}";
    }

    @Override
    public String blockHandlerGetNumber(int number, BlockException e) {
        return "BLOCKED";
    }


}
