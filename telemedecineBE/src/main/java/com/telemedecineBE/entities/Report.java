package com.telemedecineBE.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "REPORT")
@AllArgsConstructor
public class Report implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
	private String name;
	private String type;
	private String url;
	private long size;
	@JsonIgnore
	@Lob
	private byte[] data;
	@ElementCollection
	private List<Integer> userIds = new ArrayList<>();


	public Report() { super(); }

	public Report(String name, String type, byte[] data){
		this.name = name;
		this.type = type;
		this.data = data;
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String description) {
		this.name = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}

	public long getSize() {
		return size;
	}

	public void setSize(long size) {
		this.size = size;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Integer> getUserIds() {
		return userIds;
	}

	public void setUserIds(List<Integer> userIds) {
		this.userIds = userIds;
	}

	@Override
	public String toString() {
		return "Report{" +
				"id=" + id +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", size=" + size +
				'}';
	}
}
