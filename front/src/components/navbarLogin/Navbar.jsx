import React from 'react'
import './navbar.css'
import { useNavigate } from 'react-router-dom';

export default function Navbar() {

    const navigate = useNavigate();
    
    const navigateToJobsearch = () => {
        navigate("/jobsearch");
    };
    const navigateToPublicservice = () => {
        navigate("/publicservice");
    };
    const navigateToEducation = () => {
        navigate("/education");
    };
    const navigateToAlarmservice = () => {
        navigate("/alarmservice");
    };

    return (
        <div className="navbar">
            <div className="navbarWrapper">
                <div className="navLogo">
                    <img src={process.env.PUBLIC_URL + '/img/logo.jpg'} width = '70px' alt='logo image'/>
                </div>
                <div className="nav">
                    <div className="navJob" onClick={navigateToJobsearch}>
                        구직
                    </div>
                    <div className="navPbl" onClick={navigateToPublicservice}>
                        공공 서비스
                    </div>
                    <div className="navEdu" onClick={navigateToEducation}>
                        교육
                    </div>
                    <div className="navAlm" onClick={navigateToAlarmservice}>
                        알림
                    </div>
                </div>
                <div className="navUser">
                    <div className="userImg">
                    <img src={process.env.PUBLIC_URL + '/img/user.jpg'} width = '35px' alt='logo image'/>
                    </div>
                    <div className="user">
                        하미리님
                    </div>
                </div>
            </div>
        </div>
    )
}