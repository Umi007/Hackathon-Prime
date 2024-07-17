import { useEffect, useState } from "react";

function signUp() {

    return (
        <>
            <main>
                <form action="/api/users" method="post">
                    <label htmlFor="username">Username</label>
                    <input type="text" id="username" name="username" autoComplete="username" required/>
                    <br />
                    <label htmlFor="email">Email</label>
                    <input type="email" id="email" name="email" autoComplete="email" required/>
                    <br />
                    <label htmlFor="password">Password</label>
                    <input type="password" id="password" name="password" autoComplete="new-password" required/>
                    <br />
                    <input type="submit" value="Submit"/>
                </form>
            </main>
        </>
    )
}

export default signUp;