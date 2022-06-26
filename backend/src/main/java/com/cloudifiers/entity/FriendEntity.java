package com.cloudifiers.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "FRIENDS")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FriendEntity {

	@EmbeddedId
	private FriendId friendId;

	@Embeddable
	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	public static class FriendId implements Serializable {

		private static final long serialVersionUID = 1L;

		@Column(name = "USER1_ID")
		private Integer user1Id;

		@Column(name = "USER2_ID")
		private Integer user2Id;

	}
}