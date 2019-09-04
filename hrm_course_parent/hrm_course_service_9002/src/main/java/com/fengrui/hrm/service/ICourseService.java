package com.fengrui.hrm.service;

import com.fengrui.hrm.domain.Course;
import com.fengrui.hrm.query.CourseQuery;
import com.fengrui.hrm.util.PageList;
import com.baomidou.mybatisplus.service.IService;


public interface ICourseService extends IService<Course> {

    /**
     * 分页+高级查询+关联查询
     * @param query
     * @return
     */
    PageList<Course> selectListPage(CourseQuery query);
}
