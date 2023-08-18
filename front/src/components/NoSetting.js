import React from "react";
import "./NoSetting.css";

function NoSetting() {
  return (
    <div className="outer">
      <div className="inner">
        <img
          src={process.env.PUBLIC_URL + "/MaskGroup.png"}
          alt="이미지 설명"
        />
        <p>지역이 등록되어 있지 않아요</p>
        <h5>“알림" 페이지로 이동해서 관심 지역을 설정해보세요!</h5>
      </div>
    </div>
  );
}

export default NoSetting;
