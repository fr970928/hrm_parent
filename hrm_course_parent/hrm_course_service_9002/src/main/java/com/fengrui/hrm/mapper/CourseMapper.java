package com.fengrui.hrm.mapper;

import com.fengrui.hrm.domain.Course;
import com.fengrui.hrm.query.CourseQuery;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.Page;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface CourseMapper extends BaseMapper<Course> {

    List<Course> loadListPage(Page<Course> page, @Param("query") CourseQuery query);

    //上线
    void batchOnline(List<Long> ids);

    //下线
    void batchOffline(List<Long> ids);

}
