import {Item} from "../types/Item.ts";
import ItemCard from "../components/ItemCard.tsx";
import Header from "../components/Header.tsx";
import "../styles/ItemGalleryPage.css"

type ItemGalleryPageProps = {
    items:Item[],
}

export default function ItemGalleryPage(props:Readonly<ItemGalleryPageProps>) {
    return (
        <>
            <Header/>
            <div className="gallery">
                {props.items.map(item => (
                    <ItemCard key={item.id} item={item}/>
                ))}
            </div>
        </>
    );
}