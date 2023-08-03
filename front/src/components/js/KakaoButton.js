const SocialKakao = () => {
  const Rest_api_key = "2084bffabb7233adbadad5eb741dd31a"; //REST API KEY
  const redirect_uri = "http://localhost:3000/auth/kakao/callback"; //Redirect URI
  // oauth 요청 URL
  const kakaoURL = `https://kauth.kakao.com/oauth/authorize?client_id=${Rest_api_key}&redirect_uri=${redirect_uri}&response_type=code`;
  const handleLogin = () => {
    window.location.href = kakaoURL;
  };
  const code = new URL(window.location.href).searchParams.get("code");
  return (
    <>
      <button onClick={handleLogin}>카카오 로그인</button>
    </>
  );
};
export default SocialKakao;
