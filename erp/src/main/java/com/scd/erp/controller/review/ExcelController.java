package com.scd.erp.controller.review;

import com.scd.erp.service.review.ExcelService;
import com.scd.erp.service.review.ReviewService;
import com.scd.erp.user.review.Content;
import com.scd.erp.user.review.Items;
import com.scd.erp.user.review.Record;
import com.scd.erp.utils.extraUtil.LoginUtile;
import org.apache.commons.io.FilenameUtils;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.util.ZipSecureFile;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.List;

@RestController
@RequestMapping("excel/")
public class ExcelController {

    private final ReviewService rs;

    private LoginUtile lu;

    private final ExcelService excelService;

    @Autowired
    public ExcelController(ReviewService rs,ExcelService excelService) {
        this.lu = new LoginUtile();
        this.rs = rs;
        this.excelService=excelService;
    }

    /*
     *导出用户信息
     * 2018/10/30+
     * dayue
     */
    @RequestMapping(value = "/export",method = RequestMethod.GET)
    public void downloadAllClassmate(Integer pid, Integer rid, HttpSession session, HttpServletResponse response) throws IOException {
        ClassPathResource cpr = new ClassPathResource("/template/"+"record.xlsx");
        String filename = cpr.getFilename();

        String extension = FilenameUtils.getExtension(filename);
        InputStream is = cpr.getInputStream();
        Workbook workbook ;
        if ("xls".equals(extension)) {
            workbook = new HSSFWorkbook(is);
        } else if ("xlsx".equals(extension)) {
            workbook = new XSSFWorkbook(is);
        } else {
            // 无效后缀名称，这里之能保证excel的后缀名称，不能保证文件类型正确，不过没关系，在创建Workbook的时候会校验文件格式
            throw new IllegalArgumentException("Invalid excel version");
        }
        Sheet sheet = workbook.getSheetAt(0);

        Integer personID = lu.getUser(session).getUserid();
        //查询出的数据
        List<Record> classmateList = rs.selectRecordMg(rid,personID,pid);

        //新增数据行，并且设置单元格数据
        //查询出来的数据可能为null这种看着很不舒服  将null变为空
        String fileName = null;
        //在表中存放查询到的数据放入对应的列
        for (Record record : classmateList) {
            fileName =  record.getPname()+"评审表" ;//设置要导出的文件的名字
            Row row2 = sheet.getRow( 1 );
            row2.getCell(1).setCellValue(record.getDocumentno());
            row2.getCell(3).setCellValue(record.getEdition());
            Row row3 = sheet.getRow( 2 );
            row3.getCell(1).setCellValue(record.getPname());
            row3.getCell(3).setCellValue(record.getModel());
            Row row4 = sheet.getRow( 3);
            row4.getCell(1).setCellValue(record.getcustname());
            row4.getCell(3).setCellValue(record.getCarmodel());
            Row row5 = sheet.getRow( 4 );
            row5.getCell(1).setCellValue(record.getHost());
            row5.getCell(3).setCellValue(record.getStarttime());
            Row row6 = sheet.getRow( 5 );
            Row row7 = sheet.getRow( 6);
            Row row8 = sheet.getRow( 7);
            Row row9 = sheet.getRow( 8);
            List<Items> item = record.getItem();
            for (Items it:item) {
                if(it.getIid()==1){
                    row6.getCell(1).setCellValue(it.getItems());
                    if(it.getIid()!=1){
                        row6.removeCell(row6.getCell(1));
                    }
                }else if(it.getIid()==2){
                    row6.getCell(2).setCellValue(it.getItems());
                }else if(it.getIid()==3){
                    row6.getCell(3).setCellValue(it.getItems());
                }else if(it.getIid()==4){
                    row7.getCell(1).setCellValue(it.getItems());
                }else if(it.getIid()==5){
                    row7.getCell(2).setCellValue(it.getItems());
                }else if(it.getIid()==6){
                    row7.getCell(3).setCellValue(it.getItems());
                }else if(it.getIid()==7){
                    row8.getCell(1).setCellValue(it.getItems());
                }else if(it.getIid()==8){
                    row8.getCell(2).setCellValue(it.getItems());
                }else if(it.getIid()==9){
                    row8.getCell(3).setCellValue(it.getItems());
                }else if(it.getIid()==10){
                    row9.getCell(1).setCellValue(it.getItems());
                }else if(it.getIid()==11){
                    row9.getCell(2).setCellValue(it.getItems());
                }else if(it.getIid()==12){
                    row9.getCell(3).setCellValue(it.getItems());
                }

            }
            Row row10 = sheet.getRow( 9);
            Content contents = record.getContents();
            row10.getCell(1).setCellValue(contents.getContent());
            Row row11 = sheet.getRow( 10);
            String notes = contents.getNotes();
            String[] split = notes.split("###");
            String newNotes ="";
            for (int i=0;i<split.length;i++){
                newNotes+= split[i]+"\n";
            }
            row11.getCell(1).setCellValue(newNotes);
            Row row12 = sheet.getRow( 18);
            row12.getCell(1).setCellValue(contents.getConclusion());
            Row row13 = sheet.getRow( 19);
            row13.getCell(1).setCellValue(contents.getRemarks());
            Row row14 = sheet.getRow( 20);
            String sign = contents.getSign();
            String[] split1 = sign.split("###");
            String newSign ="";
            for (int i=0;i<split1.length;i++){
                newSign+= split1[i]+"        ";
            }
            row14.getCell(1).getCellStyle().setAlignment(CellStyle.ALIGN_CENTER);
            row14.getCell(1).getCellStyle().setAlignment(CellStyle.VERTICAL_CENTER);
            row14.getCell(1).setCellValue(newSign);


        }
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/octet-stream");
        response.setContentType("application/vnd.ms-excel");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName + ".xlsx", "UTF-8"));
        OutputStream outputStream =response.getOutputStream();
        workbook.write(outputStream);
        outputStream.flush();
        outputStream.close();
    }


}