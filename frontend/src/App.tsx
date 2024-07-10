import './App.css'
import {createBrowserRouter, RouterProvider} from "react-router-dom";
import HomePage from "./pages/HomePage.tsx";
import AddPage from "./pages/AddPage.tsx";


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
    ])

  return (
    <>
       <RouterProvider router={router}/>
    </>
  )
}


