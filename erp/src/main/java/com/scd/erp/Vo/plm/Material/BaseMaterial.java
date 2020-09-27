package com.scd.erp.Vo.plm.Material;

import com.scd.erp.Vo.Product.Type;
import com.scd.erp.Vo.plm.Center.MaterialLink;
import com.scd.erp.Vo.plm.Note.Note;
import com.scd.erp.constant.ConstantMaterials;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.List;

@Table(name = "erp_plm_material_base")
public class BaseMaterial {
    @Id
    @Column(name = "baseID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer baseid;

    /**
     * 物料名称
     */
    @Column(name = "nameCn")
    private String namecn;

    /**
     * 英文名
     */
    @Column(name = "nameEn")
    private String nameen;

    /**
     * 简称
     */
    @Column(name = "nameShort")
    private String nameshort;

    /**
     * 客户零件号
     */
    @Column(name = "CustomerPN")
    private String customerpn;

    /**
     * 物料版本
     */
    @Column(name = "Version")
    private String version;

    /**
     * 物料级别
     */
    @Column(name = "Clazz")
    private Integer clazz;

    /**
     * 物料小类
     */
    @Column(name = "SubClass")
    private Integer subclass;

    /**
     * 物料组ID
     */
    @Column(name = "CostGroupID")
    private Integer costgroupid;

    /**
     * 主体材料
     */
    @Column(name = "MainMaterial")
    private String mainmaterial;

    /**
     * 是否标准件
     */
    @Column(name = "Standard")
    private Boolean standard;

    /**
     * 物料关键参数描述
     */
    @Column(name = "Description")
    private String description;

    /**
     * erp编号
     */
    @Column(name = "ERPNUM")
    private String erpnum;

    /**
     * 所属产品线
    */
    @Column(name = "typeID")
    private Integer typeid;

    /**
     * 备注
     */
    private String remark;

    @Column(name = "Iteration")
    private Integer iteration;

    private Crafts crafts;
    private Cycle cycle;
    private Layout layout;
    private Logistic logistic;
    private List<Stock> stocks;
    private Note note;
    private Group group;
    private Type type;

    private int count;
    private List<BaseMaterial> baseMaterials;
    private List<Bom> boms;
    private MaterialLink link;

    public List<Bom> getBoms() {
        return boms;
    }

    public void setBoms(List<Bom> boms) {
        this.boms = boms;
    }

    public Integer getIteration() {
        return iteration;
    }

    public void setIteration(Integer iteration) {
        this.iteration = iteration;
    }

    public MaterialLink getLink() {
        return link;
    }

    public void setLink(MaterialLink link) {
        this.link = link;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BaseMaterial{");
        sb.append("baseid=").append(baseid);
        sb.append(", namecn='").append(namecn).append('\'');
        sb.append(", nameen='").append(nameen).append('\'');
        sb.append(", nameshort='").append(nameshort).append('\'');
        sb.append(", customerpn='").append(customerpn).append('\'');
        sb.append(", version='").append(version).append('\'');
        sb.append(", clazz=").append(clazz);
        sb.append(", subclass=").append(subclass);
        sb.append(", costgroupid=").append(costgroupid);
        sb.append(", mainmaterial='").append(mainmaterial).append('\'');
        sb.append(", standard=").append(standard);
        sb.append(", description='").append(description).append('\'');
        sb.append(", erpnum='").append(erpnum).append('\'');
        sb.append(", typeid=").append(typeid);
        sb.append(", remark='").append(remark).append('\'');
        sb.append(", crafts=").append(crafts);
        sb.append(", cycle=").append(cycle);
        sb.append(", layout=").append(layout);
        sb.append(", logistic=").append(logistic);
        sb.append(", stocks=").append(stocks);
        sb.append(", note=").append(note);
        sb.append(", group=").append(group);
        sb.append(", type=").append(type);
        sb.append(", count=").append(count);
        sb.append(", baseMaterials=").append(baseMaterials);
        sb.append(", typeI='").append(getTypeI()).append('\'');
        sb.append(", groupI='").append(getGroupI()).append('\'');
        sb.append(", clazzI='").append(getClazzI()).append('\'');
        sb.append(", subclassI='").append(getSubclassI()).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (!(o instanceof BaseMaterial)) return false;

        BaseMaterial material = (BaseMaterial) o;

        return new EqualsBuilder()
                .append(getCount(), material.getCount())
                .append(getBaseid(), material.getBaseid())
                .append(getNamecn(), material.getNamecn())
                .append(getNameen(), material.getNameen())
                .append(getNameshort(), material.getNameshort())
                .append(getCustomerpn(), material.getCustomerpn())
                .append(getVersion(), material.getVersion())
                .append(getClazz(), material.getClazz())
                .append(getSubclass(), material.getSubclass())
                .append(getCostgroupid(), material.getCostgroupid())
                .append(getMainmaterial(), material.getMainmaterial())
                .append(getStandard(), material.getStandard())
                .append(getDescription(), material.getDescription())
                .append(getErpnum(), material.getErpnum())
                .append(getTypeid(), material.getTypeid())
                .append(getRemark(), material.getRemark())
                .append(getCrafts(), material.getCrafts())
                .append(getCycle(), material.getCycle())
                .append(getLayout(), material.getLayout())
                .append(getLogistic(), material.getLogistic())
                .append(getStocks(), material.getStocks())
                .append(getNote(), material.getNote())
                .append(getGroup(), material.getGroup())
                .append(getType(), material.getType())
                .append(getBaseMaterials(), material.getBaseMaterials())
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(getBaseid())
                .append(getNamecn())
                .append(getNameen())
                .append(getNameshort())
                .append(getCustomerpn())
                .append(getVersion())
                .append(getClazz())
                .append(getSubclass())
                .append(getCostgroupid())
                .append(getMainmaterial())
                .append(getStandard())
                .append(getDescription())
                .append(getErpnum())
                .append(getTypeid())
                .append(getRemark())
                .append(getCrafts())
                .append(getCycle())
                .append(getLayout())
                .append(getLogistic())
                .append(getStocks())
                .append(getNote())
                .append(getGroup())
                .append(getType())
                .append(getCount())
                .append(getBaseMaterials())
                .toHashCode();
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public List<BaseMaterial> getBaseMaterials() {
        return baseMaterials;
    }

    public void setBaseMaterials(List<BaseMaterial> baseMaterials) {
        this.baseMaterials = baseMaterials;
    }
    public Type getType() {
        return type;
    }
    public String getTypeI() {
        if (null!=type)
            return type.getTypename();
        return null;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Group getGroup() {
        return group;
    }
    public String  getGroupI() {
        if (null != group)
            return group.getGroupname();
        return null;
    }

    public void setGroup(Group group) {
        this.group = group;
    }

    public Cycle getCycle() {
        return cycle;
    }

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
    }

    public Layout getLayout() {
        return layout;
    }

    public void setLayout(Layout layout) {
        this.layout = layout;
    }

    public Logistic getLogistic() {
        return logistic;
    }

    public void setLogistic(Logistic logistic) {
        this.logistic = logistic;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }

    public Note getNote() {
        return note;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public Crafts getCrafts() {
        return crafts;
    }

    public void setCrafts(Crafts crafts) {
        this.crafts = crafts;
    }

    /**
     * @return baseID
     */
    public Integer getBaseid() {
        return baseid;
    }

    /**
     * @param baseid
     */
    public void setBaseid(Integer baseid) {
        this.baseid = baseid;
    }

    /**
     * 获取物料名称
     *
     * @return nameCn - 物料名称
     */
    public String getNamecn() {
        return namecn;
    }

    /**
     * 设置物料名称
     *
     * @param namecn 物料名称
     */
    public void setNamecn(String namecn) {
        this.namecn = namecn == null ? null : namecn.trim();
    }

    /**
     * 获取英文名
     *
     * @return nameEn - 英文名
     */
    public String getNameen() {
        return nameen;
    }

    /**
     * 设置英文名
     *
     * @param nameen 英文名
     */
    public void setNameen(String nameen) {
        this.nameen = nameen == null ? null : nameen.trim();
    }

    /**
     * 获取简称
     *
     * @return nameShort - 简称
     */
    public String getNameshort() {
        return nameshort;
    }

    /**
     * 设置简称
     *
     * @param nameshort 简称
     */
    public void setNameshort(String nameshort) {
        this.nameshort = nameshort == null ? null : nameshort.trim();
    }

    /**
     * 获取客户零件号
     *
     * @return CustomerPN - 客户零件号
     */
    public String getCustomerpn() {
        return customerpn;
    }

    /**
     * 设置客户零件号
     *
     * @param customerpn 客户零件号
     */
    public void setCustomerpn(String customerpn) {
        this.customerpn = customerpn == null ? null : customerpn.trim();
    }

    /**
     * 获取物料版本
     *
     * @return Version - 物料版本
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置物料版本
     *
     * @param version 物料版本
     */
    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    /**
     * 获取物料级别
     *
     * @return Clazz - 物料级别
     */
    public Integer getClazz() {
        return clazz;
    }

    public String getClazzI(){
        if (clazz != null) {
            return ConstantMaterials.MATERIAL_CLAZZ[clazz];
        }else {
            return null;
        }
    }
    /**
     * 设置物料级别
     *
     * @param clazz 物料级别
     */
    public void setClazz(Integer clazz) {
        this.clazz = clazz;
    }

    /**
     * 获取物料小类
     *
     * @return SubClass - 物料小类
     */
    public Integer getSubclass() {
        return subclass;
    }
    public String getSubclassI() {
        if (subclass != null) {
            return ConstantMaterials.MATERIAL_SUBCATEGORY[subclass];
        }else {
            return null;
        }

    }

    /**
     * 设置物料小类
     *
     * @param subclass 物料小类
     */
    public void setSubclass(Integer subclass) {
        this.subclass = subclass;
    }

    /**
     * 获取物料组ID
     *
     * @return CostGroupID - 物料组ID
     */
    public Integer getCostgroupid() {
        return costgroupid;
    }

    /**
     * 设置物料组ID
     *
     * @param costgroupid 物料组ID
     */
    public void setCostgroupid(Integer costgroupid) {
        this.costgroupid = costgroupid;
    }

    /**
     * 获取主体材料
     *
     * @return MainMaterial - 主体材料
     */
    public String getMainmaterial() {
        return mainmaterial;
    }

    /**
     * 设置主体材料
     *
     * @param mainmaterial 主体材料
     */
    public void setMainmaterial(String mainmaterial) {
        this.mainmaterial = mainmaterial == null ? null : mainmaterial.trim();
    }

    /**
     * 获取是否标准件
     *
     * @return Standard - 是否标准件
     */
    public Boolean getStandard() {
        return standard;
    }

    /**
     * 设置是否标准件
     *
     * @param standard 是否标准件
     */
    public void setStandard(Boolean standard) {
        this.standard = standard;
    }

    /**
     * 获取物料关键参数描述
     *
     * @return Description - 物料关键参数描述
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置物料关键参数描述
     *
     * @param description 物料关键参数描述
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * 获取erp编号
     *
     * @return ERPNUM - erp编号
     */
    public String getErpnum() {
        return erpnum;
    }

    /**
     * 设置erp编号
     *
     * @param erpnum erp编号
     */
    public void setErpnum(String erpnum) {
        this.erpnum = erpnum;
    }

    /**
     * @return typeID
     */
    public Integer getTypeid() {
        return typeid;
    }

    /**
     * @param typeid
     */
    public void setTypeid(Integer typeid) {
        this.typeid = typeid;
    }

    /**
     * 获取备注
     *
     * @return remark - 备注
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置备注
     *
     * @param remark 备注
     */
    public void setRemark(String remark) {
        this.remark = remark == null ? null : remark.trim();
    }
}