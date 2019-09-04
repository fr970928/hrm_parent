package com.fengrui.hrm.mapper;

import com.fengrui.hrm.domain.CourseType;
import com.fengrui.hrm.query.CourseTypeQuery;
import com.fengrui.hrm.util.PageList;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CourseTypeMapper extends BaseMapper<CourseType> {

    /**
     * @param page
     * @param query
     * @return
     */
   List<CourseType> loadListPage(Pagination page,
                                 @Param("query") CourseTypeQuery query);
}
