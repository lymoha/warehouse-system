import {Item} from "../types/Item.ts";
import "../styles/ItemCard.css"
import {useNavigate} from "react-router-dom";
import axios from "axios";

type ItemCardProps = {
    item: Item
}

export default function ItemCard(props: Readonly<ItemCardProps>) {
    const navigate = useNavigate();

    const deleteById = (id:string) => {
        axios.delete('api/'+ id)
            .catch(error => console.error("something went wrong", error))
    }

    const handleEdit = () => {
        navigate("/update/" + props.item.id);
    }

    const handleDel = () => {
        deleteById(props.item.id)
        navigate("/gallery");
    }
    return (
        <article className="item-card">
            <p>{props.item.name} : {props.item.amount}</p>
            <div className="card-button">
                <button onClick={handleEdit}>Edit</button>
                <button onClick={handleDel}>Del</button>
            </div>
        </article>
    );
}