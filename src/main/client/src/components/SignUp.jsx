import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

function signUp({setUserId}) {
    const [newUser, setNewUser] = useState({username: "", email: "", password: ""});
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        setNewUser({ ...newUser, [e.target.name]: e.target.value });
    };

    const handleSubmit = async (event) => {
        event.preventDefault();
        const response = await fetch("/api/users", {
            method: "POST",
            headers: {
                "Content-Type": "application/json",
            },
            body: JSON.stringify(newUser)
        })
        const data = await response.json();
        setUserId(data.id);
        navigate("/");
    }

    return (
        <>
            <main>
                <form onSubmit={handleSubmit}>
                    <label htmlFor="username">Username</label>
                    <input
                        type="text"
                        id="username"
                        name="username"
                        autoComplete="username"
                        value={newUser.username}
                        onChange={handleInputChange}
                        required
                    />
                    <br />
                    <label htmlFor="email">Email</label>
                    <input
                        type="email"
                        id="email"
                        name="email"
                        autoComplete="email"
                        value={newUser.email}
                        onChange={handleInputChange}
                        required
                    />
                    <br />
                    <label htmlFor="password">Password</label>
                    <input
                        type="password"
                        id="password"
                        name="password"
                        autoComplete="new-password"
                        value={newUser.password}
                        onChange={handleInputChange}
                        required
                    />
                    <br />
                    <input type="submit" value="Submit" />
                </form>
            </main>
        </>
    )
}

export default signUp;