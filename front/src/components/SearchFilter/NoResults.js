import { styled } from "styled-components";
import NoAnswer from "../../components/NoAnswer";

const NoResults = () => {
  return (
    <NoResultsStyled>
      <NoAnswer />
    </NoResultsStyled>
  );
};

const NoResultsStyled = styled.div`
  width: 100%;
  height: 60vh;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: 24px;
`;

export default NoResults;
