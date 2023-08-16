import React from "react";
import "./content.css";
import AssignmentIndIcon from "@mui/icons-material/AssignmentInd";
import Diversity3Icon from "@mui/icons-material/Diversity3";
import NotificationsActiveIcon from "@mui/icons-material/NotificationsActive";
import MenuBookIcon from "@mui/icons-material/MenuBook";
import { useNavigate } from "react-router-dom";

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
        <div className="svc">
          <div className="svcJbs" onClick={navigateToJobsearch}>
            <div className="ttl">구직정보</div>
            <div className="icon">
              <AssignmentIndIcon />
            </div>
            <div className="dtl">다양한 구직 정보를 제공해 드립니다.</div>
          </div>
          <div className="svcPbl" onClick={navigateToPublicservice}>
            <div className="ttl">공공 서비스 정보</div>
            <div className="icon">
              <Diversity3Icon />
            </div>
            <div className="dtl">
              다양한 공공 서비스 정보를 제공해 드립니다.
            </div>
          </div>
          <div className="svcEdu" onClick={navigateToEducation}>
            <div className="ttl">교육 정보</div>
            <div className="icon">
              <MenuBookIcon />
            </div>
            <div className="dtl">
              취업을 위한 다양한 교육이 준비되어 있습니다.
            </div>
          </div>
          <div className="svcAlm" onClick={navigateToAlarmservice}>
            <div className="ttl">알림 서비스</div>
            <div className="icon">
              <NotificationsActiveIcon />
            </div>
            <div className="dtl">
              관심 지역의 취업 정보를 알림톡으로 알려드립니다.
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
