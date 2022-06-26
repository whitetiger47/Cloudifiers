/* ROUTERS  */
export const ROUTES = {
  LOGIN: "/",
  FORGOT_PASSWORD: "/forgotPassword",
  RESET_PASSWORD: "/api/user/reset/:userId/:jwtToken",
  SIGNUP: "/signup",
  PROFILE: "/profile",
  CHANGEPASSWORD: "/changepassword",
  LOGOUT: "/logout",
  ADD_POST: "/add_post",
  VIEW_TIMELINE: "/view_timeline",
  USER_POSTS: "/user_posts",
  USER_FRIENDS: "/user_friends",
  CONNECT_FRIENDS: "/connect_friends",
  EDIT_PROFILE: "/edit_profile",
  EDIT_ACCOUNT: "/edit_account",

  NOT_FOUND: "*",
  ERROR: "/error",
};

/* Authentication */
export const TOKEN = "TOKEN";
export const USER = "USER";
export const ADMIN = "ADMIN";
export const USER_ID = "USER_ID";

export const APP_ROLES = {
  SUPER_ADMIN: "super_admin",
  APP_USER: "app_user",
};
