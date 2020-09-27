package com.scd.erp.service.APQP.impl;

import com.scd.erp.mapper.APQPFileMapper;
import com.scd.erp.service.APQP.APQPFileService;
import com.scd.erp.user.APQP.APQPFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class APQPFileServiceImpl implements APQPFileService {
    @Autowired
    private APQPFileMapper apqpFileMapper;
    @Override
    public void saveFileMsg(APQPFile apqpFile) {
        apqpFileMapper.saveFileMsg(apqpFile);
    }
}
