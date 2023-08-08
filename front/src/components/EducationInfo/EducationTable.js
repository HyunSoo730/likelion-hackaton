import React from "react";
import styled, { css } from "styled-components";
import { getRemainingDays, decodeHTMLEntities } from "../../utils/Utils";

const EducationTable = ({ data }) => {
  if (typeof data !== "object" || data === null) {
    return null;
  }
  const SvcClick = () => {
    window.open(data.svcUrl);
  };

  const registCostText =
    data.registCost === "0" ? "무료" : `${data.registCost}원`;

  return (
    <EducationTableStyled onClick={SvcClick}>
      <TopStyled>
        <SvcStatStyled status={data.applyState}>
          {data.applyState}
        </SvcStatStyled>
        <DdayElem>D-{getRemainingDays(data.applicationEndDate)}</DdayElem>
      </TopStyled>
      <EducationStyled>
        <EducationeNameStyled>
          {decodeHTMLEntities(data.subject)}
        </EducationeNameStyled>
        <EducationItem>
          <div>
            신청기간 : {data.startDate} ~ {data.endDate}
          </div>
          <div>
            교육기간 : {data.applicationStartDate} ~ {data.applicationEndDate}
          </div>
          <div>{data.maxClassNM}</div>
          <PlaceStyled>수강정원 : {data.registPeople}명</PlaceStyled>
          <div>교육비용 : {registCostText}</div>
        </EducationItem>
      </EducationStyled>
    </EducationTableStyled>
  );
};

const EducationTableStyled = styled.div`
  width: 380px;
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

const EducationStyled = styled.div`
  padding: 16px 24px;
`;

const EducationItem = styled.div`
  display: flex;
  flex-direction: column;
  color: #696969;
  line-height: 35px;
  font-size: 20px;
`;

const EducationeNameStyled = styled.div`
  font-size: 24px;
  font-weight: bold;
  line-height: 1.2;
  min-height: 2.4em;
  max-height: 2.4em;
  overflow: hidden;
  white-space: normal;
`;

const PlaceStyled = styled.span`
  display: inline-block;
  max-width: 25ch;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
`;

export default EducationTable;
