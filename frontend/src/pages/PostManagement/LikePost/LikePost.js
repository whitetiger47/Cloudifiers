import React, { useState, useContext, useEffect } from "react";
import { Button, Dialog } from "@mui/material";
import axios_api from "../../../common/axios";
import { AppContext } from "../../../context/userContext";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "../../../common/constants";
import { toast } from "react-toastify";

function LikePost(props) {
  // const {
  //   state: { authenticated, userId, currentUser },
  // } = useContext(AppContext);
  let navigate = useNavigate();
  const userId = localStorage.getItem("userId");

  const LikeMsg = "Like";
  const msgColor = "white";

  const [LikeMsgState, setLikeMsgState] = useState(LikeMsg);
  const [msgColorState, setMsgColorState] = useState(msgColor);
  const [count, setCount] = useState();

  const [tempState, setTempState] = useState(0);

  const handleButton = (event) => {
    // if (!authenticated) {
    //   navigate(ROUTES.LOGIN);
    // } else {
    setTempState(tempState + 1);
    // }
  };

  useEffect(async () => {
    if (tempState != 0) {
      axios_api
        .get(`/post/like/status/${props.postId}/${userId}`)
        .then((res) => {
          if (res.data.status) {
            //   api to delete from Likes table
            axios_api
              .delete(`/post/dislike/${props.postId}/${userId}`)
              .then((response) => {
                if (response.data.success) {
                  axios_api
                    .get(`/post/like/total/${props.postId}`)
                    .then((res) => {
                      if (res.data.success) {
                        setCount(res.data.likes);
                      }
                    })
                    .catch((err) => {});
                  toast.success("You unliked a post!");
                  setLikeMsgState("Like");
                  setMsgColorState("white");
                }
              });
          } else {
            // api to add to Likes table
            // const LikeReqBody = {
            //   userId: userId,
            //   property_id: props.postId,
            // };
            axios_api
              .post(`/post/like/${props.postId}/${userId}`)
              .then((response) => {
                if (response.data.success) {
                  axios_api
                    .get(`/post/like/total/${props.postId}`)
                    .then((res) => {
                      if (res.data.success) {
                        setCount(res.data.likes);
                      }
                    })
                    .catch((err) => {});
                  toast.success("You liked a post!");
                  setLikeMsgState("Unlike");
                  setMsgColorState("red");
                }
              });
          }
        })
        .catch((err) => {});
    }
  }, [tempState]);

  useEffect(async () => {
    //   Call api to check whether the post is already present in the Like table or not?
    axios_api
      .get(`/post/like/status/${props.postId}/${userId}`)
      .then((res) => {
        if (res.data.status) {
          axios_api
            .get(`/post/like/total/${props.postId}`)
            .then((res) => {
              if (res.data.success) {
                setCount(res.data.likes);
              }
            })
            .catch((err) => {});
          setLikeMsgState("Unlike");
          setMsgColorState("red");
        } else {
          axios_api
            .get(`/post/like/total/${props.postId}`)
            .then((res) => {
              if (res.data.success) {
                setCount(res.data.likes);
              }
            })
            .catch((err) => {});
          setLikeMsgState("Like");
          setMsgColorState("white");
        }
      })
      .catch((err) => {});
  }, []);

  return (
    <div>
      <Button
        variant="contained"
        sx={{ mt: 3, mb: 2, mr: 2 }}
        onClick={handleButton}
      >
        <svg
          style={{ marginRight: "4px" }}
          xmlns="http://www.w3.org/2000/svg"
          width="16"
          height="16"
          fill={msgColorState}
          class="bi bi-heart-fill"
          viewBox="0 0 16 16"
        >
          <path
            fill-rule="evenodd"
            d="M8 1.314C12.438-3.248 23.534 4.735 8 15-7.534 4.736 3.562-3.248 8 1.314z"
          />
        </svg>
        {LikeMsgState} {count}
      </Button>
    </div>
  );
}

export default LikePost;
