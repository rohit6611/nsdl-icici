package com.altruist.nsdl.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user_token_detail")
public class UserTokenDetailModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "uid")
	private String uid;

	@Column(name = "current_date1")
	private Date currentDate1;

	@Column(name = "expire_date")
	private Date expireDate;

	@Column(name = "jwt_token")
	private String jwtToken;

	@Column(name = "jwt_refresh_token")
	private String jwtRefreshToken;

	@Column(name = "refresh_key")
	private String refreshKey;

	@Column(name = "token_key")
	private String tokenKey;

	@Column(name = "expiry_date")
	private Date expiryDate;

	@Column(name = "source")
	private String source;

	@Column(name = "refresh_current_date")
	private Date refreshCurrentDate;

	@Column(name = "refresh_expiry_date")
	private Date refreshExpiryDate;

	@Column(name = "service_id")
	private String serviceId;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Date getCurrentDate1() {
		return currentDate1;
	}

	public void setCurrentDate1(Date currentDate1) {
		this.currentDate1 = currentDate1;
	}

	public Date getExpireDate() {
		return expireDate;
	}

	public void setExpireDate(Date expireDate) {
		this.expireDate = expireDate;
	}

	public String getJwtToken() {
		return jwtToken;
	}

	public void setJwtToken(String jwtToken) {
		this.jwtToken = jwtToken;
	}

	public String getJwtRefreshToken() {
		return jwtRefreshToken;
	}

	public void setJwtRefreshToken(String jwtRefreshToken) {
		this.jwtRefreshToken = jwtRefreshToken;
	}

	public String getRefreshKey() {
		return refreshKey;
	}

	public void setRefreshKey(String refreshKey) {
		this.refreshKey = refreshKey;
	}

	public String getTokenKey() {
		return tokenKey;
	}

	public void setTokenKey(String tokenKey) {
		this.tokenKey = tokenKey;
	}

	public Date getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getSource() {
		return source;
	}

	public void setSource(String source) {
		this.source = source;
	}

	public Date getRefreshCurrentDate() {
		return refreshCurrentDate;
	}

	public void setRefreshCurrentDate(Date refreshCurrentDate) {
		this.refreshCurrentDate = refreshCurrentDate;
	}

	public Date getRefreshExpiryDate() {
		return refreshExpiryDate;
	}

	public void setRefreshExpiryDate(Date refreshExpiryDate) {
		this.refreshExpiryDate = refreshExpiryDate;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	@Override
	public String toString() {
		return "UserTokenDetailModel [id=" + id + ", uid=" + uid + ", currentDate1=" + currentDate1 + ", expireDate="
				+ expireDate + ", jwtToken=" + jwtToken + ", jwtRefreshToken=" + jwtRefreshToken + ", refreshKey="
				+ refreshKey + ", tokenKey=" + tokenKey + ", expiryDate=" + expiryDate + ", source=" + source
				+ ", refreshCurrentDate=" + refreshCurrentDate + ", refreshExpiryDate=" + refreshExpiryDate
				+ ", serviceId=" + serviceId + "]";
	}

}
