import Header from "../components/Header.tsx";
import {useNavigate} from "react-router-dom";

export default function HomePage() {
    const navigate = useNavigate();
    const onButtonClick = () => navigate("/add");

    return (
        <>
            <Header/>
            <button type="button" onClick={onButtonClick}>Add</button>
        </>


    );
}
