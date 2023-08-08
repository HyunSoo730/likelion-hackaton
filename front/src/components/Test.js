import { useEffect } from "react";
import { useLocation, useNavigate } from "react-router-dom";
import axios from "axios";

function Auth() {
  const Rest_api_key = "bf84c97be9c877da06a4bcfa274ebeb0"; //REST API KEY
  const redirect_uri = "http://localhost:3000/auth/kakao/callback"; //Redirect URI
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;

  const location = useLocation();
  const navigate = useNavigate();
  const KAKAO_CODE = location.search.split("=")[1];

  console.log(KAKAO_CODE);

  const getKakaoToken = () => {
    axios
      .get("http://13.209.173.69:8080/auth/kakao/callback", {
        params: {
          code: KAKAO_CODE,
        },
      })
      // .then((res) => res.json())
      .then((res) => console.log(res))
      .then((data) => {
        if (data.access_token) {
          localStorage.setItem("token", data.access_token);
        } else {
          navigate("/");
        }
      });
  };
  useEffect(() => {
    getKakaoToken();
  }, []);
  return <h1>Hi</h1>;
}

export default Auth;