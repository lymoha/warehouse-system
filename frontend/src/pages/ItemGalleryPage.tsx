import ItemCard from "../components/ItemCard.tsx";
import Header from "../components/Header.tsx";
import "../styles/ItemGalleryPage.css"
import {useItemContext} from "../hooks/useItemContext.ts";

export default function ItemGalleryPage() {
    const {items} = useItemContext();

    return (
        <>
            <Header/>
            <div className="gallery">
                {items.map(item => (
                    <ItemCard key={item.id} item={item}/>
                ))}
            </div>
        </>
    );
}