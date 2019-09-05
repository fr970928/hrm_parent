package com.fengrui.hrm.service.impl;

import com.fengrui.hrm.domain.Role;
import com.fengrui.hrm.mapper.RoleMapper;
import com.fengrui.hrm.service.IRoleService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;


@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements IRoleService {

}
