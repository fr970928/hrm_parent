package com.fengrui.hrm.service.impl;

import com.fengrui.hrm.client.EsCourseClient;
import com.fengrui.hrm.doc.EsCourse;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class CourseServiceImpl extends ServiceImpl<CourseMapper, Course> implements ICourseService {

    @Autowired
    private CourseMapper mapper;
    @Autowired
    private CourseDetailMapper detailMapper;
    @Autowired
    private EsCourseClient esCourseClient

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

    @Override
    public void onLine(Long[] ids) {
        //在索引库批量添加数据
        List<Course> courseList = mapper.selectBatchIds(Arrays.asList(ids));
        List<EsCourse> esCourseList = courseList2EsCourse(courseList);
        esCourseClient.batchSave(esCourseList);
        //批量修改状态
        mapper.batchOnline(Arrays.asList(ids));
    }

    @Override
    public void offLine(Long[] ids) {
        List<Course> courseList = mapper.selectBatchIds(Arrays.asList(ids));
        List<EsCourse> esCourseList = courseList2EsCourse(courseList);
        esCourseClient.batchDel(esCourseList);
        //批量修改状态
        mapper.batchOffline(Arrays.asList(ids));
    }

    private List<EsCourse> courseList2EsCourse(List<Course> courseList) {
        List<EsCourse> result = new ArrayList<>();
        for (Course course : courseList) {
            result.add(course2EsCourse(course));
        }
        return result;
    }

    private EsCourse course2EsCourse(Course course) {
        EsCourse  result = new EsCourse();
        result.setId(course.getId());
        result.setName(course.getName());
        result.setUsers(course.getUsers());
        result.setCourseTypeId(course.getCourseTypeId());
        //type-同库
        if (course.getCourseType() != null)
            result.setCourseTypeName(course.getCourseType().getName());
        //跨服务操作
        result.setGradeId(course.getGrade());
        result.setGradeName(null);
        result.setStatus(course.getStatus());
        result.setTenantId(course.getTenantId());
        result.setTenantName(course.getTenantName());
        result.setUserId(course.getUserId());
        result.setUserName(course.getUserName());
        result.setStartTime(course.getStartTime());
        result.setEndTime(course.getEndTime());
        //Detail
        result.setIntro(null);
        //resource
        result.setResources(null);
        //market
        result.setExpires(null);
        result.setPrice(null);
        result.setPriceOld(null);
        result.setQq(null);
        return result;
    }

    @Override
    public boolean deleteById(Serializable id) {
        //删除数据库
        mapper.deleteById(id);
        //判断状态-还要删除索引库
        Course course = mapper.selectById(id);
        if (  course.getStatus() ==1)
            esCourseClient.delete(Integer.valueOf(id.toString()));
        return true;

    }

    @Override
    public boolean updateById(Course entity) {
        //修改数据库
        mapper.updateById(entity);
        //判断状态-还要修改索引库
        Course course = mapper.selectById(entity.getId());
        if ( course.getStatus() ==1)
            esCourseClient.save(course2EsCourse(entity));
        return true;
    }
}
