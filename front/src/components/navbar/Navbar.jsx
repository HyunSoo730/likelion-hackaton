import React, { useState } from "react";
import "./navbar.css";
import { useNavigate, useLocation } from "react-router-dom";
import userImg from "../../assets/images/user.png";
import daoLogoImg from "../../assets/images/daoLogo.png";
import logoImg from "../../assets/images/logo.jpg";
import axios from "axios";

export default function Navbar() {
  const navigate = useNavigate();
  const location = useLocation();

  const handleNavigation = (path) => {
    navigate(path);
  };

  const accessToken = localStorage.getItem("access_token");
  const userName = localStorage.getItem("userName");

  const isLoggedIn = accessToken && userName; 

  const handleLogout = async () => {
    try {
      const response = await axios.get("/auth/kakao/logout", {
        params: {
          accessToken: accessToken,
        },
      });

      console.log(response.data);

      if (response.status === 200) {
        localStorage.removeItem("access_token");
        localStorage.removeItem("userName");
        localStorage.removeItem("jwtToken");
        localStorage.removeItem("user_id");
        navigate("/");
      } else {
        console.error("로그아웃 실패:", response);
      }
    } catch (error) {
      console.error("로그아웃 오류:", error);
    }
  };

  return (
    <div className="navbar">
      <div className="navbarWrapper">
      <div className="navTop">
        <div className="navLogo" onClick={() => handleNavigation("/")}>
          <img src={daoLogoImg} width="50px" alt="" />
          <img src={logoImg} width="70px" alt="" />
        </div>
      </div>
      <div className={`navBtm ${
            location.pathname === "/" ? "main" : "else"
          }`}>
        {isLoggedIn && (
          <div className="indexTab">
            <div
              className={`indexName ${
                location.pathname === "/jobinfo" ? "active" : ""
              }`}
              onClick={() => handleNavigation("/jobinfo")}
            >
              구직정보
            </div>
            <div
              className={`indexName ${
                location.pathname === "/publicservice" ? "active" : ""
              }`}
              onClick={() => handleNavigation("/publicservice")}
            >
              공공서비스
            </div>
            <div
              className={`indexName ${
                location.pathname === "/educationinfo" ? "active" : ""
              }`}
              onClick={() => handleNavigation("/educationinfo")}
            >
              교육정보
            </div>
            <div
              className={`indexName ${
                location.pathname === "/alarmservice" ? "active" : ""
              }`}
              onClick={() => handleNavigation("/alarmservice")}
            >
              알림
            </div>
          </div>
        )}
      </div>
        {!isLoggedIn && (
          <div className="navUser">
            <div className="signIn" onClick={() => handleNavigation("/signin")}>
              로그인
            </div>
          </div>
        )}
        {isLoggedIn && (
          <div className="navUser">
            <img src={userImg} width="30px" alt="" />
            <div
              className="signUp"
              onClick={() => handleNavigation("/alarmservice")}
            >
              {userName}님
            </div>{" "}
            |
            <div className="signUp" onClick={handleLogout}>
              로그아웃
            </div>
          </div>
        )}
      </div>
    </div>
  );
}
