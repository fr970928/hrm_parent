package com.fengrui.hrm.service.impl;

import com.fengrui.hrm.doc.EsCourse;
import com.fengrui.hrm.query.EsCourseQuery;
import com.fengrui.hrm.repository.CourseRepository;
import com.fengrui.hrm.service.IEsCourseService;
import com.fengrui.hrm.util.PageList;
import com.sun.org.apache.xpath.internal.operations.Lte;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EsCourseServiceImpl implements IEsCourseService {

    @Autowired
    private CourseRepository repository;

    @Override
    public void updateById(EsCourse esCourse) {
        repository.save(esCourse);
    }

    @Override
    public void insert(EsCourse esCourse) {
        repository.save(esCourse);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public EsCourse selectById(Long id) {
        return repository.findById(id).get();
    }

    @Override
    public List<EsCourse> selectList(Object o) {
        Page page = (Page) repository.findAll();
        return page.getContent();
    }

    @Override
    public PageList<EsCourse> selectListPage(EsCourseQuery query) {
        NativeSearchQueryBuilder builder = new NativeSearchQueryBuilder();
        BoolQueryBuilder bool = QueryBuilders.boolQuery();
        //模糊查询
        bool.must(QueryBuilders.matchQuery("intro", "zhang"));
        //精确查询
        List<QueryBuilder> filter = bool.filter();
        filter.add(QueryBuilders.rangeQuery("age").gte(0).lte(200));

        builder.withQuery(bool);
        //排序
        builder.withSort(SortBuilders.fieldSort("age").order(SortOrder.ASC));
        //分页
        builder.withPageable(PageRequest.of(query.getPage()-1, query.getRows()));
        //构造条件查询
        NativeSearchQuery esquery = builder.build();
        //查询
        Page<EsCourse> search = repository.search(esquery);
        return new PageList<>(search.getTotalElements(), search.getContent());
    }

    @Override
    public void batchSave(List<EsCourse> ids) {
        repository.saveAll(ids);
    }

    @Override
    public void batchDel(List<EsCourse> esCourseList) {
        repository.deleteAll(esCourseList);
    }

}
