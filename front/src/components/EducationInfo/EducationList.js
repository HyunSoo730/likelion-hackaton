import React from "react";
import styled from "styled-components";
import EducationTable from "./EducationTable";

const EducationList = ({ EducationLists }) => {
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
          <EducationTable key={startIndex + index} data={data} />
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
  width: 80%;
`;

export default EducationList;
