import axios from 'axios'

export const apiClient = axios.create(
    {
        baseURL: 'http://localhost:8080'
    }
);
export const deleteTodoApi
    = (username, id) => apiClient.delete(`/users/${username}/todos/${id}`)
    //http://localhost:8080/users/in28minutes/todos