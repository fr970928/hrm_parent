package com.fengrui.hrm.client;

import com.fengrui.hrm.domain.Systemdictionary;
import com.fengrui.hrm.query.SystemdictionaryQuery;
import com.fengrui.hrm.util.AjaxResult;
import com.fengrui.hrm.util.PageList;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class SystemdictionaryClientHystrixFallbackFactory implements FallbackFactory<SystemdictionaryClient> {

    @Override
    public SystemdictionaryClient create(Throwable throwable) {
        return new SystemdictionaryClient() {
            @Override
            public AjaxResult save(Systemdictionary systemdictionary) {
                return null;
            }

            @Override
            public AjaxResult delete(Integer id) {
                return null;
            }

            @Override
            public Systemdictionary get(Long id) {
                return null;
            }

            @Override
            public List<Systemdictionary> list() {
                return null;
            }

            @Override
            public PageList<Systemdictionary> json(SystemdictionaryQuery query) {
                return null;
            }
        };
    }
}
