package com.fengrui.hrm.web.controller;

import com.fengrui.hrm.doc.EsCourse;
import com.fengrui.hrm.query.EsCourseQuery;
import com.fengrui.hrm.service.IEsCourseService;
import com.fengrui.hrm.util.AjaxResult;
import com.fengrui.hrm.util.PageList;
import org.aspectj.weaver.loadtime.Aj;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@RequestMapping("/esCourse")
public class EsCourseController {

    @Autowired
    public IEsCourseService esCourseService;

    //保存和修改公用的
    @PostMapping("/save")
    public AjaxResult save(@RequestBody EsCourse esCourse){
        try {
            if (esCourse.getId() != null){
                esCourseService.updateById(esCourse);
            }else {
                esCourseService.insert(esCourse);
            }
            return AjaxResult.me();
        } catch (Exception e){
            e.printStackTrace();
            return AjaxResult.me().setMessage("保存对象失败,错误: "+e.getMessage());
        }
    }

    //删除
    @DeleteMapping("/{id}")
    public AjaxResult delete(@PathParam("id") Long id){
        try {
            esCourseService.deleteById(id);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setMessage("删除对象失败,错误: "+e.getMessage());
        }
    }

    //获取用户
    @GetMapping("/{id}")
    public EsCourse get(@PathParam("id") Long id){
        return esCourseService.selectById(id);
    }

    //获取所有用户
    @GetMapping("/list")
    public List<EsCourse> list(){
        return esCourseService.selectList(null);
    }

    //分页查询
    @PostMapping("/json")
    public PageList<EsCourse> json(@RequestBody EsCourseQuery query){
        return esCourseService.selectListPage(query);
    }

    //上线
    @PostMapping("/online")
    AjaxResult batchSave(@RequestBody List<EsCourse> esCourses){
        try {
            esCourseService.batchSave(esCourses);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("批量添加失败,错误: "+e.getMessage());
        }
    }

    //下线
    @PostMapping("/offline")
    AjaxResult batchDel(@RequestBody List<EsCourse> esCourses){
        try {
            esCourseService.batchDel(esCourses);
            return AjaxResult.me();
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.me().setSuccess(false).setMessage("批量删除失败,错误: "+e.getMessage());
        }
    }
}
