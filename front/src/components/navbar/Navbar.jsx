import React from 'react'
import './navbar.css'
import { useNavigate } from 'react-router-dom';

export default function Navbar() {
    const navigate = useNavigate();

    const navigateToMain = () => {
        navigate("/");
    }; 

    const navigateToSignIn = () => {
        navigate("/SignIn");
    };   

    return (
        <div className="navbar">
            <div className="navbarWrapper">
                <div className="navLogo" onClick={navigateToMain}>
                <img src={process.env.PUBLIC_URL + '/img/logo.jpg'} width = '70px' alt=''/>
                </div>
                <div className="navUser">
                    <div className="signIn" onClick={navigateToSignIn}>
                        로그인
                    </div>
                    ｜
                    <div className="signUp">
                        회원가입
                    </div>
                </div>
            </div>
        </div>
    )
}