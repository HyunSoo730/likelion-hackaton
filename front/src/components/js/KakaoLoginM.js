import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import Rest_api_key from "../../config/key";
import kakaoButton from "../../assets/images/KakaoLogin.png";
import daologo from "../../assets/images/daoLogo.png";
import likelionlogo from "../../assets/images/likelionlogo.png";
import "./KakaoLoginM.css"; // CSS 파일 import

function KakaoLogin() {
  const redirect_uri = "http://localhost:3000/auth/kakao/callback"; //Redirect URI
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;

  const location = useLocation();
  const navigate = useNavigate();
  const KAKAO_CODE = location.search.split("=")[1];

  useEffect(() => {
    if (!location.search) return;
    // getKakaoToken();
  }, []);

  const handleLogin = () => {
    window.location.href = kakaoURL;
    // getKakaoToken();
  };

  const handleNavigation = (path) => {
    navigate(path);
  };

  return (
    <div className="kakao_login_containerM">
      <div className="login_box">
        <div className="logo_container" onClick={() => handleNavigation("/")}>
          <img src={daologo} alt="daologo" />
          <div className="service_text">다오</div>
        </div>
        <h1 className="h1">
          간편 회원가입으로
          <br />
          서비스를 이용해보세요!
        </h1>
        <div onClick={handleLogin} className="login_buttonM">
          <img src={kakaoButton} alt="KakaoLogin" />
        </div>
      </div>
    </div>
  );
}

export default KakaoLogin;
