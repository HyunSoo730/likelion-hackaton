import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";
import Rest_api_key from "../../config/key";

function Auth() {
  const redirect_uri = "http://localhost:3000/auth/kakao/callback"; //Redirect URI
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;

  const location = useLocation();
  const navigate = useNavigate();
  const KAKAO_CODE = location.search.split("=")[1];

  const getKakaoToken = () => {
    console.log(KAKAO_CODE);

    axios
      .get("http://13.209.173.69:8080/auth/kakao/callback", {
        params: {
          code: KAKAO_CODE,
        },
      })
      .then((res) => {
        console.log(res);
        return res.data;
      })
      .then((data) => {
        const jwtToken = data.access_token; // JWT 토큰 추출
        if (jwtToken) {
          localStorage.setItem("token", jwtToken); // localStorage에 토큰 저장
        } else {
          navigate("/Auth");
        }
      })
      .catch((error) => {
        console.error("Error fetching Kakao token:", error);
        // 에러 처리 로직 추가
      });
  };

  useEffect(() => {
    getKakaoToken();
  }, []);
  return <h1>Hi</h1>;
}

export default Auth;
