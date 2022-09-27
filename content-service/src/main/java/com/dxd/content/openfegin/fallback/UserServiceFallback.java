package com.dxd.content.openfegin.fallback;

import com.dxd.content.common.ResponseResult;
import com.dxd.content.domain.entity.User;
import com.dxd.content.openfegin.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UserServiceFallback implements UserService {
    @Override

    public ResponseResult getUser(int id) {
        log.info("fallback getUser");
        User user = User.builder().id(111).mobile("18700002333").nickname("降级方案返回用户").build();

        return  ResponseResult.success(user);
    }
}
