import {DtoItem} from "../types/Item.ts";
import ItemForm from "../components/ItemForm.tsx";

type DetailPageProps = {
    updateItem: (item: DtoItem, id: string | undefined) => void;
}

export default function DetailPage(props: Readonly<DetailPageProps>) {
    return (
        <ItemForm manipulateItem={props.updateItem} />
    )

}