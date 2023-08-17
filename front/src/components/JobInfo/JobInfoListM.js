import React from "react";
import styled from "styled-components";
import JobInfoTableM from "./JobInfoTableM";

import { useMediaQuery } from "react-responsive";

const JobInfoListM = ({ JobInfoLists }) => {
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
          <JobInfoTableM key={startIndex + index} data={data} />
        ))}
      </Row>
    );
  }

  return <JobInfoListsStyled>{rows}</JobInfoListsStyled>;
};

const JobInfoListsStyled = styled.div`
  display: static;
  flex-wrap: wrap;
  justify-content: center;
  flex-direction: column;
  align-items: center;
  height: 74vh;
`;

const Row = styled.div`
  display: static;
  justify-content: center;
  align-items: center;
  width: 80%;
`;

export default JobInfoListM;
