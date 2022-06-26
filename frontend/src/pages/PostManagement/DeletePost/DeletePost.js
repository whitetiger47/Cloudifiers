import React from "react";
import { useNavigate } from "react-router-dom";
import { ROUTES } from "../../../common/constants";
import axios_api from "../../../common/axios";
import {
  Dialog,
  DialogTitle,
  DialogContent,
  DialogActions,
  DialogContentText,
  Button,
} from "@mui/material";
import { toast } from "react-toastify";
import DeleteForeverIcon from "@mui/icons-material/DeleteForever";
import CloseIcon from "@mui/icons-material/Close";

const DeletePost = (props) => {
  const [openMsg, setOpenMsg] = React.useState(false);
  const navigate = useNavigate();

  const handleClickOpen = () => {
    setOpenMsg(true);
  };

  const handleClose = () => {
    setOpenMsg(false);
  };

  const handleDeleteButton = (event) => {
    axios_api
      .delete(`/file/remove/${props.fileName}`)
      .then((response) => {
        if (response.data.success) {
          axios_api
            .delete(`/post/${props.postId}`)
            .then((res) => {
              if (res.data.success) {
                setOpenMsg(false);
                toast.success("Post Deleted Successfully!");
                navigate(ROUTES.USER_POSTS);
              }
            })
            .catch((err) => {
              toast.error(
                "Post description could not be Deleted. Please try again later!" ||
                  "Something went wrong!"
              );
            });
        }
      })
      .catch((err) => {
        toast.error(
          "Post could not be Deleted. Please try again later." ||
            "Something went wrong!"
        );
      });
  };

  return (
    <>
      <Button
        color="error"
        variant="contained"
        startIcon={<DeleteForeverIcon />}
        sx={{ mt: 3, mb: 2, mr: 2 }}
        onClick={handleClickOpen}
      >
        Delete
      </Button>
      <Dialog open={openMsg} onClose={handleClose}>
        <DialogTitle>Delete Post</DialogTitle>
        <DialogContent>
          <DialogContentText>
            Are you sure you want to 'Delete' this post?
          </DialogContentText>
        </DialogContent>
        <DialogActions>
          <Button
            startIcon={<CloseIcon />}
            variant="outlined"
            onClick={handleClose}
          >
            Close
          </Button>
          <Button
            startIcon={<DeleteForeverIcon />}
            color="error"
            variant="outlined"
            onClick={handleDeleteButton}
          >
            Delete
          </Button>
        </DialogActions>
      </Dialog>
    </>
  );
};

export default DeletePost;
