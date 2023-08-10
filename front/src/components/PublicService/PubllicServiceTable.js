import React from "react";
import styled, { css } from "styled-components";
import { getRemainingDays, decodeHTMLEntities } from "../../utils/Utils";

const PublicServiceTable = ({ serviceInfoData }) => {
  if (typeof serviceInfoData !== "object" || serviceInfoData === null) {
    return null;
  }
  const SvcClick = () => {
    window.open(serviceInfoData.svcUrl);
  };

  return (
    <ServiceInfoTableStyled onClick={SvcClick}>
      <ServiceImageStyled imageurl={serviceInfoData.imgUrl}>
        <DdayElem>D-{getRemainingDays(serviceInfoData.rcptenddt)}</DdayElem>
      </ServiceImageStyled>
      <ServiceInfoStyled>
        <ServiceInfoItem>
          <ServiceNameStyled>
            {decodeHTMLEntities(serviceInfoData.svcNM)}
          </ServiceNameStyled>
          <div>{serviceInfoData.maxClassNM}</div>
          <PlaceStyled>
            {serviceInfoData.areaNM} / {serviceInfoData.placeNM}
          </PlaceStyled>
          <div>{serviceInfoData.payAtNM}</div>
          <SvcStatStyled status={serviceInfoData.svcStatNM}>
            {serviceInfoData.svcStatNM}
          </SvcStatStyled>
        </ServiceInfoItem>
      </ServiceInfoStyled>
    </ServiceInfoTableStyled>
  );
};

const ServiceInfoTableStyled = styled.div`
  width: 300px;
  height: 400px;
  min-width: 250px;
  border-radius: 20px;
  background-color: white;
  box-shadow: 4px 4px 20px 0px #0000001a;
  overflow: hidden;
  margin: 30px 50px;
`;

const ServiceImageStyled = styled.div`
  display: flex;
  align-items: center;
  justify-content: center;
  width: 100%;
  height: 156px;
  border-radius: 20px 20px 0 0;
  background: #fff0e8;
  position: relative;
  background-image: url(${(props) => props.imageurl});
  background-position: center;
`;

const DdayElem = styled.div`
  width: 80px;
  height: 40px;
  background-color: #ffb287;
  border-radius: 0 20px 0 20px;
  position: absolute;
  top: 0;
  right: 0;
  color: #ffffff;
  font-size: 20px;
  font-weight: bold;
  display: flex;
  align-items: center;
  justify-content: center;
`;

const ServiceInfoStyled = styled.div`
  width: 100%;
  height: calc(100% - 156px);
`;

const ServiceInfoItem = styled.div`
  display: flex;
  flex-direction: column;
  padding: 24px 30px;
  color: #696969;
  line-height: 35px;
  font-size: 16px;
`;

const ServiceNameStyled = styled.div`
  font-size: 19px;
  font-weight: bold;
  line-height: 1.2;
  min-height: 2.4em;
  max-height: 2.4em;
  overflow: hidden;
  white-space: normal;
  margin-bottom: 5px;
`;

const PlaceStyled = styled.span`
  display: inline-block;
  max-width: 25ch;
  overflow: hidden;
  white-space: nowrap;
  text-overflow: ellipsis;
`;

const SvcStatStyled = styled.div.attrs((props) => ({
  status: props.status,
}))`
  width: 88px;
  height: 34px;
  border-radius: 4px;
  text-align: center;
  color: #ffffff;
  font-weight: bold;
  margin-top: 5px;
  background-color: #858484;

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

export default PublicServiceTable;
