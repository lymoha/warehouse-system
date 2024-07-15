import {DtoItem, Item} from "../types/Item.ts";
import {createContext, FC, ReactNode, useEffect, useState} from "react";
import axios from "axios";

type ItemContextType = {
    items: Item[];
    getAllItems: () => void;
    updateItem: (newItem:DtoItem, id:string) => void;
    addItem: (newItem:DtoItem) => void;
    deleteById: (id:string) => void;
}

export const ItemContext = createContext<ItemContextType | undefined>(undefined);

export const ItemProvider: FC<{children:ReactNode}> = ({children}) => {

    const [items, setItems] = useState<Item[]>([])

    const addItem = (newItem: DtoItem) => {
        axios.post("/api/add", newItem)
            .then(getAllItems)
            .catch(error => console.error("error adding item: ", error));
    };

    const updateItem = (item: DtoItem, id: string) => {
        axios.put("/api/update/" + id, item)
            .then(getAllItems)
            .catch(error => console.error("Error updating item: ", error));
    }

    const getAllItems = () => {
        axios.get("/api")
            .then(response => {
                setItems(response.data)
            })
            .catch(error => console.error("something went wrong", error))
    };

    const deleteById = (id:string) => {
        axios.delete('/api/'+ id)
            .then(getAllItems)
            .catch(error => console.error("something went wrong", error))
    }

    useEffect(() => {
        getAllItems();
    }, []);

    return(
        <ItemContext.Provider value={{items, addItem, updateItem, getAllItems, deleteById}}>
            {children}
        </ItemContext.Provider>
    )
}