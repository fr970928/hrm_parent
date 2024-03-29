package com.fengrui.hrm.client;

import com.fengrui.hrm.domain.Employee;
import com.fengrui.hrm.query.EmployeeQuery;
import com.fengrui.hrm.util.AjaxResult;
import com.fengrui.hrm.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "ZUUL-GATEWAY",configuration = FeignClientsConfiguration.class,
        fallbackFactory = EmployeeClientHystrixFallbackFactory.class)
@RequestMapping("/employee")
public interface EmployeeClient {
    /**
     * 保存和修改公用的
     * @param employee  传递的实体
     * @return Ajaxresult转换结果
     */
    @RequestMapping(value="/save",method= RequestMethod.POST)
    AjaxResult save(Employee employee);

    /**
     * 删除对象信息
     * @param id
     * @return
     */
    @RequestMapping(value="/delete/{id}",method=RequestMethod.DELETE)
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    Employee get(@RequestParam(value = "id", required = true) Long id);


    /**
     * 查看所有的员工信息
     * @return
     */
    @RequestMapping("/list")
    public List<Employee> list();

    /**
     * 分页查询数据
     *
     * @param query 查询对象
     * @return PageList 分页对象
     */
    @RequestMapping(value = "/json",method = RequestMethod.POST)
    PageList<Employee> json(@RequestBody EmployeeQuery query);
}
