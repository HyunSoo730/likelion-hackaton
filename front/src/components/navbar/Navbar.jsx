import React, { useState } from "react";
import "./navbar.css";
import { useNavigate, useLocation } from "react-router-dom";
import userImg from "../../assets/images/user.png";
import daoLogoImg from "../../assets/images/daoLogo.png";
import logoImg from "../../assets/images/logo.jpg";

export default function Navbar() {
  const navigate = useNavigate();
  const location = useLocation();

  const handleNavigation = (path) => {
    navigate(path);
  };

  const isLoggedIn = true;

  return (
    <div className="navbar">
      <div className="navbarWrapper">
        <div className="navLogo" onClick={() => handleNavigation("/")}>
          <img src={daoLogoImg} width="50px" alt="" />
          <img src={logoImg} width="70px" alt="" />
        </div>
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
        {!isLoggedIn && (
          <div className="navUser">
            <div className="signIn" onClick={() => handleNavigation("/signin")}>
              로그인
            </div>
            ｜<div className="signUp">회원가입</div>
          </div>
        )}
        {isLoggedIn && (
          <div className="navUser" onClick={() => handleNavigation("/alarmservice")}>
            <img src={userImg} width="30px" alt="" />
            <div className="signUp">하미리님</div>
          </div>
        )}
      </div>
    </div>
  );
}
