package com.emlakcepte.model;

import java.time.LocalDateTime;

import com.emlakcepte.model.enums.SalesType;
import com.emlakcepte.model.enums.PropertyType;
import com.emlakcepte.model.enums.RealtyType;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "realty")
public class Realty {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Integer id;

	@Column(name = "no")
	private Integer no;

	@Column(name = "title")
	private String title;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "create_date")
	private LocalDateTime createDate;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "due_date")
	private LocalDateTime dueDate;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "Object_id")
	private User user;

	@Column(name = "user_id")
	private Integer userId;
	@Column(name = "province")
	private String province;
	@Column(name = "district")
	private String district;
	@Column(name = "status")
	@Enumerated(EnumType.STRING)
	private RealtyType status;
	@Column(name = "category_type")
	@Enumerated(EnumType.STRING)
	private SalesType categoryType;
	@Column(name = "property_type")
	@Enumerated(EnumType.STRING)
	private PropertyType propertyType;

	public Realty() {
		this.createDate = LocalDateTime.now();
		this.dueDate = LocalDateTime.now().plusDays(30);
	}

	public Integer getNo() {
		return no;
	}

	public void setNo(Integer no) {
		this.no = no;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@JsonIgnore
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Temporal(TemporalType.TIMESTAMP)
	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDateTime dueDate) {
		this.dueDate = dueDate;
	}

	public RealtyType getStatus() {
		return status;
	}

	public void setStatus(RealtyType status) {
		this.status = status;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public SalesType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(SalesType categoryType) {
		this.categoryType = categoryType;
	}

	public PropertyType getPropertyType() {
		return propertyType;
	}

	public void setPropertyType(PropertyType propertyType) {
		this.propertyType = propertyType;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	@Override
	public String toString() {
		return "Realty [no=" + id + ", title=" + title + ", status=" + status + ", province=" + province + ", district="
				+ district + "]";
	}

}
