import { useState } from "react";
import { styled } from "styled-components";
import {
  AreaNMs,
  SvcStatNMs,
  MaxClassNMs,
  PayAtNMs,
} from "../../assets/data/PubSvcFilter.data";
import FilterContainer from "../SearchFilter/FilterContainer";

const PubSvcFilterList = ({ filterData, setFilterData }) => {
  const handleFilterUpdate = (filterType, selectedValues) => {
    const updatedFilterData = {
      ...filterData,
      [filterType]: selectedValues,
    };
    setFilterData(updatedFilterData);
  };
  return (
    <FilterContainerStyled>
      <FilterContainer
        Filters={AreaNMs}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("areaNM", selectedValues)
        }
      />
      <FilterContainer
        Filters={SvcStatNMs}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("svcStatNM", selectedValues)
        }
      />
      <FilterContainer
        Filters={MaxClassNMs}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("maxClassNM", selectedValues)
        }
      />
      <FilterContainer
        Filters={PayAtNMs}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("payAtNM", selectedValues)
        }
      />
    </FilterContainerStyled>
  );
};

const FilterContainerStyled = styled.div`
  display: flex;
`;

export default PubSvcFilterList;
