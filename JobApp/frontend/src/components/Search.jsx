import {
  AppBar,
  Toolbar,
  Box,
  Card,
  Typography,
} from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";

const Search = () => {
  const [post, setPost] = useState([]);

  useEffect(() => {
    const fetchingJobPost = async () => {
      try {
        const response = await axios.get(`http://localhost:8080/jobPosts`);
        console.log(response);
        setPost(response.data);
      } catch (error) {
        console.error("Error fetching job posts:", error);
      }
    };
    fetchingJobPost();
  }, []);

  return (
    <>
      <Box sx={{ flexGrow: 1 }}>
        <AppBar position="static">
          <Toolbar>
            <Typography
              variant="h6"
              align="center"
              component="div"
              sx={{ flexGrow: 1 }}
            >
              Job Portal
            </Typography>
          </Toolbar>
        </AppBar>
      </Box>

      <Box
        sx={{
          display: "flex",
          flexWrap: "wrap",
          gap: "16px",
          padding: "16px",
          justifyContent: "center",
        }}
      >
        {post.map((p) => (
          <Card
            key={p.postId}
            sx={{
              padding: "16px",
              maxWidth: "300px",
              boxShadow: 3,
              display: "flex",
              flexDirection: "column",
              gap: "8px",
            }}
          >
            <Typography
              variant="h5"
              sx={{ fontSize: "1.5rem", fontWeight: "600" }}
            >
              {p.postProfile}
            </Typography>
            <Typography
              sx={{ color: "#585858", marginTop: "8px" }}
              variant="body1"
            >
              Description: {p.postDesc}
            </Typography>
            <Typography variant="h6">
              Years of Experience: {p.reqExperience} years
            </Typography>

            <Typography variant="body2" gutterBottom>
              Skills:
            </Typography>
            {p.postTechStack.map((s, i) => (
              <Typography variant="body2" gutterBottom key={i}>
                {s}
              </Typography>
            ))}
          </Card>
        ))}
      </Box>
    </>
  );
};

export default Search;
