import React from "react";
import { useMediaQuery } from "react-responsive";
import KakaoLogin from "../components/js/KakaoLogin";
import KakaoLoginM from "../components/js/KakaoLoginM";

function SignIn() {
  const isMobile = useMediaQuery({ query: "(max-width: 768px)" });

  return (
    <div>
      {isMobile ? (
        <div>
          {/* Mobile-specific content */}
          <KakaoLoginM />
        </div>
      ) : (
        <div>
          {/* Desktop-specific content */}
          <KakaoLogin />
        </div>
      )}
    </div>
  );
}

export default SignIn;
