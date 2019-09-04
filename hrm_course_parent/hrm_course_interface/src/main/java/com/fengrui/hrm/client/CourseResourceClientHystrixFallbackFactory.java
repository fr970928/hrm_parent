package com.fengrui.hrm.client;

import com.fengrui.hrm.domain.CourseResource;
import com.fengrui.hrm.query.CourseResourceQuery;
import com.fengrui.hrm.util.AjaxResult;
import com.fengrui.hrm.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CourseResourceClientHystrixFallbackFactory implements FallbackFactory<CourseResourceClient> {

    @Override
    public CourseResourceClient create(Throwable throwable) {
        return new CourseResourceClient() {
            @Override
            public AjaxResult save(CourseResource courseResource) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public CourseResource get(Long id) {
                return null;
            }

            @Override
            public List<CourseResource> list() {
                return null;
            }

            @Override
            public PageList<CourseResource> json(CourseResourceQuery query) {
                return null;
            }
        };
    }
}
