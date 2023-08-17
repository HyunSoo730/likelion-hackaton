import { styled } from "styled-components";

const NoInterest = () => {
  return (
    <NointerestStyled>
      <div>관심 지역을 등록해주세요.</div>
    </NointerestStyled>
  );
};

const NointerestStyled = styled.div`
  width: 100%;
  height: 65vh;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
  background: #fff0e8;
`;

export default NoInterest;
