package com.fengrui.hrm.client;

import com.fengrui.hrm.domain.Pager;
import com.fengrui.hrm.query.PagerQuery;
import com.fengrui.hrm.util.AjaxResult;
import com.fengrui.hrm.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class PagerClientHystrixFallbackFactory implements FallbackFactory<PagerClient> {

    @Override
    public PagerClient create(Throwable throwable) {
        return new PagerClient() {
            @Override
            public AjaxResult save(Pager pager) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Pager get(Long id) {
                return null;
            }

            @Override
            public List<Pager> list() {
                return null;
            }

            @Override
            public PageList<Pager> json(PagerQuery query) {
                return null;
            }
        };
    }
}
