package com.dxd.content.openfegin;

import com.dxd.content.common.ResponseResult;
import com.dxd.content.openfegin.fallback.UserServiceFallback;
import com.dxd.content.openfegin.fallback.UserServiceFallbackFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


//@FeignClient(value = "user-service", path = "/users", fallback = UserServiceFallback.class)
@FeignClient(value = "user-service",fallbackFactory = UserServiceFallbackFactory.class)
public interface UserService {
    /**
     * 调用用户服务
     *
     * @param id 用户id
     * @return ResponseResult
     */
    @GetMapping("{id}")
    ResponseResult getUser(@PathVariable(value = "id") int id);

}
