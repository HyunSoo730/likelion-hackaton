import React, { useState } from "react";
import "./navbarMainM.css";
import { useNavigate, useLocation } from "react-router-dom";
import userImg from "../../assets/images/user.png";
import daoLogoImg from "../../assets/images/daoLogo.png";
import logoImg from "../../assets/images/logo.jpg";

export default function NavbarMainM() {
  const navigate = useNavigate();
  const location = useLocation();

  const handleNavigation = (path) => {
    navigate(path);
  };

  const isLoggedIn = false;

  return (
    <div className="navbarMainM">
        
        <div className="navTop">
        <div className="navLogoMainM" onClick={() => handleNavigation("/")}>
          <img src={daoLogoImg} width="50px" alt="" />
          <img src={logoImg} width="60px" alt="" />
        </div>
        {!isLoggedIn && (
          <div className="navUserMainM">
            <div className="signInMainM" onClick={() => handleNavigation("/signin")}>
              로그인
            </div>
            ｜<div className="signUpMainM">회원가입</div>
          </div>
        )}
        {isLoggedIn && (
          <div className="navUserMainM" onClick={() => handleNavigation("/alarmservice")}>
            <img src={userImg} width="20px" alt="" />
            <div className="signUpMainM">하미리님</div>
          </div>
        )}
        </div>
        
        
    </div>
  );
}
