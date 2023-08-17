import React from "react";
import styled from "styled-components";
import JobInfoTable from "./JobInfoTable";

const JobInfoList = ({ JobInfoLists }) => {
  if (!Array.isArray(JobInfoLists)) {
    return null;
  }
  const itemsPerRow = 3;
  const totalRows = Math.ceil(JobInfoLists.length / itemsPerRow);

  const rows = [];

  for (let i = 0; i < totalRows; i++) {
    const startIndex = i * itemsPerRow;
    const rowItems = JobInfoLists.slice(startIndex, startIndex + itemsPerRow);

    rows.push(
      <Row key={i} className="row">
        {rowItems.map((data, index) => (
          <JobInfoTable key={startIndex + index} data={data} />
        ))}
      </Row>
    );
  }

  return <JobInfoListsStyled>{rows}</JobInfoListsStyled>;
};

const JobInfoListsStyled = styled.div`
  @media (max-width: 768px){
    display: static;
    height: 74vh;
  }
  @media (min-width: 769px){
    display: flex;
    height: 100vh;
  }
  flex-wrap: wrap;
  justify-content: center;
  flex-direction: column;
  align-items: center;
`;

const Row = styled.div`
@media (max-width: 768px){
  display: static;
}
@media (min-width: 769px){
  display: flex;
}
  justify-content: center;
  align-items: center;
  width: 80%;
`;

export default JobInfoList;
