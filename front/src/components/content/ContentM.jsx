import React from "react";
import "./contentM.css";
import { useNavigate } from "react-router-dom";
import jobImg from "../../assets/images/jobImg.png";
import pblImg from "../../assets/images/pblImg.png";
import eduImg from "../../assets/images/eduImg.png";
import alrmImg from "../../assets/images/alrmImg.png";

export default function Content() {
  const navigate = useNavigate();

  const navigateToJobsearch = () => {
    navigate("/jobinfo");
  };
  const navigateToPublicservice = () => {
    navigate("/publicservice");
  };
  const navigateToEducation = () => {
    navigate("/educationinfo");
  };
  const navigateToAlarmservice = () => {
    navigate("/alarmservice");
  };

  return (
    <div className="contentM">
      <div className="formM">
        <div className="enterM">서비스 바로가기</div>
        <div className="svcM">
          <div className="svcJbsM" onClick={navigateToJobsearch}>
            <div className="ttlM">구직정보</div>
              <img className="contentImgM" src={jobImg} alt="" />
            <div className="dtlM">다양한 구직 정보를 제공해 드립니다.</div>
          </div>
          <div className="svcPblM" onClick={navigateToPublicservice}>
            <div className="ttlM">공공 서비스</div>
              <img className="contentImgM" src={pblImg} alt="" />
            <div className="dtlM">
              다양한 공공 서비스 정보를 제공해 드립니다.
            </div>
          </div>
        </div>
        <div className="svcM">
          <div className="svcEduM" onClick={navigateToEducation}>
            <div className="ttlM">교육 정보</div>
              <img className="contentImgM" src={eduImg} alt="" />
            <div className="dtlM">
              취업을 위한 다양한 교육이 준비되어 있습니다.
            </div>
          </div>
          <div className="svcAlmM" onClick={navigateToAlarmservice}>
            <div className="ttlM">알림 서비스</div>
              <img className="contentImgM" src={alrmImg} alt="" />
            <div className="dtlM">
              관심 지역의 취업 정보를 알림톡으로 알려드립니다.
            </div>
          </div>
        </div>
      </div>
    </div>
  );
}
