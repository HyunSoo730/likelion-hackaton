import React from 'react'
import './navbar.css'
import { useNavigate } from 'react-router-dom';

export default function Navbar() {
    const navigate = useNavigate();
    
    const navigateToSignIn = () => {
        navigate("/signin");
    };

    return (
        <div className="navbar">
            <div className="navbarWrapper">
                <div className="navLogo">
                <img src={process.env.PUBLIC_URL + '/img/logo.jpg'} width = '70px' alt=''/>
                </div>
                <div className="navUser">
                    <div className="login" onclick={navigateToSignIn}>
                        로그인
                    </div>
                    ｜
                    <div className="join">
                        회원가입
                    </div>
                </div>
            </div>
        </div>
    )
}