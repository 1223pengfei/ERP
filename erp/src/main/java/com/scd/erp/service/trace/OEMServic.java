package com.scd.erp.service.trace;

import com.scd.erp.Vo.Alarm.OEM;

import java.util.Map;

public interface OEMServic {
    Map getList(Integer row, Integer page);

    void set(OEM oem);
}
