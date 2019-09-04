package com.fengrui.hrm.service;

import com.fengrui.hrm.domain.CourseType;
import com.fengrui.hrm.query.CourseTypeQuery;
import com.fengrui.hrm.util.PageList;
import com.baomidou.mybatisplus.service.IService;


public interface ICourseTypeService extends IService<CourseType> {

    /**
     * 高级查询+分页+关联查询
     * @param query
     * @return
     */
    PageList<CourseType> selectListPage(CourseTypeQuery query);
}
