import Header from "../components/Header.tsx";
import DummyItem from "../components/DummyItem.tsx";
import "../styles/PageContainer.css"

export default function HomePage() {
    return (
        <div className={"page-container"}>
            <Header/>
            <DummyItem/>
        </div>


    );
}
