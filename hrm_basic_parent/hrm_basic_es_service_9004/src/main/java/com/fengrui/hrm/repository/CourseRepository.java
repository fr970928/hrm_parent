package com.fengrui.hrm.repository;


import com.fengrui.hrm.doc.EsCourse;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface CourseRepository extends ElasticsearchRepository<EsCourse, Long> {

}
