package com.fengrui.hrm.util;

import com.fengrui.hrm.domain.Employee;
import com.fengrui.hrm.domain.Tenant;

public class UserInfoHolder {
    public static Tenant getTenant(){
        Tenant currentLoginUserTenant = new Tenant();
        currentLoginUserTenant.setId(26L);
        currentLoginUserTenant.setCompanyName("233哈哈哈");
        return currentLoginUserTenant;
    }
    public static Employee getLoginUser(){
        Employee employee = new Employee();
        employee.setId(42L);
        employee.setUsername("fr");
        return employee;
    }
}
