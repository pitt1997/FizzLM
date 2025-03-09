package com.lijs.fizz.role.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lijs.fizz.core.model.entity.permission.PermissionDO;
import com.lijs.fizz.role.dao.PermissionDAO;
import com.lijs.fizz.role.service.PermissionService;
import org.springframework.stereotype.Service;

/**
 * @author pitt1
 * @description 针对表【t_permission(功能权限表)】的数据库操作Service实现
 * @createDate 2025-01-02 15:26:56
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionDAO, PermissionDO>
        implements PermissionService {

}
