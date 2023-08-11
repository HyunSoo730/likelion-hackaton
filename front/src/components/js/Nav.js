import { Link } from "react-router-dom";
import Navigation from "../css/Nav.module.css";

function Nav() {
  return (
    <header className={Navigation.header}>
      <div>
        <Link to="/" className={Navigation.linkStyle}>
          <h1>Dao</h1>
        </Link>
      </div>
      <nav>
        <span>구직</span>
        <span>공공서비스</span>
        <span>알림</span>
      </nav>
      <nav>
        <Link to="/signin" className={Navigation.linkStyle}>
          로그인
        </Link>{" "}
        <span style={{ color: "#CCCCCC" }}>|</span>{" "}
        <Link to="/signup" className={Navigation.linkStyle}>
          회원가입
        </Link>
      </nav>
    </header>
  );
}

export default Nav;
