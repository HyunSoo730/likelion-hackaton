import { useState } from "react";
import { styled } from "styled-components";
import { ApplyState, RegistCost } from "../../assets/data/EduFilter.data";
import FilterContainer from "../SearchFilter/FilterContainer2";

const EduFilterList = ({ filterData, setFilterData }) => {
  const handleFilterUpdate = (filterType, selectedValues) => {
    let updatedFilterData = {};

    if (Array.isArray(selectedValues)) {
      updatedFilterData = {
        ...filterData,
        [filterType]: selectedValues.length > 0 ? selectedValues[0] : null,
      };
    } else {
      updatedFilterData = {
        ...filterData,
        [filterType]: selectedValues,
      };
    }
    setFilterData(updatedFilterData);
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

export default EduFilterList;
