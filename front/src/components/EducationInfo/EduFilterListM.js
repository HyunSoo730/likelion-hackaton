import { useState } from "react";
import { styled } from "styled-components";
import { ApplyState, RegistCost } from "../../assets/data/EduFilter.data";
import FilterContainer from "../SearchFilter/FilterContainer2M";

const EduFilterListM = () => {
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
        Filters={ApplyState}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("applyState", selectedValues)
        }
      />
      <FilterContainer
        Filters={RegistCost}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("registCost", selectedValues)
        }
      />
    </FilterContainerStyled>
  );
};

const FilterContainerStyled = styled.div`
  display: flex;
`;

export default EduFilterListM;
