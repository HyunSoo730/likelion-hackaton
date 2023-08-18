import React from "react";
import styled, { css } from "styled-components";
import { getRemainingDays2 } from "../../utils/Utils";
import generateMessage from "../KakaoMessage/messageform";
import { axiosMessage } from "../../api/axios/axios.Alarm";
import ApplicateModal from "./ApplicateModal";
import HeartModal from "./HeartModal";
import heartOff from "../../assets/images/heartOff.png";
import heartOn from "../../assets/images/heartOn.png";
import { useState } from "react";

const JobInfoTable = ({ data }) => {
  const [isOpen, setIsOpen] = useState(false);
  const [isOpenHeart, setIsOpenHeart] = useState(false);

  const onClickButton = () => {
    setIsOpen(true);
  };

  if (typeof data !== "object" || data === null) {
    return null;
  }

  const onClickHeart = () => {
    if (!isOpenHeart) {
      setIsOpenHeart(true);
    }
  };

  const SvcClick = () => {
    const contactInfo = `${data.clerk} / ${data.clerkContt}`;
    const access_token = localStorage.getItem("access_token");
    const message = generateMessage(
      data.wantedTitle,
      data.emplymShpNm,
      data.acptMthdCd,
      contactInfo
    );

    const jsonString = JSON.stringify(message);

    axiosMessage(access_token, jsonString)
      .then((response) => {
        console.log("Response:", response);
        onClickHeart();
      })
      .catch((error) => {
        console.error("Error:", error);
      });
    setIsOpenHeart(false);
  };

  return (
    <JobinfoTableStyled>
      <TopStyled>
        <TopStyledLeft>
          <SvcStatStyled status={data.deadline}>{data.deadline}</SvcStatStyled>
          <DdayElem>D - {getRemainingDays2(data.toAcptDd)}</DdayElem>
          <Heart onClick={onClickHeart}>
            <img src={heartOn} alt="" />
          </Heart>
          {isOpenHeart && (
            <HeartModal
              open={isOpenHeart}
              onClose={() => {
                setIsOpenHeart(false);
              }}
              SvcClick={SvcClick}
            />
          )}
        </TopStyledLeft>

        {isOpen && (
          <ApplicateModal
            open={isOpen}
            onClose={() => {
              setIsOpen(false);
            }}
            data={data}
          />
        )}
      </TopStyled>
      <JobinfoStyled onClick={onClickButton}>
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
  @media (max-width: 768px) {
    max-width: 350px;
    height: 240px;
    min-width: 350px;
    border-radius: 10px;
  }
  @media (min-width: 769px) {
    max-width: 380px;
    height: 286px;
    min-width: 380px;
    border-radius: 20px;
  }
  width: 100%;
  background-color: white;
  box-shadow: 4px 4px 20px 0px #0000001a;
  overflow: hidden;
  margin: 10px 10px;
`;

const TopStyled = styled.div`
  display: flex;
  border-bottom: 1px solid #d3d3d3;
  justify-content: space-between;
  @media (max-width: 768px) {
    padding: 10px 16px;
  }
  @media (min-width: 769px) {
    padding: 16px 24px;
  }
`;

const TopStyledLeft = styled.div`
  display: flex;
`;

const SvcStatStyled = styled.div.attrs((props) => ({
  status: props.status,
}))`
  @media (max-width: 768px) {
    width: 55px;
    height: 31px;
    font-size: 13px;
  }
  @media (min-width: 769px) {
    width: 68px;
    height: 32px;
    font-size: 17px;
  }
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
  @media (max-width: 768px) {
    width: 60px;
    font-size: 13px;
    height: 31px;
    margin-left: 6px;
  }
  @media (min-width: 769px) {
    width: 60px;
    font-size: 17px;
    height: 32px;
    margin-left: 10px;
  }
  background-color: #ffb287;
  border-radius: 4px;
  top: 0;
  color: #ffffff;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const Heart = styled.div`
  display: flex;
  @media (max-width: 768px) {
    width: 100px;
    margin-left: 160px;
  }
  @media (min-width: 769px) {
    margin-left: 165px;
  }
`;

const Applicate = styled.div`
  @media (max-width: 768px) {
    width: 63px;
    height: 31px;
    font-size: 13px;
  }
  @media (min-width: 769px) {
    width: 80px;
    height: 32px;
    font-size: 17px;
  }
  align-items: right;
  border-radius: 4px;
  text-align: center;
  color: #ffffff;
  font-weight: bold;
  background-color: #ff6c1b;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const JobinfoStyled = styled.div`
  padding: 16px 24px;
`;

const JobinfoItem = styled.div`
  display: flex;
  flex-direction: column;
  color: #696969;
  @media (max-width: 768px) {
    line-height: 27px;
    font-size: 16px;
  }
  @media (min-width: 769px) {
    line-height: 35px;
    font-size: 20px;
  }
`;

const JobinfoeNameStyled = styled.div`
  @media (max-width: 768px) {
    font-size: 18px;
  }
  @media (min-width: 769px) {
    font-size: 24px;
  }
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
