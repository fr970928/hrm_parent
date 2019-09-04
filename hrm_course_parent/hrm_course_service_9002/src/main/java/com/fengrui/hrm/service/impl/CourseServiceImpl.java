package com.fengrui.hrm.service.impl;

import com.fengrui.hrm.domain.Course;
import com.fengrui.hrm.domain.CourseDetail;
import com.fengrui.hrm.mapper.CourseDetailMapper;
import com.fengrui.hrm.mapper.CourseMapper;
import com.fengrui.hrm.query.CourseQuery;
import com.fengrui.hrm.service.ICourseService;
import com.fengrui.hrm.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper mapper;
    @Autowired
    private CourseDetailMapper detailMapper;
    @Override
    public PageList<Course> selectListPage(CourseQuery query) {
        Page<Course> page = new Page<>(query.getPage(),query.getRows());
        List<Course> rows =  mapper.loadListPage(page,query);
        return new PageList<>(page.getTotal(),rows);
    }

    @Override
    public boolean insert(Course entity) {
        //课程表
        entity.setStatus(0); // tenantId tenantName userId userName
        mapper.insert(entity);
        //课程详情
        entity.getDetail().setCourseId(entity.getId());
        detailMapper.insert(entity.getDetail());
        return true;
    }
}
