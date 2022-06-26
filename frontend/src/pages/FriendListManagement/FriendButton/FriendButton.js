import React, { useState, useContext, useEffect } from "react";
import { Button, Dialog } from "@mui/material";
import axios_api from "../../../common/axios";
import { AppContext } from "../../../context/userContext";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "../../../common/constants";
import { toast } from "react-toastify";
import PersonAddIcon from "@mui/icons-material/PersonAdd";
import PersonRemoveIcon from "@mui/icons-material/PersonRemove";

function FriendButton(props) {
  // const {
  //   state: { authenticated, userId, currentUser },
  // } = useContext(AppContext);
  let navigate = useNavigate();
  const userId = localStorage.getItem("userId");

  const frndMsg = "Add Friend";
  const msgIcon = <PersonAddIcon />;

  const [frndMsgState, setFrndMsgState] = useState(frndMsg);
  const [msgIconState, setMsgIconState] = useState(msgIcon);

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
        .get(`/friend/status/${userId}/${props.user2Id}`)
        .then((res) => {
          if (res.data.status) {
            // remove friend api based on logged in user id and friend's user id
            const frndRemBody = {
              data: {
                user1Id: userId,
                user2Id: props.user2Id,
              },
            };
            // console.log(frndRemBody);
            axios_api.delete("/friend/remove/", frndRemBody).then((res) => {
              if (res.data.success) {
                console.log("deleted");
                toast.success("You removed a Friend!");
                setFrndMsgState("Add Friend");
                setMsgIconState(<PersonAddIcon />);
                // navigate(ROUTES.USER_FRIENDS);
              }
            });
          } else {
            // call add friend api
            const frndReqBody = {
              user1Id: userId,
              user2Id: props.user2Id,
            };
            axios_api.post("/friend/add/", frndReqBody).then((res) => {
              if (res.data.success) {
                console.log("posted");
                toast.success("You added a new Friend!");
                setFrndMsgState("Remove Friend");
                setMsgIconState(<PersonRemoveIcon />);
                // navigate(ROUTES.USER_FRIENDS);
              }
            });
          }
        })
        .catch((err) => {});
    }
  }, [tempState]);

  useEffect(async () => {
    //Check whether this friend is already a friend of the user or not by checking it in the friends table using user's user id and that friend's user id
    axios_api
      .get(`/friend/status/${userId}/${props.user2Id}`)
      .then((res) => {
        if (res.data.status) {
          console.log("status:", res.data);
          setFrndMsgState("Remove Friend");
          setMsgIconState(<PersonRemoveIcon />);
        } else {
          setFrndMsgState("Add Friend");
          setMsgIconState(<PersonAddIcon />);
        }
      })
      .catch((err) => {});
  }, []);

  return (
    <div>
      <Button
        variant="contained"
        sx={{ mt: 3, mb: 2, mr: 2 }}
        startIcon={msgIconState}
        onClick={handleButton}
      >
        {frndMsgState}
      </Button>
    </div>
  );
}

export default FriendButton;
