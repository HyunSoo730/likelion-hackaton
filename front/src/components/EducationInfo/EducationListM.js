import React from "react";
import styled from "styled-components";
import EducationTableM from "./EducationTableM";

const EducationListM = ({ EducationLists }) => {
  if (!Array.isArray(EducationLists)) {
    return null;
  }
  const itemsPerRow = 3;
  const totalRows = Math.ceil(EducationLists.length / itemsPerRow);

  const rows = [];

  for (let i = 0; i < totalRows; i++) {
    const startIndex = i * itemsPerRow;
    const rowItems = EducationLists.slice(startIndex, startIndex + itemsPerRow);

    rows.push(
      <Row key={i} className="row">
        {rowItems.map((data, index) => (
          <EducationTableM key={startIndex + index} data={data} />
        ))}
      </Row>
    );
  }

  return <EducationListsStyled>{rows}</EducationListsStyled>;
};

const EducationListsStyled = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  height: 100vh;
`;

const Row = styled.div`
  display: flex;
  flex-direction: column; /* 추가된 부분 */

  justify-content: center;
  align-items: center;
  width: 80%;
`;

export default EducationListM;
