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
        {rowItems.map((serviceInfoData, index) => (
          <JobInfoTable key={startIndex + index} data={serviceInfoData} />
        ))}
      </Row>
    );
  }

  return <JobInfoListsStyled>{rows}</JobInfoListsStyled>;
};

const JobInfoListsStyled = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  height: 100vh;
`;

const Row = styled.div`
  display: flex;
  width: 65%;
`;

export default JobInfoList;
