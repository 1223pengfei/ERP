package com.scd.erp.service.review.Impl;

import com.scd.erp.mapper.ContentMapper;
import com.scd.erp.mapper.FileMapper;
import com.scd.erp.mapper.RecordMapper;
import com.scd.erp.service.review.ReviewService;
import com.scd.erp.user.review.Content;
import com.scd.erp.user.review.Record;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final RecordMapper rm;

    private final ContentMapper cm;

    private final FileMapper fm;

    @Autowired
    public ReviewServiceImpl(RecordMapper rm, ContentMapper cm,FileMapper fm) {
        this.rm = rm;
        this.cm = cm;
        this.fm = fm;
    }

    @Override
    public Map addReviewMsg(Content contents, String ids, Record record, String personIDs,String rfids) {
        //Integer recordid = rm.selectRid(record.getPname());
        Map map = new HashMap();
        Integer recordid = record.getRid();
        if (recordid == -1) {
            rm.addRecordMsg(record);
            int rid = record.getRid();
            if(personIDs!=null & !personIDs.equals("")){
                String[] PID = personIDs.split(",");
                for (int i=0;i<PID.length;i++){
                    System.out.println("交换机的时间="+PID[i ]);
                    int personID = Integer.valueOf(PID[i]);
                    rm.saveID(rid, personID);
                }
            }
            if ( ids.length()!=0) {
                String[] id = ids.split(",");
                for (int i=0;i<id.length;i++){
                    int iid = Integer.valueOf(id[i]);
                    rm.saveRecordItemsID(rid, iid);
                }
            }
            contents.setRid(rid);
            cm.saveContent(contents);
            int tid = contents.getTid();
            String[] rfid = rfids.split(",");
            for (int i=0;i<rfid.length;i++){
                int fid = Integer.valueOf(rfid[i]);
                fm.saveContentIDs(tid,fid);
            }
            map.put("rid",rid);
            return map;

        }else{
            rm.updateRecordMsg(record);
            if (personIDs!=null & !personIDs.equals("")) {
                String[] PID = personIDs.split(",");
                for (int i = 0; i < PID.length; i++) {
                    System.out.println("交换机的时间=" + PID[i]);
                    int personID = Integer.valueOf(PID[i]);
                    rm.deleteID(recordid, personID);
                    rm.saveID(recordid, personID);
                }
            }
            if (ids!=null) {
                String[] id = ids.split(",");
                for (int i=0;i<id.length;i++){
                    int iid = Integer.valueOf(id[i]);
                    rm.delRecordItemsID(recordid, iid);
                    rm.saveRecordItemsID(recordid, iid);
                }
            }
            if(contents!=null){
                contents.setRid(recordid);
                cm.updateContent(contents);
                if(!(rfids == null)){
                    int tid = contents.getTid();
                    String[] rfid = rfids.split(",");
                    for (int i=0;i<rfid.length;i++){
                        int fid = Integer.valueOf(rfid[i]);
                        fm.saveContentIDs(tid,fid);
                    }
                }

            }else{
                contents.setRid(recordid);
                cm.saveContent(contents);
                if(null != rfids){
                    int tid = contents.getTid();
                    String[] rfid = rfids.split(",");
                    for (int i=0;i<rfid.length;i++){
                        int fid = Integer.valueOf(rfid[i]);
                        fm.saveContentIDs(tid,fid);
                    }
                }
            }
            map .put("rid",recordid);
            return map;
        }
    }

    @Override
    public List<Record> selectRecordMg(Integer personID, Integer rid,Integer pid) {
        return rm.selectRecordMg(rid,personID,pid);
    }

    @Override
    public Boolean deleteRecordMg(Integer rid,String rfids) {
        if(rid!=null & !rid.equals("")){
            int a = rm.deleteByPrimaryKey(rid);
            int b = cm.deleteReviewContent(rid);
            int c = rm.deletePersonReviewRecord(rid);
            int d = rm.deleteProjectRecordItem(rid);
            if(rfids!=null & !rfids.equals("")){
                String[] fids = rfids.split(",");
                for (int i=0;i<fids.length;i++){
                    int rfid = Integer.valueOf(fids[i]);
                    fm.deleteByPrimaryKey(rfid);
                }

            }

        }

        return null;
    }

    @Override
    public Content addContentMsg(Content contents) {
        Content content = cm.selectContentByRid(contents.getRid());
        String notes ;
        String sign ;
        if(content!=null){
            notes=content.getNotes()+"###"+contents.getNotes();
            sign = content.getSign()+"###" +contents.getSign();
            contents.setConclusion(content.getConclusion());
            contents.setContent(content.getContent());
            contents.setRemarks(content.getRemarks());
            contents.setNotes(notes);
            contents.setSign(sign);

            cm.updateContent(contents);
        }
        return cm.selectContentByRid(contents.getRid());
    }
}
