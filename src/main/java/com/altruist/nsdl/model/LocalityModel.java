package com.altruist.nsdl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="icici_locality_list")
public class LocalityModel {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Column(name = "locality_name")
	private String localityName;

	@Column(name = "locality_code")
	private String localityCode;

	@Column(name = "city_code")
	private String cityCode;

	public Long getId() {
		return id;
	}

	public String getLocalityName() {
		return localityName;
	}

	public String getLocalityCode() {
		return localityCode;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setLocalityName(String localityName) {
		this.localityName = localityName;
	}

	public void setLocalityCode(String localityCode) {
		this.localityCode = localityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	@Override
	public String toString() {
		return "LocalityModel [id=" + id + ", localityName=" + localityName + ", localityCode=" + localityCode
				+ ", cityCode=" + cityCode + "]";
	}

}
