import React from "react";
import styled, { css } from "styled-components";
import { getRemainingDays2 } from "../../utils/Utils";
import generateMessage from "../KakaoMessage/messageform";

const JobInfoTableM = ({ data }) => {
  if (typeof data !== "object" || data === null) {
    return null;
  }
  const SvcClick = () => {
    const contactInfo = `${data.clerk} / ${data.clerkContt}`;
    const message = generateMessage(
      data.wantedTitle,
      data.emplymShpNm,
      data.acptMthdCd,
      contactInfo
    );

    const jsonString = JSON.stringify(message);
  };

  return (
    <JobinfoTableStyledM onClick={SvcClick}>
      <TopStyledM>
        <SvcStatStyledM status={data.deadline}>{data.deadline}</SvcStatStyledM>
        <DdayElemM>D-{getRemainingDays2(data.toDd)}</DdayElemM>
      </TopStyledM>
      <JobinfoStyledM>
        <JobinfoeNameStyledM>{data.wantedTitle}</JobinfoeNameStyledM>
        <JobinfoItemM>
          <PlaceStyledM>{data.workPlcNm}</PlaceStyledM>
          <div>접수방법 : {data.acptMthdCd} 접수</div>
          <div>모집인원 : {data.clltPrnnum}명</div>
          <div>{data.plbizNm}</div>
        </JobinfoItemM>
      </JobinfoStyledM>
    </JobinfoTableStyledM>
  );
};

const JobinfoTableStyledM = styled.div`
  width: 100%;
  max-width: 350px;
  height: 240px;
  min-width: 350px;
  border-radius: 10px;
  background-color: white;
  box-shadow: 4px 4px 20px 0px #0000001a;
  overflow: hidden;
  margin: 10px 10px;
`;

const TopStyledM = styled.div`
  display: flex;
  border-bottom: 1px solid #d3d3d3;
  padding: 10px 16px;
`;

const SvcStatStyledM = styled.div.attrs((props) => ({
  status: props.status,
}))`
  width: 60px;
  height: 32px;
  border-radius: 4px;
  text-align: center;
  color: #ffffff;
  font-weight: bold;
  background-color: #858484;
  display: flex;
  align-items: center;
  justify-content: center;

  ${({ status }) =>
    status === "안내중" &&
    css`
      background-color: #ffb287;
    `}

  ${({ status }) =>
    status === "접수중" &&
    css`
      background-color: #ff8643;
    `}

  ${({ status }) =>
    status === "일시중지" &&
    css`
      background-color: #acacac;
    `}
`;

const DdayElemM = styled.div`
  width: 60px;
  height: 32px;
  background-color: #ffb287;
  border-radius: 4px;
  top: 0;
  color: #ffffff;
  font-size: 15px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 16px;
`;

const JobinfoStyledM = styled.div`
  padding: 16px 24px;
`;

const JobinfoItemM = styled.div`
  display: flex;
  flex-direction: column;
  color: #696969;
  line-height: 27px;
  font-size: 16px;
`;

const JobinfoeNameStyledM = styled.div`
  font-size: 18px;
  font-weight: bold;
  line-height: 1.2;
  min-height: 2.4em;
  max-height: 2.4em;
  overflow: hidden;
  white-space: normal;
  margin-bottom: 4px;
`;

const PlaceStyledM = styled.span`
  display: inline-block;
  max-width: 25ch;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
`;

export default JobInfoTableM;
