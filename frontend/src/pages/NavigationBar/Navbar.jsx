import React, { useContext } from "react";
import {
  AppBar,
  Box,
  Toolbar,
  IconButton,
  Typography,
  Menu,
  Container,
  Avatar,
  Button,
  Tooltip,
  MenuItem,
  Divider,
} from "@mui/material";
import { AppContext } from "../../context/userContext";
import MenuIcon from "@mui/icons-material/Menu";
import { useNavigate } from "react-router-dom";
import Logo from "../../assets/images/Logo.png";
import { ROUTES } from "../../common/constants";
import Settings from "./components/Settings";


const settings = ["Profile", "Change Password", "Logout"];

const Navbar = () => {
  const {
    state: { authenticated, currentUser },
  } = useContext(AppContext);
  let navigate = useNavigate();
  const [anchorElNav, setAnchorElNav] = React.useState(null);
  const [anchorElUser, setAnchorElUser] = React.useState(null);

  const handleOpenNavMenu = (event) => {
    console.log(event);
    setAnchorElNav(event.currentTarget);
  };
  const handleOpenUserMenu = (event) => {
    setAnchorElUser(event.currentTarget);
  };

  const handleCloseNavMenu = () => {
    console.log("test");
    setAnchorElNav(null);
  };

  const handleCloseUserMenu = () => {
    setAnchorElUser(null);
  };

  return (
    <AppBar position="static" style={{ background: "#ffffff" }}>
      <Container maxWidth="xl">
        <Toolbar disableGutters>
          <Box noWrap sx={{ mr: 2, display: { xs: "none", md: "flex" } }}>
            <img
              onClick={(event) => navigate(ROUTES.VIEW_TIMELINE)}
              height={44}
              width={104}
              src={Logo}
              alt="logo"
            />
          </Box>

          <Box sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <IconButton
              size="large"
              aria-label="account of current user"
              aria-controls="menu-appbar"
              aria-haspopup="true"
              onClick={handleOpenNavMenu}
              color="default"
            >
              <MenuIcon />
            </IconButton>
            <Menu
              id="menu-appbar"
              anchorEl={anchorElNav}
              anchorOrigin={{
                vertical: "bottom",
                horizontal: "left",
              }}
              keepMounted
              transformOrigin={{
                vertical: "top",
                horizontal: "left",
              }}
              open={Boolean(anchorElNav)}
              onClose={handleCloseNavMenu}
              sx={{
                display: { xs: "block", md: "none", color: "black" },
              }}
            >
               
              {/* {pages.map((page) => (
                <MenuItem key={page} onClick={handleCloseNavMenu}>
                  <Typography textAlign="center">{page}</Typography>
                </MenuItem>
              ))} */}
           
              {/* <MenuItem onClick={() => navigate(ROUTES.PROPERTY_LISTING)}>
                <Typography textAlign="center">Property Rental</Typography>
              </MenuItem> */}
              <MenuItem  onClick={() => 
                  navigate(ROUTES.CONNECT_FRIENDS)}>
                  <Typography textAlign="center">Connect</Typography>
                </MenuItem>
              <MenuItem onClick={() => navigate(ROUTES.USER_FRIENDS)}>
                <Typography textAlign="center">My Friends</Typography>
              </MenuItem>
              <MenuItem onClick={() => navigate(ROUTES.USER_POSTS)}>
                <Typography textAlign="center">My Posts</Typography>
              </MenuItem>

            </Menu>
          </Box>
          <Box noWrap sx={{ flexGrow: 1, display: { xs: "flex", md: "none" } }}>
            <img height={40} width={80} src={Logo} alt="logo" />
          </Box>
          <Box sx={{ flexGrow: 1, display: { xs: "none", md: "flex" } }}>
            
            <Button
              onClick={() => navigate(ROUTES.CONNECT_FRIENDS)}
              sx={{ my: 2, color: "black", display: "block" }}
            >
              Connect
              </Button>
              <Button
              onClick={() => navigate(ROUTES.USER_FRIENDS)}
              sx={{ my: 2, color: "black", display: "block" }}
            >
              My Friends
            </Button>
            <Button
              onClick={() => navigate(ROUTES.USER_POSTS)}
              sx={{ my: 2, color: "black", display: "block" }}
            >
              My Posts
            </Button>
          </Box>
          <Box
            display="flex"
            justifyContent="space-evenly"
            alignItems="right"
            // width={225}
            sx={{ flexGrow: 0 }}
          >
            <Button
              variant="contained"
              sx={{
                marginRight: 2,
                backgroundColor: "#1C3988",
                alignItems: "right",
                display: { xs: "none", md: "flex" },
              }}
              onClick={() => navigate(ROUTES.ADD_POST)}
            >
              Post
            </Button>
            {/* {authenticated ? ( */}
              <Settings />
            {/* ) : (
              <Button
                variant="contained"
                sx={{
                  backgroundColor: "#1C3988",
                  display: { md: "flex" },
                }}
                onClick={() => navigate(ROUTES.LOGIN)}
              >
                Login
              </Button>
            )} */}
          </Box>
        </Toolbar>
      </Container>
    </AppBar>
  );
};

export default Navbar;
