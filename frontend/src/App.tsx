import './App.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import HomePage from "./pages/HomePage.tsx";
import AddPage from "./pages/AddPage.tsx";
import {DtoItem} from "./types/Item.ts";
import axios from "axios";


export default function App() {

    const addItem = (newItem: DtoItem) => {
        axios.post("/add", newItem )
            .catch(error => console.error("error adding item: ", error));
    };

    const router = createBrowserRouter([
        {
            path: "/",
            element: <HomePage/>
        },
        {
            path: "/add",
            element: <AddPage addItem={addItem}/>
        },
    ])

  return (
    <>
       <RouterProvider router={router}/>
    </>
  )
}


