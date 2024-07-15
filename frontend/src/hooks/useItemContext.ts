import {createContext, useContext} from "react";
import {ItemContextType} from "../context/ItemContext.tsx";

export const ItemContext = createContext<ItemContextType | undefined>(undefined);

export const useItemContext = () => {
    const context = useContext(ItemContext);
    if(!context) throw new Error("useItemContext musst be used within ItemProvider");
    return context;
}

