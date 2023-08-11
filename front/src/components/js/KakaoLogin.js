import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

function KakaoLogin() {
  const Rest_api_key = "bf84c97be9c877da06a4bcfa274ebeb0"; //REST API KEY
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

  return <div onClick={handleLogin}>KakaoLogin</div>;
}

export default KakaoLogin;
