import React, { useEffect, useState, useContext } from 'react'
import NavigationBar from "../../NavigationBar/Navbar";
import { Container, Box, CssBaseline, TextField, Grid } from '@mui/material';
import { Card, CardMedia, CardContent, Typography, CardActions, Button } from '@mui/material';
import axios_api from '../../../common/axios';
import PageHeading from "../../../Components/PageHeading/PageHeading";
import { Divider } from '@material-ui/core';
import AccountCircleIcon from '@mui/icons-material/AccountCircle';
import LikePost from '../../PostManagement/LikePost/LikePost';
import Footer from "../../Footer/Footer";

const ViewTimeline = () => {
    const userId=localStorage.getItem("userId");

    const [postDetails, setpostDetails] = useState(null);

    useEffect(async () => {
        await axios_api.get(`/timeline/${userId}`)
            .then(response => {
                if (response.data.success) {
                    setpostDetails(response.data.postModelList);
                }

            }).catch((err) => {
                setpostDetails([])
            })
    }, [])

    return (
        <>
            <NavigationBar />
            <PageHeading heading="Welcome to Cloudifiers!" />
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
                            {postDetails ? postDetails.length > 0 ? postDetails.map((postDetail) => (
                                <Grid item xs={12} sm={6} md={4} lg={3.5} >
                                    <Card sx={{ maxWidth: 345 }}>
                                        <CardContent>
                                            <div sx={{ display: 'flex' }}>
                                                <Button color="primary" startIcon={<AccountCircleIcon style={{ fontSize: 50 }} color="primary" />}>
                                                    <span style={{ color: 'black', fontSize: 25 }}> 
                                                    {/* textTransform: 'capitalize'  */}
                                                        {postDetail.userEntity.firstName} {postDetail.userEntity.lastName}
                                                    </span>
                                                </Button>
                                            </div>
                                        </CardContent>
                                        <CardMedia
                                            component="img"
                                            height="150"
                                            image={postDetail.postEntity.postUrl}
                                            alt="friend image"
                                        />
                                        <CardContent>
                                            <Typography gutterBottom variant="body2" component="div" textOverflow="ellipsis" overflow="hidden" whiteSpace="nowrap">
                                                {postDetail.postEntity.description}
                                            </Typography>
                                        </CardContent>
                                        <Divider />
                                        <Box sx={{ display: 'flex', justifyContent: 'center' }}>
                                            <LikePost postId={postDetail.postEntity.postId} /> 
                                        </Box>
                                    </Card>
                                </Grid>
                            )

                            ) : "It seems your friends have not posted any images lately. So, there is nothing to show on your timeline!" : " Fetching timeline"}

                        </Grid>
                    </Box>
                </Box>
            </Container>
            <Footer />

        </>
    )
}

export default ViewTimeline