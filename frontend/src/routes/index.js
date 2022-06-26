import React, { useContext, useEffect } from "react";
import { Navigate, Routes, Route, useLocation } from "react-router-dom";

import * as ActionTypes from "../common/actionTypes";
import jwtDecode from "jwt-decode";
import App from "../app/App";
import Error from "../common/error";
import { AppContext } from "../context/userContext";
import { ROUTES, TOKEN } from "../common/constants";
import Login from "../pages/UserManagement/Login/Login";
import Signup from "../pages/UserManagement/Signup/Signup";
import Logout from "../Components/Logout";
import AddPost from "../pages/PostManagement/AddPost/AddPost";
import ViewTimeline from "../pages/TimelineManagement/ViewTimeline/ViewTimeline";
import UserPosts from "../pages/PostManagement/UserPosts/UserPosts";
import UserFriends from "../pages/FriendListManagement/UserFriends/UserFriends";
import ConnectFriends from "../pages/FriendListManagement/ConnectFriends/ConnectFriends";
import AccountDetails from "../pages/Profile/AccountDetails/AccountDetails";

function Routing() {
  const { initializeAuth, dispatch } = useContext(AppContext);
  const location = useLocation();
  const openPages = [
    {
      pageLink: ROUTES.LOGIN,
      view: Login,
    },
    {
      pageLink: ROUTES.SIGNUP,
      view: Signup,
    },
    {
      pageLink: ROUTES.LOGOUT,
      view: Logout,
    },
    {
      pageLink: ROUTES.ADD_POST,
      view: AddPost,
    },
    {
      pageLink: ROUTES.VIEW_TIMELINE,
      view: ViewTimeline,
    },
    {
      pageLink: ROUTES.USER_POSTS,
      view: UserPosts,
    },
    {
      pageLink: ROUTES.USER_FRIENDS,
      view: UserFriends,
    },
    {
      pageLink: ROUTES.CONNECT_FRIENDS,
      view: ConnectFriends,
    },
    {
      pageLink: ROUTES.EDIT_ACCOUNT,
      view: AccountDetails,
    },
    {
      pageLink: ROUTES.ERROR,
      view: Error,
    },
  ];

  useEffect(() => {
    initializeAuth();
    if (localStorage.getItem(TOKEN)) {
      const token = localStorage.getItem(TOKEN);
      const decoded = jwtDecode(token);
      const expiresAt = decoded.exp;
      const currentTime = Date.now();

      if (expiresAt < currentTime / 1000) {
        dispatch({ type: ActionTypes.LOGOUT });
      }
    }
  }, []);

  const routes = (
    <Routes location={location}>
      {openPages.map((page, index) => {
        return (
          <Route
            exact
            path={page.pageLink}
            element={<page.view />}
            key={index}
          />
        );
      })}
      <Route path={ROUTES.NOT_FOUND} element={<Navigate to={ROUTES.ERROR} />} />
    </Routes>
  );

  return <div className="container">{routes}</div>;
}

export default Routing;
