package com.scd.erp.service.review.Impl;

import com.scd.erp.mapper.FileMapper;
import com.scd.erp.service.review.FileUploadService;
import com.scd.erp.user.review.ContentFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUploadServiceImpl implements FileUploadService {
    @Autowired
    private FileMapper fm;
    @Override
    public void saveFileMsg(ContentFile contentFile) {
        fm.saveFileMsg(contentFile);

    }
}
