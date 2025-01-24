import DeleteIcon from '@mui/icons-material/Delete';
import EditIcon from '@mui/icons-material/Edit';
import SearchIcon from '@mui/icons-material/Search';

import {
  Box,
  Card,
  InputAdornment,
  TextField,
  Typography,
} from "@mui/material";
import axios from "axios";
import { useEffect, useState } from "react";
import { useNavigate } from 'react-router-dom';

const Search = () => {
  const [query, setQuery] = useState("");
  const [post, setPost] = useState(null);
  const navigate = useNavigate();

  const handleEdit = (id) => {
    navigate("/edit", { state: { id } });
  };

  useEffect(() => {
    const fetchPosts = async () => {
      const response = await axios.get(`http://localhost:8080/jobPosts/keyword/${query}`);
      setPost(response.data);
    };

    const fetchInitialPosts = async () => {
      const response = await axios.get(`http://localhost:8080/jobPosts`);
      setPost(response.data);
    };

    fetchInitialPosts();
    if (query.length === 0) fetchInitialPosts();
    if (query.length > 2) fetchPosts();
  }, [query]);

  const handleDelete = (id) => {
    async function deletePost() {
      await axios.delete(`http://localhost:8080/jobPost/${id}`);
    }
    deletePost();
    window.location.reload();
  };

  return (
    <>
      <Box sx={{ margin: "2%" }}>
        <Box sx={{ marginBottom: "16px" }}>
          <TextField
            InputProps={{
              startAdornment: (
                <InputAdornment position="start">
                  <SearchIcon />
                </InputAdornment>
              ),
            }}
            placeholder="Search..."
            fullWidth
            onChange={(e) => setQuery(e.target.value)}
          />
        </Box>

        <Box
          sx={{
            display: "grid",
            gridTemplateColumns: "repeat(auto-fill, minmax(300px, 1fr))",
            gap: "16px",
          }}
        >
          {post &&
            post.map((p, index) => (
              <Card
                key={p.id || index} // Use p.id if unique, else fallback to index
                sx={{
                  padding: "16px",
                  backgroundColor: "#ADD8E6",
                  overflow: "hidden",
                }}
              >
                <Typography
                  variant="h5"
                  sx={{
                    fontSize: "2rem",
                    fontWeight: "600",
                    fontFamily: "sans-serif",
                  }}
                >
                  {p.postProfile}
                </Typography>
                <Typography
                  sx={{
                    color: "#585858",
                    marginTop: "8px",
                    fontFamily: "cursive",
                  }}
                  variant="body2"
                >
                  Description: {p.postDesc}
                </Typography>
                <Typography
                  variant="h6"
                  sx={{ marginTop: "8px", fontFamily: "unset" }}
                >
                  Experience: {p.reqExperience} years
                </Typography>
                <Typography
                  sx={{ fontFamily: "serif", marginTop: "8px" }}
                  variant="body2"
                >
                  Skills:
                </Typography>
                {p.postTechStack.map((s, i) => (
                  <Typography key={i} variant="body2">
                    {s}.
                  </Typography>
                ))}
                <Box
                  sx={{
                    display: "flex",
                    justifyContent: "space-between",
                    marginTop: "16px",
                  }}
                >
                  <DeleteIcon onClick={() => handleDelete(p.postId)} />
                  <EditIcon onClick={() => handleEdit(p.postId)} />
                </Box>
              </Card>
            ))}
        </Box>
      </Box>
    </>
  );
};

export default Search;
