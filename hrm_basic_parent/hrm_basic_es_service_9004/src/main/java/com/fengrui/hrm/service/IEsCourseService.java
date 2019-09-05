package com.fengrui.hrm.service;

import com.fengrui.hrm.doc.EsCourse;
import com.fengrui.hrm.query.EsCourseQuery;
import com.fengrui.hrm.util.PageList;

import java.util.List;

public interface IEsCourseService {

    void updateById(EsCourse esCourse);

    void insert(EsCourse esCourse);

    void deleteById(Long id);

    EsCourse selectById(Long id);

    List<EsCourse> selectList(Object o);

    PageList<EsCourse> selectListPage(EsCourseQuery query);

    void batchSave(List<EsCourse> ids);

    void batchDel(List<EsCourse> esCourseList);

}
