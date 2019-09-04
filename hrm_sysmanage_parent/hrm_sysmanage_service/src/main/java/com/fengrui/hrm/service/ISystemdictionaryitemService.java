package com.fengrui.hrm.service;

import com.fengrui.hrm.domain.Systemdictionaryitem;
import com.baomidou.mybatisplus.service.IService;

import java.util.List;


public interface ISystemdictionaryitemService extends IService<Systemdictionaryitem> {

    /**
     * 通过parent的sn查询明细
     * @param sn
     * @return
     */
    List<Systemdictionaryitem> listByParentSn(String sn);
}
