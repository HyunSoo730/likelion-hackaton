import React, { useState } from "react";
import "./navbarM.css";
import { useNavigate, useLocation } from "react-router-dom";
import userImg from "../../assets/images/user.png";
import daoLogoImg from "../../assets/images/daoLogo.png";
import logoImg from "../../assets/images/logo.jpg";

export default function NavbarM() {
  const navigate = useNavigate();
  const location = useLocation();

  const handleNavigation = (path) => {
    navigate(path);
  };

  const isLoggedIn = true;

  return (
    <div className="navbarM">
        
        <div className="navTop">
        <div className="navLogoM" onClick={() => handleNavigation("/")}>
          <img src={daoLogoImg} width="50px" alt="" />
          <img src={logoImg} width="60px" alt="" />
        </div>
        {!isLoggedIn && (
          <div className="navUserM">
            <div className="signInM" onClick={() => handleNavigation("/signin")}>
              로그인
            </div>
            ｜<div className="signUpM">회원가입</div>
          </div>
        )}
        {isLoggedIn && (
          <div className="navUserM" onClick={() => handleNavigation("/alarmservice")}>
            <img src={userImg} width="20px" alt="" />
            <div className="signUpM">하미리님</div>
          </div>
        )}
        </div>
        
        <div className="navBtm">
          <div
            className={`indexNameM ${
              location.pathname === "/jobinfo" ? "active" : ""
            }`}
            onClick={() => handleNavigation("/jobinfo")}
          >
            구직정보
          </div>
          <div
            className={`indexNameM ${
              location.pathname === "/publicservice" ? "active" : ""
            }`}
            onClick={() => handleNavigation("/publicservice")}
          >
            공공서비스
          </div>
          <div
            className={`indexNameM ${
              location.pathname === "/educationinfo" ? "active" : ""
            }`}
            onClick={() => handleNavigation("/educationinfo")}
          >
            교육정보
          </div>
          <div
            className={`indexNameM ${
              location.pathname === "/alarmservice" ? "active" : ""
            }`}
            onClick={() => handleNavigation("/alarmservice")}
          >
            알림
          </div>
        </div>
    </div>
  );
}
