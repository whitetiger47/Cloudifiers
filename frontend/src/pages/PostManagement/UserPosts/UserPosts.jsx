import React, { useEffect, useState, useContext } from 'react'
import NavigationBar from "../../NavigationBar/Navbar";
import { Container, Box, CssBaseline, TextField, Grid } from '@mui/material';
import { Card, CardMedia, CardContent, Typography, CardActions, Button } from '@mui/material';
import axios_api from '../../../common/axios';
import { useNavigate } from 'react-router';
import { AppContext } from "../../../context/userContext";
import PageHeading from "../../../Components/PageHeading/PageHeading";
import FriendButton from '../../FriendListManagement/FriendButton/FriendButton';
import { Divider } from '@material-ui/core';

import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import LikePost from '../LikePost/LikePost';
import DeletePost from '../DeletePost/DeletePost.js';

const UserPosts = () => {
    // const {
    //     state: { authenticated, userId, currentUser },
    // } = useContext(AppContext);

    const userId=localStorage.getItem("userId");

    const navigate = useNavigate();

    // const [friendDetails, setFriendDetails] = useState([]);

    const [userPostDetails, setUserPostDetails] = useState(null);
    const [userDetails, setUserDetails] = useState(null);
    
    // const fetchPostDetails = (data) => {
    //     data.map((postData) => (
    //         //call api to get details of the post from post table
    //         axios_api.get(`/properties/getProperty/${postData.post_user_id}`).then((res) => {
    //             if(res.data.success){
    //                 setFriendDetails(prev => ([...prev, res.data.data]));
    //             };   
    //         })
    //     ))
    // }

    // const fetchUserDetails = async (userID) => {
    //     await axios_api.get(`/user/${userID}`).then((res) => {
    //         if(res.data.success){
    //             console.log(res);
    //             setUserDetails(res.data.user);
    //         }
    //     }).catch((err) => {
    //             setUserDetails([]);
    //         })
    // }

    useEffect(async () => {
        //call api to get the list of all posts of the user from the Posts table
        axios_api.get(`/post/user/${userId}`).then((res) => {
            if(res.data.success){
                // console.log(res);
                // fetchPostDetails(res.data.favorites);
                // setPostDetails(prev => ([...prev, res.data.data]));
                setUserPostDetails(res.data.postModelList);
            }
        }).catch((err) => {
                setUserPostDetails([]);
            })
    }, [])

    // const handleClick = (propertyId) => {
    //     // navigate(`/view_favorites_details/${propertyId}`);
    //     navigate(`/propertyDetails/${propertyId}`);

    // };

    return (
        <>
            <NavigationBar />
            <PageHeading heading="My Posts" />
            <Container component="main" maxWidth='xl' style={{ width: "85%", margin: "3rem auto" }}>
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                    }}
                >

                    <Box component="form" noValidate sx={{ mt: 3 }}>
                        <Grid container spacing={4}>
                            {userPostDetails ? userPostDetails.length > 0 ? userPostDetails.map((userPostDetail) => (
                                <Grid item xs={12} sm={6} md={4} lg={3.5} >
                                    <Card sx={{ maxWidth: 345 }}>
                                        <CardContent>
                                            <div sx={{ display: 'flex' }}>
                                                <Button color="primary" startIcon={<AccountCircleIcon style={{ fontSize: 50 }} color="primary" />}>
                                                    <span style={{ color: 'black', fontSize: 25 }}> 
                                                        {userPostDetail.userEntity.firstName} {userPostDetail.userEntity.lastName}
                                                    </span>
                                                </Button>
                                            </div>
                                        </CardContent>
                                        <CardMedia
                                            component="img"
                                            height="150"
                                            image={userPostDetail.postEntity.postUrl}
                                            alt="friend image"
                                        />
                                        <CardContent>
                                            <Typography gutterBottom variant="body2" component="div" textOverflow="ellipsis" overflow="hidden" whiteSpace="nowrap">
                                                {userPostDetail.postEntity.description}
                                            </Typography>
                                        </CardContent>
                                        <Divider />
                                        <Box sx={{ display: 'flex', justifyContent: 'center' }}>
                                            <LikePost postId={userPostDetail.postEntity.postId} />
                                            <DeletePost postId={userPostDetail.postEntity.postId} fileName={userPostDetail.postEntity.fileName} />
                                        </Box>
                                    </Card>
                                </Grid>
                            )

                            ) : "You have not posted any pictures! Now is the time to post some ;)" : " Fetching your posts"}

                        </Grid>
                    </Box>
                </Box>
            </Container>

        </>
    )
}

export default UserPosts