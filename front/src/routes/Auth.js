import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import Rest_api_key from "../config/key";

const Auth = () => {
  const navigate = useNavigate();

  useEffect(() => {
    const params = new URL(document.location.toString()).searchParams;
    const code = params.get("code");
    const grantType = "authorization_code";
    const redirect_uri = "http://13.209.173.69:3000/auth/kakao/callback";

    axios
      .post(
        `https://kauth.kakao.com/oauth/token?grant_type=${grantType}&client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&code=${code}`,
        {},
        {
          headers: {
            "Content-type": "application/x-www-form-urlencoded;charset=utf-8",
          },
        }
      )
      .then((res) => {
        const { access_token } = res.data;
        axios
          .post(
            `https://kapi.kakao.com/v2/user/me`,
            {},
            {
              headers: {
                Authorization: `Bearer ${access_token}`,
                "Content-type":
                  "application/x-www-form-urlencoded;charset=utf-8",
              },
            }
          )
          .then((res) => {
            const userName = res.data.properties.nickname;
            const user_id = res.data.id;
            localStorage.setItem("access_token", access_token);
            localStorage.setItem("userName", userName);
            localStorage.setItem("user_id", user_id);

            const response = axios.get("/auth/kakao/callback/access", {
              params: {
                accessToken: access_token,
              },
            });
            const jwtToken = response.data;
            localStorage.setItem("jwtToken", jwtToken);

            navigate("/");
          });
      })
      .catch((Error) => {
        console.log(Error);
      });
  }, []);

  return <h1>로그인 중입니다</h1>;
};

export default Auth;
