package com.cloudifiers.constants;

public class CloudifiersConstants {

	public static final String BASE_API_URL = "/v1/api";
	
	public static class ApiEndPoints {
		
		// user management
		public static final String SAVE_USER_URL = "/user";
		public static final String SAVE_USER_DESC = "Create a new user if the userId field is null, otherwise update the existing user with id userId";
		
		public static final String FETCH_USER_URL = "/user/{userId}";
		public static final String FETCH_USER_DESC = "Fetches user with the given userId";
		
		public static final String LOGIN_URL = "/login";
		public static final String LOGIN_DESC = "Fetches user with the given email and password";
		
		public static final String SEARCH_USER_URL = "/user/search/{keyword}";
		public static final String SEARCH_USER_DESC = "Search users using firstName, lastName, email, or concatination of firstName and lastName";
		
		// friend list management
		public static final String ADD_FRIEND_URL = "/friend/add";
		public static final String ADD_FRIEND_DESC = "Add a friend into friend list";
		
		public static final String REMOVE_FRIEND_URL = "/friend/remove";
		public static final String REMOVE_FRIEND_DESC = "Remove a friend from friend list";
		
		public static final String GET_FRIENDS_URL = "/friend/list/{userId}";
		public static final String GET_FRIENDS_DESC = "Find list of friends of user userId";
		
		public static final String CHECK_FRIEND_STATUS_URL = "/friend/status/{user1Id}/{user2Id}";
		public static final String CHECK_FRIEND_STATUS_DESC = "Checks whether the user2Id is in the list of user1 friends.";
		
		// post management
		public static final String ADD_POST_URL = "/post/add";
		public static final String ADD_POST_DESC = "Creates a new Post.";
		
		public static final String FETCH_POST_URL = "/post/{postId}";
		public static final String FETCH_POST_DESC = "Fetch existing post using post id.";
		
		public static final String FETCH_POSTS_URL = "/post/user/{userId}";
		public static final String FETCH_POSTS_DESC = "Retrieves all the posts created by user with provided userId";
		
		public static final String DELETE_POST_URL = "/post/{postId}";
		public static final String DELETE_POST_DESC = "Delete existing post using post id.";
		
		public static final String LIKE_POST_URL = "/post/like/{postId}/{userId}";
		public static final String LIKE_POST_DESC = "Performs the like operation when userId likes the post with given postId.";
		
		public static final String DISLIKE_POST_URL = "/post/dislike/{postId}/{userId}";
		public static final String DISLIKE_POST_DESC = "Performs the dislike operatioin when userId dislikes the post with given postId.";
		
		public static final String CHECK_LIKE_STATUS_URL = "/post/like/status/{postId}/{userId}";
		public static final String CHECK_LIKE_STATUS_DESC = "Checks whether the given user has liked the given post.";
		
		public static final String GET_TOTAL_LIKES_URL = "/post/like/total/{postId}";
		public static final String GET_TOTAL_LIKES_DESC = "Fetches count of total likes of post with given postId.";
		
		public static final String GET_USERS_LIKED_POST_URL = "/post/like/{postId}";
		public static final String GET_USERS_LIKED_POST_DESC = "Fethces all the users who liked post with given postId.";
		
		public static final String ADD_COMMENT_URL = "/post/comment";
		public static final String ADD_COMMENT_DESC = "Add a new comment.";
		
		public static final String DELETE_COMMENT_URL = "/post/comment/{commentId}";
		public static final String DELETE_COMMENT_DESC = "Delete existing comment.";
		
		public static final String FETCH_COMMNETS_URL = "/post/comment/{postId}";
		public static final String FETCH_COMMENTS_DESC = "Fetches all the comment for post with given postId.";
		
		// Storage
		public static final String UPLOAD_FILE_URL = "/file/upload";
		public static final String UPLOAD_FILE_DESC = "Uploads file into s3 bucket and returns url of the uploaded file.";
		
		public static final String REMOVE_FILE_URL = "/file/remove/{fileName}";
		public static final String REMOVE_FILE_DESC = "Remove file from s3 bucket."; 
		
		// timeline
		public static final String GENERATE_TIMELINE_URL = "/timeline/{userId}";
		public static final String GENERATE_TIMELINE_DESC = "Generates timeline for user with provided userId.";
	}	
	
	public static class ControllerInfo {
		public static final String USER_MANAGEMENT_CONTROLLER_VALUE = "User Management Controller";
		public static final String USER_MANAGEMENT_CONTROLLER_DESC = "Performs save, update, and delete operations on user";
		
		public static final String STORAGE_CONTROLLER_VALUE = "Storage Controller";
		public static final String STORAGE_CONTROLLER_DESC = "Performs file upload and delete operation in S bucket.";
	}
	
	public static class DatabaseConstants {
		public static final String FETCH_USER_QUERY = "SELECT u FROM UserEntity u WHERE u.email = :email AND u.password = :password";
		public static final String FETCH_FRIENDS_QUERY = "SELECT u FROM FriendEntity f LEFT JOIN UserEntity u ON f.friendId.user2Id = u.userId WHERE f.friendId.user1Id = :userId";
		public static final String SEARCH_USER_QUERY = "SELECT u FROM UserEntity u WHERE "
				+ "lower(u.email) like %:keyword% "
				+ "or lower(u.firstName) like %:keyword% "
				+ "or lower(u.lastName) like %:keyword% "
				+ "or CONCAT(lower(u.firstName), ' ', lower(u.lastName)) like %:keyword% "
				+ "or CONCAT(lower(u.lastName), ' ', lower(u.firstName)) like %:keyword%";
		public static final String FETCH_USERS_LIKED_POST_QUERY = "SELECT u FROM UserEntity u WHERE u.userId IN (SELECT l.likeId.userId FROM LikeEntity l WHERE l.likeId.postId = :postId)";
		public static final String FIND_COMMENT_BY_POST_ID_QUERY = "SELECT c FROM CommentEntity c WHERE c.postId = :postId";
		public static final String GET_POSTS_BY_USER_ID_QUERY = "SELECT p FROM PostEntity p WHERE p.userId = :userId";
		public static final String GET_TIMELINE_QUERY = "SELECT p from PostEntity p WHERE p.userId IN (SELECT f.friendId.user2Id FROM FriendEntity f WHERE f.friendId.user1Id = :userId)";
	}
	
	public static class ParameterConstants {
		public static final String EMAIL = "email";
		public static final String PASSWORD = "password";
		public static final String USER_ID = "userId";
		public static final String POST_ID = "postId";
		
		public static final String KEYWORD = "keyword";
	}
}
