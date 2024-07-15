import ItemForm from "../components/ItemForm.tsx";
import '../styles/PageContainer.css'
import Header from "../components/Header.tsx";

export default function AddPage() {


    return (
        <div className={"page-container"}>
            <Header/>
            <ItemForm/>

        </div>

    );
}
