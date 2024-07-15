import {Link} from "react-router-dom";
import "../styles/Navigation.css"

export default function Navigation() {

    return (
        <ul>
            <li>
                <Link to={"/"}>Home</Link>
            </li>
            <li>
                <Link to={"/gallery"}>Gallery</Link>
            </li>
            <li>
                <Link to={"/add"}>Add</Link>
            </li>
        </ul>
    )


}