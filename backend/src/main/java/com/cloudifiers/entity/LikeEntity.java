package com.cloudifiers.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "LIKES")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LikeEntity {

	@EmbeddedId
	private LikeId likeId;

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class LikeId implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "POST_ID")
		@JsonProperty("postId")
		private Integer postId;

		@Column(name = "USER_ID")
		@JsonProperty("userId")
		private Integer userId;

	}
}
