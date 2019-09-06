package com.fengrui.hrm.client;

import com.fengrui.hrm.domain.Tenant;
import com.fengrui.hrm.query.TenantQuery;
import com.fengrui.hrm.util.AjaxResult;
import com.fengrui.hrm.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TenantClientHystrixFallbackFactory implements FallbackFactory<TenantClient> {

    @Override
    public TenantClient create(Throwable throwable) {
        return new TenantClient() {
            @Override
            public AjaxResult save(Tenant tenant) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Tenant get(Long id) {
                return null;
            }

            @Override
            public List<Tenant> list() {
                return null;
            }

            @Override
            public PageList<Tenant> json(TenantQuery query) {
                return null;
            }
        };
    }
}
