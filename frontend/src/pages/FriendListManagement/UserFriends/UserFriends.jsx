import React, { useEffect, useState, useContext } from 'react'
import NavigationBar from "../../NavigationBar/Navbar";
import { Container, Box, CssBaseline, TextField, Grid } from '@mui/material';
import { Card, CardMedia, CardContent, Typography, CardActions, Button } from '@mui/material';
import axios_api from '../../../common/axios';
import { useNavigate } from 'react-router';
import { AppContext } from "../../../context/userContext";
import PageHeading from "../../../Components/PageHeading/PageHeading";
import FriendButton from '../FriendButton/FriendButton';
import { Divider } from '@material-ui/core';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';

const UserFriends = () => {
    // const {
    //     state: { authenticated, userId, currentUser },
    // } = useContext(AppContext);
    const userId=localStorage.getItem("userId");

    const navigate = useNavigate();

    const [friendDetails, setFriendDetails] = useState([]);
    
    // const frndDetails = (data) => {
    //     data.map((friendData) => (
    //         //call api to get details of the friend from Users table
    //         axios_api.get(`/properties/getProperty/${friendData.friend_user_id}`).then((res) => {
    //             if(res.data.success){
    //                 setFriendDetails(prev => ([...prev, res.data.data]));
    //             };   
    //         })
    //     ))
    // }

    useEffect( () => {
        //call api to get the list of friends of the logged in user only
        axios_api.get(`/friend/list/${userId}`).then((res) => {
            if(res.data.success){
                setFriendDetails(res.data.friendList);
            }
        });
    }, [])

    // const handleClick = (propertyId) => {
    //     // navigate(`/view_favorites_details/${propertyId}`);
    //     navigate(`/propertyDetails/${propertyId}`);
    // };

    return (
        <>
            <NavigationBar />
            <PageHeading heading="My Friends" />
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
                        <Grid container spacing={2}>
                            {friendDetails ? friendDetails.length > 0 ? friendDetails.map((friendDetail) => (
                                <Grid item xs={12} sm={6} md={4} lg={3} >
                                    <Card sx={{ maxWidth: 345 }}>
                                        {/* <CardMedia
                                            component="img"
                                            height="140"
                                            image={friendDetail.image}
                                            alt="friend image"
                                        /> */}
                                        <CardContent>
                                            <div align="center">
                                                <AccountCircleIcon style={{ fontSize: 85 }} color="primary"/>
                                            </div>
                                            <Typography align="center" gutterBottom variant="h4" component="div" textOverflow="ellipsis" overflow="hidden" whiteSpace="nowrap">
                                                {friendDetail.firstName}
                                            </Typography>
                                            <Typography align="center" variant="h4" color="text.secondary" textOverflow="ellipsis" overflow="hidden" whiteSpace="nowrap">
                                                {friendDetail.lastName}
                                            </Typography>
                                            <br />
                                            <Typography align="center" variant="body1" color="text.secondary">
                                                {friendDetail.email}
                                            </Typography>
                                        </CardContent>
                                        {/* Call Friend Button */}
                                        <Divider />
                                        <Box sx={{ display: 'flex', justifyContent: 'center', marginLeft: 'auto', marginRight: 'auto' }}>
                                            <FriendButton user2Id={friendDetail.userId}/>
                                        </Box>
                                    </Card>
                                </Grid>
                            )

                            ) : "You do not have any friends! Now is the time to make some new friends ;)" : " Fetching your list of friends"}

                        </Grid>
                    </Box>
                </Box>
            </Container>

        </>
    )
}

export default UserFriends