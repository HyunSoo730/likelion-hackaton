import { styled } from "styled-components";

const NoResults = () => {
  return (
    <NoResultsStyled>
      <div>검색 결과가 없습니다.</div>
    </NoResultsStyled>
  );
};

const NoResultsStyled = styled.div`
  width: 100%;
  height: 400px;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
`;

export default NoResults;
