package com.fengrui.hrm.client;

import com.fengrui.hrm.doc.EsCourse;
import com.fengrui.hrm.query.EsCourseQuery;
import com.fengrui.hrm.util.AjaxResult;
import com.fengrui.hrm.util.PageList;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "HRM-ES",configuration = FeignClientsConfiguration.class,
        fallbackFactory = EsCourseClientHystrixFallbackFactory.class)
@RequestMapping("/esCourse")
public interface EsCourseClient {

    //保存和修改
    @PostMapping("/save")
    AjaxResult save(EsCourse esCourse);

    //删除
    @DeleteMapping("/delete/{id}")
    AjaxResult delete(@PathVariable("id") Integer id);

    //获取用户
    @RequestMapping("/{id}")
    EsCourse get(@RequestParam(value = "id", required = true) Long id);

    //查看所有员工信息
    @RequestMapping("/list")
    public List<EsCourse> list();

    //分页数据
    @PostMapping("/json")
    PageList<EsCourse> json(@RequestBody EsCourseQuery query);

    @PostMapping("/online")
    AjaxResult batchSave(List<EsCourse> esCourseList);

    @PostMapping("/offline")
    AjaxResult batchDel(List<EsCourse> esCourseList);

}
