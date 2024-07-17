import logo from "../assets/logo-trans.svg";
import { Link } from "react-router-dom";

function Header() {
    return(
        <>
        <header>
            <div className="header-element">
                <Link to="/">
                    <img id="logo" src={logo}/>
                </Link>
            </div>

            <div className="header-element">
                <Link to="/mydashboard">
                    <h2>My Dashboard</h2>
                </Link>

                <Link to="/signup">
                    <h2>Sign Up</h2>
                </Link>
            </div>
        </header>
        </>
    )
}

export default Header;