package cn.edu.nju.dzy.web.rest.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;


/**
 * A DTO for the ChemMetric entity.
 */
public class ChemMetricDTO implements Serializable {

    private Long id;

    private Integer mtype;


    private String name;


    private Integer attr;


    private Integer alarmType;


    private String brief;


    private String describe;


    private Integer level;


    private ZonedDateTime raiseTime;


    private ZonedDateTime solvedTime;


    private Integer status;


    private String tags;


    private Integer tenantId;


    private Long obsId;

    private String obsName;

    private Long wbsId;

    private String wbsName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public Integer getMtype() {
        return mtype;
    }

    public void setMtype(Integer mtype) {
        this.mtype = mtype;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public Integer getAttr() {
        return attr;
    }

    public void setAttr(Integer attr) {
        this.attr = attr;
    }
    public Integer getAlarmType() {
        return alarmType;
    }

    public void setAlarmType(Integer alarmType) {
        this.alarmType = alarmType;
    }
    public String getBrief() {
        return brief;
    }

    public void setBrief(String brief) {
        this.brief = brief;
    }
    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
    public ZonedDateTime getRaiseTime() {
        return raiseTime;
    }

    public void setRaiseTime(ZonedDateTime raiseTime) {
        this.raiseTime = raiseTime;
    }
    public ZonedDateTime getSolvedTime() {
        return solvedTime;
    }

    public void setSolvedTime(ZonedDateTime solvedTime) {
        this.solvedTime = solvedTime;
    }
    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }
    public Integer getTenantId() {
        return tenantId;
    }

    public void setTenantId(Integer tenantId) {
        this.tenantId = tenantId;
    }

    public Long getObsId() {
        return obsId;
    }

    public void setObsId(Long piGroupId) {
        this.obsId = piGroupId;
    }

    public String getObsName() {
        return obsName;
    }

    public void setObsName(String piGroupName) {
        this.obsName = piGroupName;
    }

    public Long getWbsId() {
        return wbsId;
    }

    public void setWbsId(Long piGroupId) {
        this.wbsId = piGroupId;
    }

    public String getWbsName() {
        return wbsName;
    }

    public void setWbsName(String piGroupName) {
        this.wbsName = piGroupName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ChemMetricDTO chemMetricDTO = (ChemMetricDTO) o;

        if ( ! Objects.equals(id, chemMetricDTO.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "ChemMetricDTO{" +
            "id=" + id +
            ", mtype='" + mtype + "'" +
            ", name='" + name + "'" +
            ", attr='" + attr + "'" +
            ", alarmType='" + alarmType + "'" +
            ", brief='" + brief + "'" +
            ", describe='" + describe + "'" +
            ", level='" + level + "'" +
            ", raiseTime='" + raiseTime + "'" +
            ", solvedTime='" + solvedTime + "'" +
            ", status='" + status + "'" +
            ", tags='" + tags + "'" +
            ", tenantId='" + tenantId + "'" +
            '}';
    }
}
