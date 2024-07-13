import {FormEvent, useState} from "react";
import {DtoItem} from "../types/Item.ts";
import '../styles/ItemForm.css'
import {useNavigate} from "react-router-dom";

type ItemFormProps = {
    manipulateItem: (item: DtoItem, id?: string) => void
}

export default function ItemForm(props: Readonly<ItemFormProps>) {
    const [name, setName] = useState<string>("");
    const [amount, setAmount] = useState<number>(0);
    const navigate = useNavigate();


    function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        props.manipulateItem({name: name, amount: amount});
        setName("");
        setAmount(0);


    }

    function handleCancel() {
        navigate("/");
    }

    return (
        <>
            <form className="item-form" onSubmit={(event) => handleSubmit(event)}>

                <label className="item-label">Name:</label>
                <input className="item-input" type="text" value={name}
                       onChange={(e) => setName(e.target.value)}/>
                <label className="item-label">Amount: </label>
                <input className="item-input" type="number" value={amount}
                       onChange={(e) => setAmount(Number(e.target.value))}/>
                <button>OK</button>
                <button type="button" onClick={handleCancel}>Cancel</button>
            </form>

        </>
    );
}
