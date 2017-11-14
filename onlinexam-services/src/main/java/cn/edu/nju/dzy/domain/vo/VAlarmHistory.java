package cn.edu.nju.dzy.domain.vo;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * A AlarmHistory.
 */
@Entity
@Table(name = "v_alarm_history")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class VAlarmHistory implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "brief")
	private String brief;

	@Column(name = "describe")
	private String describe;

	@Column(name = "level")
	private Integer level;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "raise_time")
	private ZonedDateTime raiseTime;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "solve_time")
	private ZonedDateTime solveTime;

	@DateTimeFormat(iso = ISO.DATE_TIME)
	@Column(name = "pi_time")
	private ZonedDateTime piTime;

	@Column(name = "status")
	private Integer status;

	@Column(name = "alarm_value")
	private Double alarmValue;

	@JsonIgnore
	@Column(name = "token")
	private String token;
	
	

	@Column(name = "user_id")
	private Long user_id;
	
	@Column(name = "wxid")
	private String wxid;

	@JsonIgnore
	@Column(name = "metric_id")
	private Long metric;


	@Column(name = "wbs_id")
	private Long wbs; // Device


	@Column(name = "equipment_id")
	private Long equipmentId;

	@Column(name = "equipment_name")
	private String equipment;


	@Column(name = "system_id")
	private Long systemId;

	@JsonIgnore
	@Column(name = "system_name")
	private String system;

	@Column(name = "device_name")
	private String device;

	@Column(name = "dur")
	private Long dur;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}

	public String getWxid() {
		return wxid;
	}

	public void setWxid(String wxid) {
		this.wxid = wxid;
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

	public Long getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(Long equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Long getSystemId() {
		return systemId;
	}

	public void setSystemId(Long systemId) {
		this.systemId = systemId;
	}

	public String getSystem() {
		return system;
	}

	public void setSystem(String system) {
		this.system = system;
	}

	public String getDevice() {
		return device;
	}

	public void setDevice(String device) {
		this.device = device;
	}



	public Double getAlarmValue() {
		return alarmValue;
	}

	public void setAlarmValue(Double alarmValue) {
		this.alarmValue = alarmValue;
	}

	public Long getMetric() {
		return metric;
	}

	public void setMetric(Long metric) {
		this.metric = metric;
	}

	public Long getWbs() {
		return wbs;
	}

	public void setWbs(Long wbs) {
		this.wbs = wbs;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public ZonedDateTime getRaiseTime() {
		return raiseTime;
	}

	public void setRaiseTime(ZonedDateTime raiseTime) {
		this.raiseTime = raiseTime;
	}

	public ZonedDateTime getSolveTime() {
		return solveTime;
	}

	public void setSolveTime(ZonedDateTime solveTime) {
		this.solveTime = solveTime;
	}

	public ZonedDateTime getPiTime() {
		return piTime;
	}

	public void setPiTime(ZonedDateTime piTime) {
		this.piTime = piTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	@JsonIgnore
	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public void setDur(Long dur) {
		this.dur = dur;

	}

	public Long getDur() {
		return dur;

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		VAlarmHistory alarmHistory = (VAlarmHistory) o;
		if (alarmHistory.id == null || id == null) {
			return false;
		}
		return Objects.equals(id, alarmHistory.id);
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(id);
	}

	@Override
	public String toString() {
		return "AlarmHistory{" + "id=" + id + ", raiseTime='" + raiseTime + "'" + ", solveTime='" + solveTime + "'"
				+ ", piTime='" + piTime + "'" + ", status='" + status + "'" + ", token='" + token + "'" + '}';
	}

	public String getEquipment() {
		return equipment;
	}

	public void setEquipment(String equipment) {
		this.equipment = equipment;
	}

}
