package com.fengrui.hrm.service.impl;

import com.fengrui.hrm.domain.Permission;
import com.fengrui.hrm.mapper.PermissionMapper;
import com.fengrui.hrm.service.IPermissionService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements IPermissionService {

}
