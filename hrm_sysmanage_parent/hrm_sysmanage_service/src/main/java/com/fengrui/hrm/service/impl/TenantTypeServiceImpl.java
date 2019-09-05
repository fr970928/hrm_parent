package com.fengrui.hrm.service.impl;

import com.fengrui.hrm.domain.TenantType;
import com.fengrui.hrm.mapper.TenantTypeMapper;
import com.fengrui.hrm.service.ITenantTypeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class TenantTypeServiceImpl extends ServiceImpl<TenantTypeMapper, TenantType> implements ITenantTypeService {

}
