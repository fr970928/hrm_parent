package com.fengrui.hrm.service.impl;

import com.fengrui.hrm.domain.Employee;
import com.fengrui.hrm.mapper.EmployeeMapper;
import com.fengrui.hrm.service.IEmployeeService;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements IEmployeeService {

}
