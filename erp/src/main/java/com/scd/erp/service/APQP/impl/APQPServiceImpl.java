package com.scd.erp.service.APQP.impl;

import com.scd.erp.mapper.APQPFileMapper;
import com.scd.erp.mapper.APQPMapper;
import com.scd.erp.service.APQP.APQPService;
import com.scd.erp.user.APQP.APQPFile;
import com.scd.erp.user.APQP.APQPFmea;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Drawing;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.List;

@Service
public class APQPServiceImpl implements APQPService {
    @Autowired
    private APQPMapper apqpMapper;
    @Autowired
    private APQPFileMapper apqpFileMapper;
    @Override
    public int insertApqpMsg(APQPFmea apqpFmea,String ids,String picname) throws IOException {
        if(apqpFmea!=null){
            int b = apqpMapper.insertAPQPFmea(apqpFmea);
            Integer fmeaId = apqpFmea.getId();
            apqpFmea.setId(null);
            System.out.println(apqpFmea.getId());
            if((ids != null) & !ids.equals("")){
                String[] id = ids.split(",");
                for (int i=0;i<id.length;i++){
                    int fid = Integer.valueOf(id[i]);
                    writePic(fid ,apqpFmea,picname);
                    apqpMapper.saveAPQPFileID(fmeaId,fid);
                }

            }
            return b;
        }
        return 0;
    }

    @Override
    public List<APQPFmea> selectAPQPMsg(Integer id) {
        return apqpMapper.selectAPQPMsg(id);
    }

    @Override
    public int updateAPQPMsg(APQPFmea apqpFmea, Integer fid,String picname, HttpServletResponse response) throws IOException {
        APQPFmea apqpFmea1 = apqpMapper.selectByPrimaryKey(apqpFmea.getId());
        String opinion = apqpFmea1.getOpinion();
        if(opinion!=null & "".equals(opinion)){
            apqpFmea.setOpinion(opinion+"####"+apqpFmea.getOpinion());
        }else{
            apqpFmea.setOpinion(opinion);
        }
        writePic(fid ,apqpFmea,picname);

        return apqpMapper.updateByPrimaryKey(apqpFmea);
    }

    @Override
    public List<APQPFile> selectAPQPFileMsg(Integer id) {
        return apqpFileMapper.select(id);
    }

    private void writePic(Integer fid,APQPFmea apqpFmea,String picname) throws IOException {
        FileOutputStream fileOut = null;
        ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
        //String url= "D:\\xiangmu\\works\\gitwork\\erp\\target\\classes\\static\\APQP\\"+picname+".jpg";
        String url= "C:\\erp\\static\\image\\"+picname+".jpg";
        BufferedImage _img = ImageIO.read(new File(url));
        BufferedImage bufferImg= new BufferedImage(_img.getWidth(),_img.getHeight(),BufferedImage.TYPE_INT_RGB);
        bufferImg.getGraphics().drawImage(_img, 0, 0, null);
        //BufferedImage bufferImg = ImageIO.read(new File("C:\\erp\\static\\APQP\\1.jpg"));

        ImageIO.write(bufferImg, "jpg", byteArrayOut);
        APQPFile docement = apqpFileMapper.selectByID(fid);
        //String filePath = ResourceUtils.getURL("target/classes/static").getPath();
        String filePath = ResourceUtils.getURL("static").getPath();
        File file = new File(filePath+docement.getApqp_file_url());

        FileInputStream is = new FileInputStream(file);
        ClassPathResource cpr = new ClassPathResource(filePath+docement.getApqp_file_url());
        String filename = cpr.getFilename();

        String extension = FilenameUtils.getExtension(filename);
        Workbook workbook ;
        if (extension.equals("xls")) {
            workbook = new HSSFWorkbook(is);
        } else if ("xlsx".equals(extension)) {
            workbook = new XSSFWorkbook(is);
        } else {
            //无效后缀名称，这里之能保证excel的后缀名称，不能保证文件类型正确，不过没关系，在创建Workbook的时候会校验文件格式
            throw new IllegalArgumentException("Invalid excel version");
        }
        Sheet sheet = workbook.getSheetAt(0);
        //画图的顶级管理器，一个sheet只能获取一个（一定要注意这点）
        Drawing patriarch = sheet.getDrawingPatriarch();
        
        if (apqpFmea.getId()!=null){
            APQPFmea apqpFmea1 = apqpMapper.selectByPrimaryKey(apqpFmea.getId());
            if("".equals(apqpFmea1.getOrganization()) & !apqpFmea.getOrganization().equals("")){
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(0,  0, 255, 255, (short) 1,  36, (short) 2, 37);
                //注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）　
                anchor.setAnchorType(3);
                //插入图片
                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                fileOut = new FileOutputStream(filePath+docement.getApqp_file_url());
            } if ("".equals(apqpFmea1.getExamine()) & !apqpFmea.getExamine().equals("")){
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 3,  36, (short) 4, 37);
                //注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）　
                anchor.setAnchorType(3);
                //插入图片
                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                fileOut = new FileOutputStream(filePath+docement.getApqp_file_url());
            }if ("".equals(apqpFmea1.getStandardization()) & !apqpFmea.getStandardization().equals("")){
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 7,  36, (short) 9, 37);
                //注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）　
                anchor.setAnchorType(3);
                //插入图片
                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                fileOut = new FileOutputStream(filePath+docement.getApqp_file_url());
            }if ("".equals(apqpFmea1.getApproval()) & !apqpFmea.getApproval().equals("")){
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 11,  36, (short) 15, 37);
                //注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）　
                anchor.setAnchorType(3);
                //插入图片
                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                fileOut = new FileOutputStream(filePath+docement.getApqp_file_url());
            }
        }else if(apqpFmea.getId()==null){
            if(apqpFmea.getOrganization()!=null & !"".equals(apqpFmea.getOrganization())){
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 1,  36, (short) 2, 37);
                //注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）　
                anchor.setAnchorType(3);
                //插入图片
                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                fileOut = new FileOutputStream(filePath+docement.getApqp_file_url());
            } if (apqpFmea.getExamine()!=null & !"".equals(apqpFmea.getExamine())){
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 3,  36, (short) 4, 37);
                //注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）　
                anchor.setAnchorType(3);
                //插入图片
                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                fileOut = new FileOutputStream(filePath+docement.getApqp_file_url());
            }if (apqpFmea.getStandardization()!=null & !"".equals(apqpFmea.getStandardization())){
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 7,  36, (short) 9, 37);
                //注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）　
                anchor.setAnchorType(3);
                //插入图片
                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                fileOut = new FileOutputStream(filePath+docement.getApqp_file_url());
            }if (apqpFmea.getApproval()!=null & !"".equals(apqpFmea.getApproval())){
                //anchor主要用于设置图片的属性
                HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 255, 255, (short) 11,  36, (short) 15, 37);
                //注意：这个方法在新版本的POI中参数类型改成了（AnchorType anchorType）　
                anchor.setAnchorType(3);
                //插入图片
                patriarch.createPicture(anchor, workbook.addPicture(byteArrayOut.toByteArray(), HSSFWorkbook.PICTURE_TYPE_JPEG));
                fileOut = new FileOutputStream(filePath+docement.getApqp_file_url());
            }
        }

        if (file.exists()) {//文件是否存在
            file.delete();
        }
        workbook.write(fileOut);
        fileOut.close();
    }
}
