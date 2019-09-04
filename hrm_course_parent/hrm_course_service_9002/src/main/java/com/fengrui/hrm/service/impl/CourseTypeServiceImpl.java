package com.fengrui.hrm.service.impl;

import com.fengrui.hrm.domain.CourseType;
import com.fengrui.hrm.mapper.CourseTypeMapper;
import com.fengrui.hrm.query.CourseTypeQuery;
import com.fengrui.hrm.service.ICourseTypeService;
import com.fengrui.hrm.util.PageList;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Override
    public PageList<CourseType> selectListPage(CourseTypeQuery query) {
        Page page = new Page(query.getPage(),query.getRows()); //Page
        List<CourseType> courseTypes = courseTypeMapper.loadListPage(page, query);
        return new PageList<>(page.getTotal(),courseTypes);
    }
}
