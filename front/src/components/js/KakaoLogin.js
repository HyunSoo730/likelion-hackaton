import Rest_api_key from "../../config/key";
import kakaoButton from "../../assets/images/KakaoLogin.png";
import daologo from "../../assets/images/daoLogo.png";
import likelionlogo from "../../assets/images/likelionlogo.png";
import "./KakaoLogin.css";
import { useNavigate, useLocation } from "react-router-dom";

function KakaoLogin() {
  const redirect_uri = "http://localhost:3000/auth/kakao/callback";
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;

  const handleLogin = () => {
    window.location.href = kakaoURL;
  };
  const navigate = useNavigate();

  const handleNavigation = (path) => {
    navigate(path);
  };
  return (
    <div className="kakao_login_container">
      <div className="login_box">
        <div className="logo_container" onClick={() => handleNavigation("/")}>
          <img src={daologo} alt="daologo" />
          <div className="service_text">다오</div>
        </div>
        <div className="line"></div>
        <h1 className="h1">
          간편 회원가입으로
          <br />
          서비스를 이용해보세요!
        </h1>
        <div onClick={handleLogin} className="login_button">
          <img src={kakaoButton} alt="KakaoLogin" />
        </div>
        <div className="bottom">
          <div className="bottom_text">Likelion 11th</div>
          <img src={likelionlogo} alt="likelionlogo" />
        </div>
      </div>
    </div>
  );
}

export default KakaoLogin;
