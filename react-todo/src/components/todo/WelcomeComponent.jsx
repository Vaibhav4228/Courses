
import { useState } from 'react'

import {useParams, Link} from 'react-router-dom'
import { retrieveHelloWorldBean } from './api/HelloWorldApiService.js'

function WelcomeComponent() {

    const {username } = useParams()
    const [message, setMessage] = useState(null)

    // console.log(username)

    function callHelloWorldRestApi(){
        // console.log("Calling hello world rest api")
        // axios.get('http://localhost:8080/hello-world')
        // .then(response => console.log(response))
        // .catch(error => console.log(error))
        // .finally(() => console.log("Finally"))

        retrieveHelloWorldBean()
        .then( (response) => successfulResponse(response) )
        .catch ( (error) => errorResponse(error) )
        .finally ( () => console.log('cleanup') )


    }
    
    function successfulResponse(response) {
        console.log(response)
        
        setMessage(response.data.message)
    }

    function errorResponse(error) {
        console.log(error)
    }
    return (
        <div className="WelcomeComponent">
            <h1>Welcome {username}</h1>
            <div>
                Manage your todos - <Link to="/todos">Go here</Link>
            </div>
            <button className="btn btn-success m-5" onClick={callHelloWorldRestApi}>
        Call Hello World</button>
    
    <div className="text-info">{message}</div>

        </div>
        
    )

}

export default WelcomeComponent