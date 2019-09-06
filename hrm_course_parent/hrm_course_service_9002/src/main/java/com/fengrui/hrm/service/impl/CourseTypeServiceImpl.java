package com.fengrui.hrm.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.fengrui.hrm.cache.CourseTypeCache;
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

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class CourseTypeServiceImpl extends ServiceImpl<CourseTypeMapper, CourseType> implements ICourseTypeService {

    @Autowired
    private CourseTypeMapper courseTypeMapper;

    @Autowired
    private CourseTypeCache courseTypeCache;

    @Override
    public PageList<CourseType> selectListPage(CourseTypeQuery query) {
        Page page = new Page(query.getPage(),query.getRows()); //Page
        List<CourseType> courseTypes = courseTypeMapper.loadListPage(page, query);
        return new PageList<>(page.getTotal(),courseTypes);
    }

    @Override
    public List<CourseType> queryTypeTree(Long pid) { // -1

        List<CourseType> courseTypes = courseTypeCache.getCourseTypes();
        if (courseTypes== null || courseTypes.size()<1){
            System.out.println("db...............");
            //递归
            // return getCourseTypesRecursion(pid);
            //循环
            List<CourseType> courseTypesDb = getCourseTypesLoop(pid);
            if (courseTypesDb==null|| courseTypesDb.size()<1)
                courseTypesDb = new ArrayList<>();
            courseTypeCache.setCourseTypes(courseTypesDb);//[]
            return courseTypesDb;
        }

        System.out.println("cache...............");
        return  courseTypes;

    }

    // 方案2: 循环
    private List<CourseType> getCourseTypesLoop(Long pid) { //0
        List<CourseType> result = new ArrayList<>();
        // 查询所有类型
        List<CourseType> allTypes = courseTypeMapper.selectList(null);
        //建立关联关系
        Map<Long,CourseType> allTypesDto = new HashMap<>();
        for (CourseType allType : allTypes) {
            allTypesDto.put(allType.getId(),allType);
        }
        //遍历判断是否是第一级,
        for (CourseType type : allTypes) {
            Long pidTmp = type.getPid();
            if (pidTmp.longValue()== pid.longValue()){
                result.add(type);
            }else{
                //通过map获取
                CourseType parent = allTypesDto.get(pidTmp);
                parent.getChildren().add(type);
            }
        }

        return result;
    }

    //方案1: 递归
    private List<CourseType> getCourseTypesRecursion(Long pid) {
        List<CourseType> children = courseTypeMapper
                .selectList(new EntityWrapper<CourseType>().eq("pid", pid));
        //出口
        if (children==null || children.size()<1)
        {
            return null;
        }
        for (CourseType child : children) {
            List<CourseType> courseTypes = queryTypeTree(child.getId());
            child.setChildren(courseTypes);
        }
        return children;
    }

    @Override
    public boolean insert(CourseType entity) {
        courseTypeMapper.insert(entity);
        List<CourseType> courseTypes = queryTypeTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }

    @Override
    public boolean deleteById(Serializable id) {
        courseTypeMapper.deleteById(id);
        List<CourseType> courseTypes = queryTypeTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }

    @Override
    public boolean updateById(CourseType entity) {
        courseTypeMapper.updateById(entity);
        List<CourseType> courseTypes = queryTypeTree(0L);
        courseTypeCache.setCourseTypes(courseTypes);
        return true;
    }
}
