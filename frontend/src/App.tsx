import './App.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import HomePage from "./pages/HomePage.tsx";
import AddPage from "./pages/AddPage.tsx";
import ItemGalleryPage from "./pages/ItemGalleryPage.tsx";
import DetailPage from "./pages/DetailPage.tsx";
import {ItemProvider} from "./context/ItemContext.tsx";


export default function App() {

    const router = createBrowserRouter([
        {
            path: "/",
            element: <HomePage/>
        },
        {
            path: "/add",
            element: <AddPage/>
        },
        {
            path: "/gallery",
            element: <ItemGalleryPage/>
        },
        {
            path: "/update/:id",
            element: <DetailPage/>
        }
    ])

    return (
        <ItemProvider>
            <RouterProvider router={router}/>
        </ItemProvider>
    )
}


