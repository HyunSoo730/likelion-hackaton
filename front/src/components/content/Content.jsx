import React from "react";
import "./content.css";
import { useNavigate } from "react-router-dom";
import jobImg from "../../assets/images/jobImg.png";
import pblImg from "../../assets/images/pblImg.png";
import eduImg from "../../assets/images/eduImg.png";
import alrmImg from "../../assets/images/alrmImg.png";

export default function Content() {
  const navigate = useNavigate();

  const accessToken = localStorage.getItem("access_token");
  const userName = localStorage.getItem("userName");
  const isLoggedIn = accessToken && userName;

  const navigateToJobsearch = () => {
    if (isLoggedIn) navigate("/jobinfo");
    else navigate("/signin");
  };
  const navigateToPublicservice = () => {
    if (isLoggedIn) navigate("/publicservice");
    else navigate("/signin");
  };
  const navigateToEducation = () => {
    if (isLoggedIn) navigate("/educationinfo");
    else navigate("/signin");
  };
  const navigateToAlarmservice = () => {
    if (isLoggedIn) navigate("/alarmservice");
    else navigate("/signin");
  };

  return (
    <div className="content">
      <div className="form">
        <div className="enter">서비스 바로가기</div>
        <div className="svcs">
        <div className="svc">
          <div className="svcJbs" onClick={navigateToJobsearch}>
            <div className="ttl">구직정보</div>
              <img className="contentImg" src={jobImg} alt="" />
            <div className="dtl">다양한 구직 정보를 제공해 드립니다.</div>
          </div>
          <div className="svcPbl" onClick={navigateToPublicservice}>
            <div className="ttl">공공 서비스 정보</div>
              <img className="contentImg" src={pblImg} alt="" />
            <div className="dtl">
              다양한 공공 서비스 정보를 제공해 드립니다.
            </div>
          </div>
        </div>
        <div className="svc">
          <div className="svcEdu" onClick={navigateToEducation}>
            <div className="ttl">교육 정보</div>
              <img className="contentImg" src={eduImg} alt="" />
            <div className="dtl">
              취업을 위한 다양한 교육이 준비되어 있습니다.
            </div>
          </div>
          <div className="svcAlm" onClick={navigateToAlarmservice}>
            <div className="ttl">알림 서비스</div>
              <img className="contentImg" src={alrmImg} alt="" />
            <div className="dtl">
              관심 지역의 취업 정보를 알림톡으로 알려드립니다.
            </div>
          </div>
        </div>
      </div>
      </div>
    </div>
  );
}
