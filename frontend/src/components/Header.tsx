import axios from "axios";
import {useState} from "react";

export default function Header() {

    const[loggedIn, setLoggedIn] = useState(false)

    const handleLogout = () => {
        setLoggedIn(false);
        const host = window.location.host === 'localhost:5173'
            ? 'http://localhost:8080' : window.location.origin;
        window.open(host + '/logout', '_self')

    };
    const handleLogin = () => {
        setLoggedIn(true);
        const host = window.location.host === 'localhost:5173' ? 'http://localhost:8080' : window.location.origin
        window.open(host + '/oauth2/authorization/github', '_self')

    }
    const handleMe = () => {
        axios.get("api/auth/me")
            .then((response) => {
                console.log(response.data)
            })
    };



    return (
        <>
            <h1 className="app-header">Room Ranger 3000</h1>
            <h2 className="app-header-2">Clean up your life</h2>
            <button onClick={handleMe}>Me</button>
            {!loggedIn ?
                <button onClick={handleLogin}>Login</button>
                :
                <button onClick={handleLogout}>Logout</button>
            }
        </>
    );
}
