import React from "react";
import "./footer.css";
import logoFooter from "../../assets/images/logoFooter.png";
import {useLocation } from "react-router-dom";

export default function Footer() {
    const location = useLocation();
    
    return (
        <div className={`footer ${
            location.pathname === "/" ? "main" : "else"
          }`}>
            <div className="logo"><img src={logoFooter} width="70px" alt="" /></div>
                <div className="btmInfo">
                    <div className="policy">개인정보처리방침</div>
                    <div className="pol">(주)멋쟁이사자처럼</div>
                    <div className="pol">주소000000000</div>
                    <div className="pol">대표: OOO 연락처: 351-1234</div>
                    <div className="pol">Copyright @ ABCDEFGH</div>
                </div>
        </div>
    )
}
