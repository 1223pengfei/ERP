package com.scd.erp.service.trace.Impl;

import com.scd.erp.Vo.Materail.Materail;
import com.scd.erp.mapper.MaterailMapper;
import com.scd.erp.service.trace.MaterailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.system.ApplicationHome;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Service
public class MaterailServiceImpl implements MaterailService {

    @Autowired
    private MaterailMapper materailMapper;

    @Override
    public List<Materail> getList() {
        return this.materailMapper.selectAll();
    }

    @Override
    public void setMater(Materail materail) {
        Integer id = materail.getMaterialid();
        if (id != null) {
            this.materailMapper.updateByPrimaryKeySelective(materail);
        } else {
            materail.setCreatdate(new Date());
            this.materailMapper.insertSelective(materail);
        }
    }

    @Override
    public Materail getOne(Materail materail) {
        return this.materailMapper.selectByPrimaryKey(materail.getMaterialid());
    }

    @Override
    public boolean del(Materail materail) {
        this.materailMapper.delete(materail);
        return true;
    }


    public List<Materail> carousel() {
        ApplicationHome home = new ApplicationHome(getClass());
        String realPath = home.getDir().getParent() + "/classes/carousel.txt";
        try {
            File f = new File(realPath);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String IDs = br.readLine();
            String[] ids = IDs.split("_");
            List<Materail> list = new LinkedList<>();
            for (String id : ids) {
                Materail materail = this.materailMapper.selectByPrimaryKey(Integer.parseInt(id));
                list.add(materail);
            }
            return list;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
