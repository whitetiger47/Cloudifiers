package com.cloudifiers.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "COMMENT")
@Data
public class CommentEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COMMENT_ID")
	@JsonProperty("commentId")
	private Integer commentId;

	@Column(name = "POST_ID")
	@JsonProperty("postId")
	private Integer postId;
	
	@Column(name = "USER_ID")
	@JsonProperty("userId")
	private Integer userId;

	@Column(name = "TEXT", length = 200, nullable = false, unique = false)
	@JsonProperty("text")
	private String text;

	@Column(name = "CREATED_AT")
	@JsonProperty("createdAt")
	private Date createdAt;
}
