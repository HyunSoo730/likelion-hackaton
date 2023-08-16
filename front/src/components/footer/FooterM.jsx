import React from 'react'
import './footerM.css'
import logoFooter from "../../assets/images/logoFooter.png";

export default function Footer() {
    return (
        <div className="footerM">
            <div className="logoM"><img src={logoFooter} width="70px" alt="" /></div>
                <div className="btmInfoM">
                    <div className="policyM">개인정보처리방침</div>
                    <div className="polM">(주)멋쟁이사자처럼</div>
                    <div className="polM">주소000000000</div>
                    <div className="polM">대표: OOO 연락처: 351-1234</div>
                    <div className="polM">Copyright @ ABCDEFGH</div>
                </div>
        </div>
    )
}