import React from "react";
import styled from "styled-components";
import PublicServiceTable from "./PubllicServiceTable";

const PublicServiceList = ({ publicServiceLists }) => {
  if (!Array.isArray(publicServiceLists)) {
    return null;
  }
  const itemsPerRow = 3;
  const totalRows = Math.ceil(publicServiceLists.length / itemsPerRow);

  const rows = [];

  for (let i = 0; i < totalRows; i++) {
    const startIndex = i * itemsPerRow;
    const rowItems = publicServiceLists.slice(
      startIndex,
      startIndex + itemsPerRow
    );

    rows.push(
      <Row key={i} className="row">
        {rowItems.map((serviceInfoData, index) => (
          <PublicServiceTable
            key={startIndex + index}
            serviceInfoData={serviceInfoData}
          />
        ))}
      </Row>
    );
  }

  return <PublicServiceListsStyled>{rows}</PublicServiceListsStyled>;
};

const PublicServiceListsStyled = styled.div`
  display: flex;
  flex-wrap: wrap;
  justify-content: center;
  align-items: center;
  height: 100vh;
`;

const Row = styled.div`
  display: flex;
  width: 80%;
`;

export default PublicServiceList;
