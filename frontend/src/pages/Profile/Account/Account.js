// import {
//   Avatar,
//   Box,
//   Button,
//   Card,
//   CardActions,
//   CardContent,
//   Divider,
//   Typography,
//   Input,
// } from "@mui/material";
// import CameraAltIcon from "@mui/icons-material/CameraAlt";
// import React, { useContext, useEffect, useState } from "react";
// import { AppContext } from "../../../context/userContext";
// import axios_api from "../../../common/axios";
// import AccountCircleIcon from "@mui/icons-material/AccountCircle";

// const Account = (props) => {
//   //   const {
//   //     state: { authenticated, currentUser },
//   //   } = useContext(AppContext);
//   const [fileName, setFileName] = useState();
//   //   const [userDetails, setUserDetails] = useState();
//   const userId = localStorage.getItem("userId");

//   //   const handleUpload = async (e) => {
//   //     let imageData = e.target.files[0];
//   //     setFileName(imageData.name);
//   //     props.setFileData(imageData);
//   //   };

//   //   useEffect(async () => {
//   //     await axios_api
//   //       .get(`/user/${userId}`)
//   //       .then((response) => {
//   //         if (response.data.success) {
//   //           setUserDetails(response.data.user);
//   //         }
//   //       })
//   //       .catch((err) => {
//   //         setUserDetails([]);
//   //       });
//   //   }, []);

//   return (
//     <>
//       <Card>
//         <CardContent>
//           <Box
//             sx={{
//               alignItems: "center",
//               display: "flex",
//               flexDirection: "column",
//             }}
//           >
//             {/* <Avatar
//               src={currentUser.imgURL}
//               sx={{
//                 height: 100,
//                 mb: 2,
//                 width: 100,
//               }}
//             /> */}
//             <div align="center">
//               <AccountCircleIcon style={{ fontSize: 85 }} color="primary" />
//             </div>
//             <Typography color="textPrimary" gutterBottom variant="h5">
//               {props.firstName + " " + props.lastName}
//             </Typography>
//           </Box>
//         </CardContent>
//         <Divider />

//         {/* <CardActions>
//           <label htmlFor="contained-button-file">
//             <Box sx={{ display: "flex" }}>
//               <Input
//                 sx={{ display: "none" }}
//                 accept="image/*"
//                 id="contained-button-file"
//                 type="file"
//                 onChange={handleUpload}
//               />
//               <Button
//                 component="span"
//                 color="primary"
//                 fullWidth
//                 variant="text"
//                 startIcon={<CameraAltIcon />}
//               >
//                 Upload picture
//               </Button>
//               <Typography
//                 component="span"
//                 sx={{ mt: 1 }}
//                 textOverflow="ellipsis"
//                 overflow="hidden"
//                 whiteSpace="nowrap"
//               >
//                 {fileName ? fileName : ""}
//               </Typography>
//             </Box>
//           </label>
//         </CardActions> */}
//       </Card>
//     </>
//   );
// };

// export default Account;
