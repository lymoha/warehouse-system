import Navigation from "./Navigation.tsx"
import "../styles/Header.css"

export default function Header() {

    return (
        <div className="header-container">
            <h1 className="app-header">Room Ranger 3000</h1>
            <h2 className="app-header-2">Clean up your life</h2>
            <Navigation/>
        </div>
    );
}
