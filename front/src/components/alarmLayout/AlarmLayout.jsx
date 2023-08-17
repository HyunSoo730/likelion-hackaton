import React from "react";
import "./alarmLayout.css";
// const INTRO_IMAGE = '/img/layoutBgrd.jpg'

export default function AlarmLayout() {
  return (
    <div className="alarmLayout">
      {/* <img className="bgrd" src={process.env.PUBLIC_URL + '/img/layoutBgrd.jpg'} alt='logo image'/> */}
      {/* <img src={INTRO_IMAGE} alt="intro picture" /> */}
      <div className="alarmTitle">
        관심지역의 채용공고 알림 서비스를 신청하세요.
      </div>
      <div className="alarmInfo">
        관심지역에 새로운 공고가 올라올 때마다 서비스를 이용하시는 분들께
        <br></br>
        카카오톡 문자로 안내하여 더 빠르고 간편하게 일자리를 얻으실 수 있도록
        <br></br>
        도와드리기 위해 시행하는 서비스입니다.
      </div>
    </div>
  );
}
