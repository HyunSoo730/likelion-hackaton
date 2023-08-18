import { useState } from "react";
import { styled } from "styled-components";
import { area } from "../../assets/data/AlarmFilter.data";
import FilterContainer from "../alarmContentSearch/FilterContainer";

const AlarmRegionList = ({ onFilterUpdate, selectedFilters }) => {
  const [filterData, setFilterData] = useState({});
  const handleFilterUpdate = (filterType, selectedValues) => {
    const updatedFilterData = {
      ...filterData,
      [filterType]: selectedValues,
    };
    setFilterData(updatedFilterData);
    onFilterUpdate(updatedFilterData);
  };

  return (
    <FilterContainerStyled>
      <FilterContainer
        Filters={area}
        selectedValues={selectedFilters || []}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("area", selectedValues)
        }
      />
    </FilterContainerStyled>
  );
};

const FilterContainerStyled = styled.div`
  display: flex;
`;

export default AlarmRegionList;
