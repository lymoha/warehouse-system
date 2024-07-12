import {Item} from "../types/Item.ts";
import "../styles/ItemCard.css"

type ItemCardProps = {
    item:Item,
}

export default function ItemCard(props:Readonly<ItemCardProps>) {
    return (
        <article className="item-card">
            <p>{props.item.name} : {props.item.amount}</p>
            <div className="card-button">
                <button>Edit</button>
                <button>Del</button>
            </div>
        </article>
    );
}