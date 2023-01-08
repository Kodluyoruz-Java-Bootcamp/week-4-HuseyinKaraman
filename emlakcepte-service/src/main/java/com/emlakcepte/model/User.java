package com.emlakcepte.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.emlakcepte.model.enums.Connectivity;
import com.emlakcepte.model.enums.UserType;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

@Entity
@Table(name = "users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="id")
	private Integer id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "email",unique = true,nullable = false)
	private String email; 
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "userType")
	@Enumerated(EnumType.STRING)
	private UserType type;
	
	@Column(name="connectivity")
	@Enumerated(EnumType.STRING)
	private Connectivity connectivity;
	
	@Column(name="packages")
	private Integer packages;

	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@Column(name = "create_date")
	private LocalDateTime createDate;
	
	@OneToMany(mappedBy = "user",fetch=FetchType.LAZY)
	private List<Realty> realtyList = new ArrayList<Realty>();
	
	@OneToMany(mappedBy = "receiver")
    private List<Message> messagesRecived = new ArrayList<Message>();

    @OneToMany(mappedBy = "sender")
    private List<Message> messagesSended = new ArrayList<Message>();
 
 
	public User() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserType getType() {
		return type;
	}

	public void setType(UserType type) {
		this.type = type;
	}

	public List<Realty> getRealtyList() {
		return realtyList;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public List<Message> getMessagesRecived() {
		return messagesRecived;
	}

	public void setMessagesRecived(List<Message> messagesRecived) {
		this.messagesRecived = messagesRecived;
	}

	public List<Message> getMessagesSended() {
		return messagesSended;
	}

	public void setMessagesSended(List<Message> messagesSended) {
		this.messagesSended = messagesSended;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public Integer getId() {
		return id;
	}
		
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Connectivity getConnectivity() {
		return connectivity;
	}

	public void setConnectivity(Connectivity connectivity) {
		this.connectivity = connectivity;
	}

	public Integer getPackages() {
		return packages;
	}

	public void setPackages(Integer packages) {
		this.packages = packages;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", type=" + type
				+ ", createDate=" + createDate + ", realtyList=" + realtyList + "]";
	}
	


	
}
