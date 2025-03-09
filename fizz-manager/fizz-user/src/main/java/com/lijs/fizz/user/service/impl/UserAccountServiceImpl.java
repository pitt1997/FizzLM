package com.lijs.fizz.user.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijs.fizz.user.service.UserAccountService;
import com.lijs.fizz.core.model.entity.UserAccountDO;
import com.lijs.fizz.user.dao.UserAccountDAO;
import org.springframework.stereotype.Service;

/**
* @author pitt1
* @description 针对表【t_user_account(用户账号表)】的数据库操作Service实现
* @createDate 2025-01-02 15:26:56
*/
@Service
public class UserAccountServiceImpl extends ServiceImpl<UserAccountDAO, UserAccountDO>
        implements UserAccountService {

}
