import {FormEvent, useState} from "react";
import '../styles/ItemForm.css'
import {useLocation, useNavigate, useParams} from "react-router-dom";
import {useItemContext} from "../hooks/useItemContext.ts";
import {Item} from "../types/Item.ts";

export default function ItemForm() {
    const {addItem, updateItem, items} = useItemContext();

    // ID aus url
    const urlParams = useParams()
    const id:string = urlParams.id || "";

    const currentItem: Item | undefined = items.find(item => item.id === urlParams.id)

    const [name, setName] = useState<string>(currentItem ? currentItem.name : "");
    const [amount, setAmount] = useState<number>(currentItem ? currentItem.amount : 0);
    const navigate = useNavigate();
    const location = useLocation();


    function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();

        if (location.pathname === ("/add")) {
            addItem({name: name, amount: amount});
            navigate("/")
        } else if(location.pathname === ("/update/" + id)) {
             updateItem({name: name, amount: amount}, id);
            navigate("/gallery")
        }
        setName("");
        setAmount(0);
        console.log(location.pathname)
    }

    function handleCancel() {
        if (location.pathname === ("/add")) {
            navigate("/")
        } else if(location.pathname === ("/update/" + id)) {
            navigate("/gallery")
        }
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
