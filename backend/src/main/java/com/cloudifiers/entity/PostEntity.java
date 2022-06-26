package com.cloudifiers.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "POST")
@Data
public class PostEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "POST_ID")
	@JsonProperty("postId")
	private Integer postId;

	@Column(name = "USER_ID")
	@JsonProperty("userId")
	private Integer userId;

	@Column(name = "POST_URL", length = 200, nullable = false, unique = false)
	@JsonProperty("postUrl")
	private String postUrl;

	@Column(name = "FILE_NAME")
	@JsonProperty("fileName")
	private String fileName;

	@Column(name = "CREATED_AT")
	@JsonProperty("createdAt")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date createdAt;

	@Column(name = "LIKES")
	@JsonProperty("likes")
	private Integer likes = 0;
	
	@Column(name = "DESCRIPTION")
	@JsonProperty("description")
	private String description;
}