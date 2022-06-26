import React, { useEffect, useState, useContext, useCallback } from 'react'
import NavigationBar from "../../NavigationBar/Navbar";
import Box from '@mui/material/Box';
import Button from '@mui/material/Button';
import { Container, CssBaseline } from '@mui/material';
import { FormControl, FormLabel, RadioGroup, Radio, FormGroup, FormControlLabel, Autocomplete, TextField, Grid, Checkbox, Typography, Select, MenuItem } from '@mui/material';
import postImage from "../../../assets/images/PostImage.jpg";
import { makeStyles } from "@mui/styles";
import { useForm } from "react-hook-form";
import { yupResolver } from "@hookform/resolvers/yup";
import { PropertySchema } from '../../../common/validationSchema';

import { ROUTES } from '../../../common/constants';
import axios_api from '../../../common/axios';
import { useNavigate, useParams } from "react-router-dom";
import { toast } from "react-toastify";
import { AppContext } from "../../../context/userContext";


const initialState = {
    description: '',
    fileName: '',
    likes: 0,
    postId: null,
    postUrl: '',
    userId: ''
};

const useStyles = makeStyles((theme) => ({
    paper: {
        width: "100%",
        backgroundColor: "#fff",
        transition: "box-shadow 300ms cubic-bezier(0.4, 0, 0.2, 1) 0ms",
        padding: "1rem",
        boxShadow: "rgb(100 116 139 / 12%) 0px 10px 15px",
        borderRadius: 8,
    },
    fileInput: {
        width: '97%',
        margin: '10px 0',
    },
}));

const AddPost = (props) => {
    // const {
    //     state: { userId, authenticated },
    //     dispatch,
    // } = useContext(AppContext);

    const userId = localStorage.getItem("userId");

    const [newPost, setNewPost] = useState(initialState);
    const [fileData, setFileData] = useState();
    const [descriptionField, setDescriptionField] = useState("");
    let navigate = useNavigate();

    const {
        register,
        formState: { errors },
        handleSubmit,
        reset,
        trigger,
    } = useForm({
        resolver: yupResolver(PropertySchema),
    });

    useEffect(() => {
 
        const post = { ...newPost, userId: userId };
        reset(post)
        setNewPost(post);

    }, []);

    const classes = useStyles();

    const handleBack = () => {
        navigate(ROUTES.VIEW_TIMELINE);
    };


    const handleOnChange = (e) => {
        setDescriptionField(e.target.value);
    }

    const handleUpload = (e) => {
        setFileData(e.target.files[0]);

    };

    const onSubmit = () => {
        const data = new FormData();
        data.append('file', fileData);
        if (fileData) {
            axios_api.post("/file/upload", data)
                .then((res) => {
                    console.log(res.data);
                    if(res.data.success){
                        const path = res.data.url;
                        const filename = res.data.fileName;
                        const post = { ...newPost, fileName: filename, postUrl: path, description: descriptionField}
                        setNewPost(post);

                        axios_api
                        .post("/post/add", post)
                        .then((response) => {
                            if ((response.data.success)) {
                                console.log(response.data);
                                toast.success("You posted successfully!");
                                reset();
                                navigate(ROUTES.VIEW_TIMELINE);
                            } else {
                                toast.success("Something went wrong!");
                            }
                        })
                        .catch((err) => {                                
                            toast.error(err?.response?.data?.message || "Something went wrong");
                        });
                    }                 
                })
                .catch((err) => {
                    console.log(err?.response?.data?.message);
                });

        }
        else {

        }
    };

    return (
        <>
            <NavigationBar />

            <Grid container>
                <img src={postImage} width="100%" height="410px" />
                <Box component="form" sx={{ width: '100%', mt: 5 }} >
                    {/* <Stepper sx={{ maxWidth: '80%', marginLeft: 'auto', marginRight: 'auto' }} activeStep={activeStep} >
                        label = "Post an Image";
                        {steps.map((label) => {
                            const stepProps = {};
                            const labelProps = {};
                            // if (isStepSkipped(index)) {
                            //     stepProps.completed = false;
                            // }
                            return (
                                <Step key={label}>
                                    <StepLabel StepIconComponent={ImageIcon}>{label}</StepLabel>
                                </Step>
                            );
                        })}
                    </Stepper> */}
                    
                        <React.Fragment>
                            <Container component="main" maxWidth="sm" sx={{ mt: 5 }}>
                                <CssBaseline />
                                <div className={classes.paper} >
                                    <Box
                                        sx={{
                                            marginTop: 0,
                                            display: 'flex',
                                            flexDirection: 'column',
                                            alignItems: 'center',
                                        }}
                                    >

                                        <Grid container spacing={2}>
                                            <Grid item xs={12} textAlign="center">
                                                <Typography component="h1" variant="h5" >
                                                    Upload a Post
                                                </Typography>
                                            </Grid>
                                            {/* <Grid item xs={12}>
                                                <TextField
                                                    {...register("title")}
                                                    variant="outlined"
                                                    fullWidth
                                                    name="title"
                                                    label="Title"
                                                    id="title"
                                                    value={newPost.title}
                                                    error={!!errors.title}
                                                    helperText={errors.title ? errors.title.message : ""}
                                                    onChange={handleOnChange}
                                                />
                                            </Grid> */}
                                            <Grid item xs={12} sm={3} sx={{ marginTop: "12px" }}>
                                                <Typography component="h1" variant="subtitle1">
                                                    Select Image:
                                                </Typography>
                                            </Grid>
                                            <Grid item xs={12} sm={9} sx={{ marginTop: 1 }}>
                                                <div><input type="file" name="image" onChange={handleUpload} /></div>
                                            </Grid>
                                            <Grid item xs={12}>
                                                <TextField
                                                    {...register("description")}
                                                    variant="outlined"
                                                    fullWidth
                                                    name="description"
                                                    label="Description"
                                                    // value={newPost.description}
                                                    id="description"
                                                    multiline
                                                    rows={3}
                                                    error={!!errors.description}
                                                    helperText={errors.description ? errors.description.message : ""}
                                                    onChange={handleOnChange}
                                                />
                                            </Grid>
                                        </Grid>
                                    </Box>
                                    {/* <AdDetails /> */}
                                </div>
                                <Box sx={{ display: 'flex', flexDirection: 'row', pt: 2 }}>
                                    <Button
                                        color="inherit"
                                        // disabled={activeStep === 0}
                                        onClick={handleBack}
                                        sx={{ mr: 1 }}
                                    >
                                        Back
                                    </Button>
                                    <Box sx={{ flex: '1 1 auto' }} />
                                    <Button onClick={onSubmit}>
                                        {/* {activeStep === steps.length - 1 ? 'Post Ad' : 'Next'} */}
                                        Post
                                    </Button>
                                </Box>
                            </Container>

                        </React.Fragment>
                </Box>
            </Grid>
        </>
    )
}

export default AddPost;