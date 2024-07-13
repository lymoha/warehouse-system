import {DtoItem} from "../types/Item.ts";
import ItemForm from "../components/ItemForm.tsx";

type AddPageProps= {
    addItem: (item: DtoItem) => void
}

export default function AddPage(props: Readonly<AddPageProps>) {
    return (
        <ItemForm manipulateItem={props.addItem}/>
    );
}
