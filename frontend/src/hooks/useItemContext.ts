import {useContext} from "react";
import {ItemContext} from "../context/ItemContext.tsx";

export const useItemContext = () => {
    const context = useContext(ItemContext);
    if(!context) throw new Error("useItemContext musst be used within ItemProvider");
    return context;
}