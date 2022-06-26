// import React, { useContext, useEffect, useState } from "react";
// import { Box, Container, Grid, Typography, Button } from "@mui/material";
// // import AccountProfile from "../../Components/profile/AccountProfile";
// // import { AccountProfileDetails } from "../../Components/profile/AccountProfileDetails";
// import { useNavigate } from "react-router-dom";
// import ArrowBackIcon from "@mui/icons-material/ArrowBack";
// import { ROUTES } from "../../../common/constants";
// import Navbar from "../../NavigationBar/Navbar";
// import { AppContext } from "../../../context/userContext";
// import { toast } from "react-toastify";
// import PageHeading from "../../../Components/PageHeading/PageHeading";
// import Account from "../Account/Account";
// import AccountDetails from "../AccountDetails/AccountDetails";
// import axios_api from "../../../common/axios";

// const EditProfile = () => {
//   //   const {
//   //     state: { authenticated, authToken },
//   //   } = useContext(AppContext);
//   let navigate = useNavigate();
//   //   const [fileData, setFileData] = useState();
//   const userId = localStorage.getItem("userId");
//   const [userDetails, setUserDetails] = useState();
//   //   useEffect(() => {
//   //     if (!authenticated) {
//   //       navigate(ROUTES.HOMEPAGE);
//   //     }
//   //   }, [authenticated]);

//   useEffect(async () => {
//     await axios_api
//       .get(`/user/${userId}`)
//       .then((response) => {
//         console.log("Response: " + response.data.user);
//         if (response.data.success) {
//           console.log("Response: " + response.data.user);
//           setUserDetails(response.data.user);
//         }
//       })
//       .catch((err) => {
//         setUserDetails([]);
//       });
//   }, []);

//   return (
//     <div>
//       <Navbar />
//       <Box
//         component="main"
//         sx={{
//           flexGrow: 1,
//           py: 8,
//           backgroundColor: "#F9FAFC",
//         }}
//       >
//         <Container maxWidth="lg">
//           <Button
//             sx={{
//               mb: 2,
//               alignItems: "left",
//             }}
//             component="a"
//             startIcon={<ArrowBackIcon fontSize="small" />}
//             onClick={() => navigate(ROUTES.VIEW_TIMELINE)}
//           >
//             Go Back
//           </Button>
//           {/* <Typography sx={{ mb: 2 }} variant="h4">
//             Edit Profile
//         </Typography> */}
//           <PageHeading heading="Edit Profile" />

//           <Grid container spacing={3}>
//             <Grid item lg={4} md={6} xs={12}>
//               <Account
//                 firstName={userDetails.firstName}
//                 lastName={userDetails.firstName}
//               />
//             </Grid>
//             <Grid item lg={8} md={6} xs={12}>
//               <AccountDetails
//                 firstName={userDetails.firstName}
//                 lastName={userDetails.lastName}
//                 email={userDetails.email}
//                 genderId={userDetails.genderId}
//                 password={userDetails.password}
//                 profileUrl={userDetails.profileUrl}
//               />
//             </Grid>
//           </Grid>
//         </Container>
//       </Box>
//       );
//     </div>
//   );
// };

// export default EditProfile;
