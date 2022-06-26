import React, { useState, useEffect, useContext } from "react";
import {
  Box,
  Button,
  Card,
  CardContent,
  CardHeader,
  Divider,
  Grid,
  TextField,
  Link,
  Typography,
  Container,
} from "@mui/material";
import { ROUTES } from "../../../common/constants";
import { useNavigate } from "react-router-dom";
import { useForm } from "react-hook-form";
import { toast } from "react-toastify";
import axios_api from "../../../common/axios";
import ArrowBackIcon from "@mui/icons-material/ArrowBack";

const AccountDetails = (props) => {
  const [userDetails, setUserDetails] = useState("");
  const userID = localStorage.getItem("userId");
  console.log(userID);
  console.log(userDetails);
  console.log(userDetails.firstName);

  const {
    register,
    handleSubmit,
    formState: { errors },
    reset,
    trigger,
    watch,
  } = useForm();

  const intialValues = {
    firstName: userDetails.firstName,
    lastName: userDetails.lastName,
  };

  let navigate = useNavigate();

  const onSubmit = (data) => {
    const { firstName, lastName } = data;
    const email = userDetails.email;
    const genderId = userDetails.genderId;
    const password = userDetails.password;
    const profileUrl = userDetails.profileUrl;
    const topicArn = userDetails.topicArn;
    const userId = userID;
    console.log("updated value: ", firstName);

    const updateDetails = {
      firstName,
      lastName,
      email,
      genderId,
      password,
      profileUrl,
      topicArn,
      userId,
    };
    console.log(firstName);
    axios_api
      .post("/user/", updateDetails)
      .then((response) => {
        if (response.data.success) {
          toast.success("User details updated successfully!");
          reset();
          navigate(ROUTES.VIEW_TIMELINE);
        } else {
          toast.error("User details could not be updated!");
        }
      })
      .catch((err) => {
        toast.error("Something went wrong!!" || "Something went wrong");
      });
  };

  useEffect(async () => {
    axios_api
      .get(`/user/${userID}`)
      .then((response) => {
        if (response.data.success) {
          setUserDetails(response.data.user);
        }
      })
      .catch((err) => {
        setUserDetails([]);
      });
  }, []);

  return (
    <Container maxWidth="lg" sx={{ ml: 40, mt: 10 }}>
      <Button
        sx={{
          mb: 2,
          alignItems: "left",
        }}
        component="a"
        startIcon={<ArrowBackIcon fontSize="small" />}
        onClick={() => navigate(ROUTES.VIEW_TIMELINE)}
      >
        Go Back
      </Button>
      <Grid container spacing={3}>
        <Grid item lg={8} md={6} xs={12}>
          <Card>
            <Box
              component="form"
              noValidate
              onSubmit={handleSubmit(onSubmit)}
              sx={{ mt: 1 }}
            >
              <CardHeader
                subheader="You can edit your information here"
                title="Edit Profile"
              />
              <Divider />
              <CardContent>
                <Grid container spacing={3}>
                  <Grid item md={6} xs={12}>
                    <TextField
                      defaultValue={userDetails.firstName}
                      fullWidth
                      label="First name"
                      name="firstName"
                      id="firstName"
                      required
                      variant="outlined"
                      autoComplete="firstName"
                      autoFocus
                      {...register("firstName", {
                        required: "First Name is Required",
                        pattern: {
                          value: /^[a-zA-Z ]*$/,
                          message: "Please enter alphabet characters only",
                        },
                      })}
                      onKeyUp={() => {
                        trigger("firstName");
                      }}
                    />
                    {errors.firstName && (
                      <Typography component="subtitle2" sx={{ color: "red" }}>
                        {errors.firstName.message}
                      </Typography>
                    )}
                  </Grid>
                  <Grid item md={6} xs={12}>
                    <TextField
                      defaultValue={userDetails.lastName}
                      fullWidth
                      label="Last name"
                      name="lastName"
                      required
                      variant="outlined"
                      autoComplete="lastName"
                      {...register("lastName", {
                        required: "Last Name is Required",
                        pattern: {
                          value: /^[a-zA-Z ]*$/,
                          message: "Please enter alphabet characters only",
                        },
                      })}
                      onKeyUp={() => {
                        trigger("lastName");
                      }}
                    />
                    {errors.lastName && (
                      <Typography component="subtitle2" sx={{ color: "red" }}>
                        {errors.lastName.message}
                      </Typography>
                    )}
                  </Grid>
                  <Grid item md={6} xs={12}>
                    <TextField
                      defaultValue={intialValues.email}
                      disabled
                      fullWidth
                      label="Email Address"
                      name="email"
                      required
                      variant="outlined"
                    />
                  </Grid>
                  {/* <Grid item md={6} xs={12}>
              <TextField
                disabled
                fullWidth
                label="Password"
                name="password"
                required
                type={"password"}
                variant="outlined"
              />
              <Box sx={{ ml: 1, mt: 1 }}>
                <Link
                  onClick={(event) => navigate(ROUTES.CHANGEPASSWORD)}
                  variant="body2"
                >
                  {"Change Password?"}
                </Link>
              </Box>
            </Grid>
            <Grid item md={6} xs={12}>
              <TextField
                defaultValue={intialValues.phoneNumber}
                fullWidth
                label="Phone Number"
                name="phone"
                variant="outlined"
                {...register("phoneNumber", {
                  pattern: {
                    value:
                      /^(\+\d{1,2}\s)?\(?\d{3}\)?[\s.-]?\d{3}[\s.-]?\d{4}$/,
                    message: "Please enter Phone Number in Proper Format",
                  },
                })}
                onKeyUp={() => {
                  trigger("phoneNumber");
                }}
              />
              {errors.phoneNumber && (
                <Typography component="subtitle2" sx={{ color: "red" }}>
                  {errors.phoneNumber.message}
                </Typography>
              )}
            </Grid> */}
                </Grid>
              </CardContent>
              <Divider />
              <Box
                sx={{
                  display: "flex",
                  justifyContent: "flex-end",
                  p: 2,
                }}
              >
                <Button color="primary" variant="contained" type="submit">
                  Update details
                </Button>
              </Box>
            </Box>
          </Card>
        </Grid>
      </Grid>
    </Container>
  );
};

export default AccountDetails;
