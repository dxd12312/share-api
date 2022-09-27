package com.dxd.content.openfegin.fallback;


import com.dxd.content.common.ResponseResult;
import com.dxd.content.domain.entity.User;
import com.dxd.content.openfegin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceFallbackFactory implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable cause) {
        return  id -> {
            log.info("fallback factory method test" +  cause);
            User user = User.builder().nickname("降级方案返回用户").avatar("defalut.jpg").build();
            return ResponseResult.success(user);
        };
    }
}
