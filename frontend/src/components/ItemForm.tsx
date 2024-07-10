import {FormEvent, useState} from "react";

export default function ItemForm() {
    const [name, setName] = useState<string>("");
    const [amount, setAmount] = useState<number>(0);


    function handleSubmit(event: FormEvent<HTMLFormElement>) {
        event.preventDefault();



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
