import React from "react";
import "./NoAnswer.css";

function NoAnswer() {
  return (
    <div className="container">
      <div className="imageContainer">
        <img
          src={process.env.PUBLIC_URL + "/MaskGroup.png"}
          alt="이미지 설명"
        />
      </div>
      <div className="contentContainer">
        조건에 맞는 검색 결과가 없어요.
        <div className="small">
          이건 어때요? <br></br>1. 검색 조건을 줄여보세요.<br></br>2. 단어의
          철자가 정확한지 확인해주세요.
        </div>
      </div>
    </div>
  );
}

export default NoAnswer;
