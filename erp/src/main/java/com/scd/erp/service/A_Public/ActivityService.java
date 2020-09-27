package com.scd.erp.service.A_Public;

import com.scd.erp.Vo.Person.User;
import com.scd.erp.Vo.Process.Annex;
import com.scd.erp.Vo.Process.AnnexRecode;
import com.scd.erp.utils.extraUtil.BaseResponse;

import java.util.List;
import java.util.Map;

public interface ActivityService {

    BaseResponse<String> applyCountersign(Annex annex, Integer processid, String fileids, User user);

    BaseResponse<List<Annex>> selectAnnex(Integer userid);

    List<Map<String,Object>> getTask(User user);

    void approve(String taskId, AnnexRecode recode, User user);

    void complet(String taskid, AnnexRecode recode, User user);
}
