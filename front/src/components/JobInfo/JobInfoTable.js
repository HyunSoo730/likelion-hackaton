import React from "react";
import styled, { css } from "styled-components";
import { getRemainingDays2 } from "../../utils/Utils";

const JobInfoTable = ({ data }) => {
  if (typeof data !== "object" || data === null) {
    return null;
  }
  const SvcClick = () => {
    //window.open(data.homepage);
  };

  return (
    <JobinfoTableStyled onClick={SvcClick}>
      <TopStyled>
        <SvcStatStyled status={data.deadline}>{data.deadline}</SvcStatStyled>
        <DdayElem>D - {getRemainingDays2(data.toAcptDd)}</DdayElem>
      </TopStyled>
      <JobinfoStyled>
        <JobinfoeNameStyled>{data.wantedTitle}</JobinfoeNameStyled>
        <JobinfoItem>
          <PlaceStyled>{data.area}</PlaceStyled>
          <div>접수방법 : {data.acptMthdCd} 접수</div>
          <div>모집인원 : {data.clltPrnnum}명</div>
          <div>{data.plbizNm}</div>
        </JobinfoItem>
      </JobinfoStyled>
    </JobinfoTableStyled>
  );
};

const JobinfoTableStyled = styled.div`
  width: 100%;
  max-width: 380px;
  height: 286px;
  min-width: 380px;
  border-radius: 20px;
  background-color: white;
  box-shadow: 4px 4px 20px 0px #0000001a;
  overflow: hidden;
  margin: 10px 10px;
`;

const TopStyled = styled.div`
  display: flex;
  border-bottom: 1px solid #d3d3d3;
  padding: 16px 24px;
`;

const SvcStatStyled = styled.div.attrs((props) => ({
  status: props.status,
}))`
  width: 88px;
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

const DdayElem = styled.div`
  width: 72px;
  height: 32px;
  background-color: #ffb287;
  border-radius: 4px;
  top: 0;
  color: #ffffff;
  font-size: 20px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 16px;
`;

const JobinfoStyled = styled.div`
  padding: 16px 24px;
`;

const JobinfoItem = styled.div`
  display: flex;
  flex-direction: column;
  color: #696969;
  line-height: 40px;
  font-size: 20px;
`;

const JobinfoeNameStyled = styled.div`
  font-size: 24px;
  font-weight: bold;
  line-height: 1.2;
  min-height: 2.4em;
  max-height: 2.4em;
  overflow: hidden;
  white-space: normal;
  margin-bottom: 4px;
  color: #696969;
`;

const PlaceStyled = styled.span`
  display: inline-block;
  max-width: 25ch;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
`;

export default JobInfoTable;
