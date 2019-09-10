package com.fengrui.hrm.client;

import com.fengrui.hrm.domain.Site;
import com.fengrui.hrm.query.SiteQuery;
import com.fengrui.hrm.util.AjaxResult;
import com.fengrui.hrm.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SiteClientHystrixFallbackFactory implements FallbackFactory<SiteClient> {

    @Override
    public SiteClient create(Throwable throwable) {
        return new SiteClient() {
            @Override
            public AjaxResult save(Site site) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Site get(Long id) {
                return null;
            }

            @Override
            public List<Site> list() {
                return null;
            }

            @Override
            public PageList<Site> json(SiteQuery query) {
                return null;
            }
        };
    }
}
