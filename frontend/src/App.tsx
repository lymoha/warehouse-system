import './App.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import HomePage from "./pages/HomePage.tsx";
import AddPage from "./pages/AddPage.tsx";
import {DtoItem, Item} from "./types/Item.ts";
import axios from "axios";
import {useEffect, useState} from "react";
import ItemGalleryPage from "./pages/ItemGalleryPage.tsx";
import DetailPage from "./pages/DetailPage.tsx";


export default function App() {

    const [items, setItems] = useState<Item[]>([])

    const addItem = (newItem: DtoItem) => {
        axios.post("/api/add", newItem)
            .catch(error => console.error("error adding item: ", error));
    };

    const updateItem = (item: DtoItem, id: string | undefined) => {
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

    useEffect(() => {
        getAllItems();
    }, []);

    const router = createBrowserRouter([
        {
            path: "/",
            element: <HomePage/>
        },
        {
            path: "/add",
            element: <AddPage addItem={addItem}/>
        },
        {
            path: "/gallery",
            element: <ItemGalleryPage items={items}/>
        },
        {
            path: "/update/:id",
            element: <DetailPage updateItem={updateItem}/>
        },

    ])


    return (
        <>
            <RouterProvider router={router}/>
        </>
    )
}


