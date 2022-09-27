package com.dxd.content.service;


import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.dxd.content.domain.entity.Share;

import java.util.List;

/**
* @author dxd
* @description 针对表【share(分享表)】的数据库操作Service
* @createDate 2022-09-06 17:56:54
*/
public interface ShareService  {

    /**
     * 根据id查找分享
     *
     * @param id id
     * @return 分享内容
     */
    Share findById(Integer id);

  List<Share>   findAll();

  String getNumber(int number);

    String blockHandlerGetNumber(int number, BlockException e);

}
