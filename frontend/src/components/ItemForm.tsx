import {FormEvent, useState} from "react";
import {DtoItem} from "../types/Item.ts";

type ItemFormProps = {
    addItem: (item: DtoItem) => void
}

export default function ItemForm(props: Readonly<ItemFormProps>) {
    const [name, setName] = useState<string>("");
    const [amount, setAmount] = useState<number>(0);



    function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();
        props.addItem({name: name, amount: amount});
        setName("");
        setAmount(0);


    }

    return (
        <>
            <form onSubmit={(event) => handleSubmit(event)}>
                <label>Item name:
                    <input type="text" value={name} onChange={(e) => setName(e.target.value)}/></label>
                <label>Item amount:
                    <input type="number" value={amount} onChange={(e) => setAmount(Number(e.target.value))}/></label>
                <button>Add</button>

            </form>

        </>
    );
}
