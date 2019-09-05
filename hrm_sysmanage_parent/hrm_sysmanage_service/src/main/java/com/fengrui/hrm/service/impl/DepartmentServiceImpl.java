package com.fengrui.hrm.service.impl;

import com.fengrui.hrm.domain.Department;
import com.fengrui.hrm.mapper.DepartmentMapper;
import com.fengrui.hrm.service.IDepartmentService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements IDepartmentService {

}
