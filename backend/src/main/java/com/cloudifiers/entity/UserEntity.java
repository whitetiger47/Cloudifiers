package com.cloudifiers.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "USERS")
@Data
public class UserEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "USER_ID")
	@JsonProperty("userId")
	private Integer userId;

	@Column(name = "PROFILE_URL", length = 200, nullable = true, unique = false)
	@JsonProperty("profileUrl")
	private String profileUrl;

	@Column(name = "FIRST_NAME", length = 200, nullable = false, unique = false)
	@JsonProperty("firstName")
	private String firstName;

	@Column(name = "LAST_NAME", length = 200, nullable = false, unique = false)
	@JsonProperty("lastName")
	private String lastName;

	@Column(name = "EMAIL", length = 200, nullable = false, unique = false)
	@JsonProperty("email")
	private String email;

	@Column(name = "PASSWORD", length = 200, nullable = false, unique = false)
	@JsonProperty("password")
	private String password;

	@Column(name = "GENDER_ID")
	@JsonProperty("genderId")
	private Integer genderId;
	
	@Column(name = "TOPIC_ARN")
	@JsonProperty("topicArn")
	private String topicArn;
}
