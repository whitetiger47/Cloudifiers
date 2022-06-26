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

const initialState = {
    filter: 'name',
    value1: '',
    value2: '',
};

const ConnectFriends = () => {
    const {
        state: { authenticated, userId, currentUser },
    } = useContext(AppContext);

    const navigate = useNavigate();

    const [userDetails, setUserDetails] = useState([]);
    const [searchText, setSearchText] = useState("@");

    useEffect( async () => {
        //call api to get the all the users of Cloudifiers application from Users table
        axios_api.get(`/user/search/@`)
            .then((response) => {
                console.log(response);
                if(response.data.success) {
                    setUserDetails(response.data.userList);

                } else {
                    //toast.error(response?.data?.message);
                }
            })
            .catch((err) => {
                setUserDetails([])
            });
    }, [])
    
    // const usrDetails = (data) => {
    //     data.map((userData) => (
    //         //call api to get details of the user from Users table one by one
    //         axios_api.get(`/properties/getProperty/${userData.friend_user_id}`).then((res) => {
    //             if(res.data.success){
    //                 setUserDetails(prev => ([...prev, res.data.data]));
    //             };   
    //         })
    //     ))
    // }

    const handleSearchChange = async (e) => {
        // const value = e.target.value
        // const post = { ...searchText, value1: value };

        // setSearchText(post)
        if(e.target.value == ""){
            setSearchText("@");
        } else {
            setSearchText(e.target.value)
        }
        // axios_api.get(`/user/search/${searchText}`)
        //     .then((response) => {
        //         if(response.data.success) {
        //             setUserDetails(response.data.userList);

        //         } else {
        //             //toast.error(response?.data?.message);
        //         }
        //     })
        //     .catch((err) => {
        //         setUserDetails([])
        //     });
    }

    useEffect( async () => {
        //call api to get the all the users of Cloudifiers application from Users table
        console.log("Search: " + searchText);
        // let search = searchText;
        // if(searchText == ""){
        //     search = "@";
        // }
        
        axios_api.get(`/user/search/${searchText}`)
            .then((response) => {
                if(response.data.success) {
                    setUserDetails(response.data.userList);

                } else {
                    //toast.error(response?.data?.message);
                }
            })
            .catch((err) => {
                setUserDetails([])
            });
    }, [searchText])


    // const handleClick = (propertyId) => {
    //     // navigate(`/view_favorites_details/${propertyId}`);
    //     navigate(`/propertyDetails/${propertyId}`);
    // };

    return (
        <>
            <NavigationBar />
            <PageHeading heading="CONNECT - A place to make new friends as well as connect with old friends" />
            <Container component="main" maxWidth='xl' style={{ width: "85%", margin: "3rem auto" }}>
                <CssBaseline />
                <Box
                    sx={{
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',
                    }}
                >

                    <TextField
                        name="search"
                        fullWidth
                        id="search"
                        label="Search a Friend ..."
                        autoFocus
                        // value={searchText}
                        onChange={handleSearchChange}
                    />

                    <Box component="form" noValidate sx={{ mt: 3 }}>
                        <Grid container spacing={2}>
                            {userDetails ? userDetails.length > 0 ? userDetails.map((userDetail) => (
                                <Grid item xs={12} sm={6} md={4} lg={3} >
                                    <Card sx={{ maxWidth: 345 }}>
                                        {/* <CardMedia
                                            component="img"
                                            height="140"
                                            image={userDetail.image}
                                            alt="friend image"
                                        /> */}
                                        <CardContent>
                                            <div align="center">
                                                <AccountCircleIcon style={{ fontSize: 85 }} color="primary"/>
                                            </div>
                                            <Typography align="center" gutterBottom variant="h4" component="div" textOverflow="ellipsis" overflow="hidden" whiteSpace="nowrap">
                                                {userDetail.firstName}
                                            </Typography>
                                            <Typography align="center" variant="h4" color="text.secondary" textOverflow="ellipsis" overflow="hidden" whiteSpace="nowrap">
                                                {userDetail.lastName}
                                            </Typography>
                                            <br />
                                            <Typography align="center" variant="body1" color="text.secondary">
                                                {userDetail.email}
                                            </Typography>
                                        </CardContent>
                                        {/* Call Friend Button */}
                                        <Divider />
                                        <Box sx={{ display: 'flex', justifyContent: 'center', marginLeft: 'auto', marginRight: 'auto' }}>
                                            <FriendButton user2Id={userDetail.userId}/>
                                        </Box>
                                    </Card>
                                </Grid>
                            )

                            ) : "There are no Users to connect with :(" : "Fetching application Users for you to connect with"}

                        </Grid>
                    </Box>
                </Box>
            </Container>

        </>
    )
}

export default ConnectFriends