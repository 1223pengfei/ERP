package com.scd.erp.service.APQP;

import com.scd.erp.user.APQP.APQPFile;
import com.scd.erp.user.APQP.APQPFmea;
import org.springframework.data.relational.core.sql.In;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface APQPService {
    int insertApqpMsg(APQPFmea apqpFmea, String ids, String picname) throws IOException;

    List<APQPFmea> selectAPQPMsg(Integer id);

    int updateAPQPMsg(APQPFmea apqpFmea, Integer fid, String picname, HttpServletResponse response) throws IOException;

    List<APQPFile> selectAPQPFileMsg(Integer id);
}
