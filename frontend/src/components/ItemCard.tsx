import {Item} from "../types/Item.ts";
import "../styles/ItemCard.css"
import {useNavigate} from "react-router-dom";

type ItemCardProps = {
    item: Item,
}

export default function ItemCard(props: Readonly<ItemCardProps>) {
    const navigate = useNavigate();
    const handleEdit = () => {
        navigate("/update/" + props.item.id);
    }
    return (
        <article className="item-card">
            <p>{props.item.name} : {props.item.amount}</p>
            <div className="card-button">
                <button onClick={handleEdit}>Edit</button>
                <button>Del</button>
            </div>
        </article>
    );
}