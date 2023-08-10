import { useState } from "react";
import { styled } from "styled-components";
import {
  workPlcNm,
  emplymShpNm,
  deadline,
} from "../../assets/data/JobFilter.data";
import FilterContainer from "../SearchFilter/FilterContainer";

const JobInfoFilterList = () => {
  const [filterData, setFilterData] = useState({});
  const handleFilterUpdate = (filterType, selectedValues) => {
    setFilterData((prevData) => ({
      ...prevData,
      [filterType]: selectedValues,
    }));
    console.log("Filter Data:", filterData);
  };
  return (
    <FilterContainerStyled>
      <FilterContainer
        Filters={workPlcNm}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("AreaNMs", selectedValues)
        }
      />
      <FilterContainer
        Filters={emplymShpNm}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("SvcStatNMs", selectedValues)
        }
      />
      <FilterContainer
        Filters={deadline}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("MaxClassNMs", selectedValues)
        }
      />
    </FilterContainerStyled>
  );
};

const FilterContainerStyled = styled.div`
  display: flex;
`;

export default JobInfoFilterList;
