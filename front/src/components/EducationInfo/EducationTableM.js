import React from "react";
import styled, { css } from "styled-components";
import { getRemainingDays, decodeHTMLEntities } from "../../utils/Utils";

const EducationTableM = ({ data }) => {
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
            신청기간 : {data.startDate} ~ {data.endDate} | 수강정원 :{" "}
            {data.registPeople}명
          </div>
          <div>
            교육기간 : {data.applicationStartDate} ~ {data.applicationEndDate} |
            교육비용 : {registCostText}
          </div>
          <div>{data.maxClassNM}</div>
        </EducationItem>
      </EducationStyled>
    </EducationTableStyled>
  );
};

const EducationTableStyled = styled.div`
  width: 334px;
  height: 156px;
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
  width: 42px;
  height: 20px;
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
      font-size: 10px;
    `}

  ${({ status }) =>
    status === "일시중지" &&
    css`
      background-color: #acacac;
    `}
`;

const DdayElem = styled.div`
  width: 42px;
  height: 20px;
  background-color: #ffb287;
  border-radius: 4px;
  top: 0;
  color: #ffffff;
  font-size: 10px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
  margin-left: 8px;
`;

const EducationStyled = styled.div`
  padding: 16px 24px;
`;

const EducationItem = styled.div`
  display: flex;
  flex-direction: column;
  color: #696969;
  line-height: 20px;
  font-size: 12px;
`;

const EducationeNameStyled = styled.div`
  font-size: 16px;
  font-weight: bold;
  line-height: 1.2;
  min-height: 2.4em;
  max-height: 2.4em;
  overflow: hidden;
  white-space: normal;
`;

export default EducationTableM;
