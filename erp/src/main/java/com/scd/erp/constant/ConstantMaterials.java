package com.scd.erp.constant;


import com.scd.erp.Vo.commen.Condition;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class ConstantMaterials {
    public static final String[] ANNSTATE = {"待审批","审核中","审批中","通过审核","未通过审核"};
    public static final String[] VERSTATE = {"待审批","通过审批","未通过审批","已作废"};
    //public static final String[] DOCSTATE = {""};

    public static final String[] KEYS = new String[]{"alarm","alarm_*","erpnum_","menu","docnum_","vernum_","docver_"};

    public static final String[] MATERIAL_SUBCATEGORY =
            "A：通过长期使用认可,B：通过试验认可,C：通过初步认可（技术、渠道）,D：临时物料,E：弃用（停产，断货，升级）".split(",");

    public static final String[]  MATERIAL_CLAZZ =
            "电阻,电容,电感,保险,电池,晶体,磁珠,天线,按键,二极管,三极管,驻极体,连接器,存储器,传感器,处理器,电".split(",");

    public static final String[] UNIT = "个,克,包,扎,瓶,箱,米,千,副,捆,条,块,套".split(",");

    public static final String[] MATER_FILE_PHASE=
                    "定稿阶段,NA ,产品策划 ,产品详细定义 ,S1 ,S2 ,S3 ,PPAP ,SOP,结项".split(",");
    public static List<Condition> MaterailConditions,DocmentConditions;
    public static Map MATERIAL_P , MATERIAL_M;
    static {
        MaterailConditions = new LinkedList<>();

        MaterailConditions.add(new Condition("material_namecn","物料名称"));
        MaterailConditions.add(new Condition("material_nameen","英文名"));
        MaterailConditions.add(new Condition("material_nameshort","简称"));
        MaterailConditions.add(new Condition("material_customerpn","客户零件号"));
        MaterailConditions.add(new Condition("material_version","物料版本"));
        MaterailConditions.add(new Condition("material_clazz","物料小类"));
        MaterailConditions.add(new Condition("material_subclass","物料级别"));
        MaterailConditions.add(new Condition("material_costgroupid","物料组"));
        MaterailConditions.add(new Condition("material_mainmaterial","主体材料"));
        MaterailConditions.add(new Condition("material_standard","是否标准件"));
        MaterailConditions.add(new Condition("material_description","关键参数描述"));
        MaterailConditions.add(new Condition("material_typeid","所属产品线ID"));
        MaterailConditions.add(new Condition("material_erpnum","erp编号"));

        MaterailConditions.add(new Condition("layout_automotive","相关技术认证"));
        MaterailConditions.add(new Condition("layout_totalpins","pin脚数量"));
        MaterailConditions.add(new Condition("layout_specialcharacteristics","特殊特性"));
        MaterailConditions.add(new Condition("layout_color","颜色"));
        MaterailConditions.add(new Condition("layout_value","参数值"));
        MaterailConditions.add(new Condition("layout_tolerance","容差"));
        MaterailConditions.add(new Condition("layout_temperature","工作温度范围"));
        MaterailConditions.add(new Condition("layout_voltagerange","电压范围"));
        MaterailConditions.add(new Condition("layout_currentrange","电流范围"));
        MaterailConditions.add(new Condition("layout_weightg","重量"));
        MaterailConditions.add(new Condition("layout_esd","静电等级"));
        MaterailConditions.add(new Condition("layout_fit","失效率"));
        MaterailConditions.add(new Condition("layout_footprint","PBC封装名称"));

        MaterailConditions.add(new Condition("crafts_reflowcycles","耐回流焊次数"));
        MaterailConditions.add(new Condition("crafts_environment","环保标准"));

        MaterailConditions.add(new Condition("cycle_expireduration","保质期"));
        MaterailConditions.add(new Condition("cycle_lifeduration","使用寿命"));
        MaterailConditions.add(new Condition("cycle_liferemaining","剩余寿命"));
        MaterailConditions.add(new Condition("cycle_unitofaccount","计价单位"));

        MaterailConditions.add(new Condition("logistic_packageid","包装形式"));
        MaterailConditions.add(new Condition("logistic_unit","计量单位"));
        MaterailConditions.add(new Condition("logistic_packagenumber","包数量"));
        MaterailConditions.add(new Condition("logistic_supplyform","补给方式 "));
        MaterailConditions.add(new Condition("logistic_batchtrace","批次管理"));
        MaterailConditions.add(new Condition("logistic_sntrace","序列号管理"));
        MaterailConditions.add(new Condition("logistic_stockmin","最小库存"));
        MaterailConditions.add(new Condition("logistic_stockmax","最大库存"));
        MaterailConditions.add(new Condition("logistic_stocktypeid","库存类型"));
        MaterailConditions.add(new Condition("logistic_logisticinplant","发料方式 "));

        MaterailConditions.add(new Condition("stock_manufacturer","制造商"));
        MaterailConditions.add(new Condition("stock_manufactureridentification","制商标识"));
        MaterailConditions.add(new Condition("stock_agent","代理商"));
        MaterailConditions.add(new Condition("stock_agentidentification","代理商标识"));
        MaterailConditions.add(new Condition("stock_manufacturerpartnumber","订购码"));
        MaterailConditions.add(new Condition("stock_orderingperiod","物料订购周期"));
        MaterailConditions.add(new Condition("stock_minpurchasenumber","最小订购数量"));
        MaterailConditions.add(new Condition("stock_priceincludematerial","是否包含原材料"));
        MaterailConditions.add(new Condition("stock_currentprice","现行价格"));
        MaterailConditions.add(new Condition("stock_lta","年降"));
        MaterailConditions.add(new Condition("stock_volumeprice","阶梯价格"));

        MaterailConditions.add(new Condition("note_creatuserid","创建人ID"));
        MaterailConditions.add(new Condition("note_creattime","创建时间"));

        MaterailConditions.add(new Condition("renew_renewuserid","更新人ID"));
        MaterailConditions.add(new Condition("renew_renewtime","更新时间"));
        MaterailConditions.add(new Condition("renew_iterationcode","迭代号"));
        MaterailConditions.add(new Condition("renew_iterationversion","迭代版本号"));

    }
    static {
        MATERIAL_P = new LinkedHashMap();
        MATERIAL_P.put("P0","成品"  );
        MATERIAL_P.put("P1","半成品"  );
        MATERIAL_P.put("P2","电子元器件"  );
        MATERIAL_P.put("P3","结构件"  );
        MATERIAL_P.put("P4","电气件"  );
        MATERIAL_P.put("P5","工艺辅料/原材料"  );
        MATERIAL_P.put("P6","软件"  );
        MATERIAL_P.put("P7","替代料"  );
        MATERIAL_P.put("P8","虚拟料"  );
        MATERIAL_P.put("P9","保留");

        MATERIAL_M = new LinkedHashMap();
        MATERIAL_M.put("M0","总成-模具");
        MATERIAL_M.put("M1","总成-设备");
        MATERIAL_M.put("M2","总成-工装/治具");
        MATERIAL_M.put("M3","总成-检具");
        MATERIAL_M.put("M4","半总成");
        MATERIAL_M.put("M5","结构件");
        MATERIAL_M.put("M6","电气件");
        MATERIAL_M.put("M7","工序辅料");
        MATERIAL_M.put("M8","包装周转物料");
        MATERIAL_M.put("M9","保留");
    }

    static {
        DocmentConditions = new LinkedList<>();
        DocmentConditions.add(new Condition("doc_docname","文档名称"));
        DocmentConditions.add(new Condition("doc_docnum","文档编号"));
        DocmentConditions.add(new Condition("doc_docphase","文档阶段"));
        DocmentConditions.add(new Condition("doc_creatuserid","创建人"));
        DocmentConditions.add(new Condition("doc_createtime","创建时间"));

        DocmentConditions.add(new Condition("ver_versioncode","版本号"));
        DocmentConditions.add(new Condition("ver_setuserid","修改人"));
        DocmentConditions.add(new Condition("ver_updatatime","更新时间"));
        DocmentConditions.add(new Condition("ver_verstate","状态"));
        DocmentConditions.add(new Condition("ver_verite","迭代号"));

      }

}
