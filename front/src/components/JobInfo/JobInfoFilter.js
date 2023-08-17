import { useState } from "react";
import { styled } from "styled-components";
import {
  workPlcNm,
  emplymShpNm,
  deadline,
} from "../../assets/data/JobFilter.data";
import FilterContainer from "../SearchFilter/FilterContainer";

const JobInfoFilterList = ({ filterData, setFilterData }) => {
  const handleFilterUpdate = (filterType, selectedValues) => {
    const updatedFilterData = {
      ...filterData,
      [filterType]: selectedValues,
    };
    setFilterData(updatedFilterData);
    console.log("Filter Data:", updatedFilterData);
  };
  return (
    <FilterContainerStyled>
      <FilterContainer
        Filters={workPlcNm}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("area", selectedValues)
        }
      />
      <FilterContainer
        Filters={emplymShpNm}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("jobPosition", selectedValues)
        }
      />
      <FilterContainer
        Filters={deadline}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("status", selectedValues)
        }
      />
    </FilterContainerStyled>
  );
};

const FilterContainerStyled = styled.div`
  display: flex;
`;

export default JobInfoFilterList;
