import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import Rest_api_key from "../../config/key";
import kakaoButton from "../../assets/images/KakaoLogin.png";
import daologo from "../../assets/images/daoLogo.png";
import "./KakaoLogin.css"; // CSS 파일 import

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

  console.log("test");

  const handleLogin = () => {
    window.location.href = kakaoURL;
    // getKakaoToken();
  };

  return (
    <div className="kakao_login_container">
      <div className="logo_container">
        <img src={daologo} alt="daologo" />
        <div className="service_text">다오</div>
      </div>
      <div className="line"></div>
      <div onClick={handleLogin} className="login_button">
        <img src={kakaoButton} alt="KakaoLogin" />
      </div>
    </div>
  );
}

export default KakaoLogin;
