import { useState } from "react";
import { styled } from "styled-components";
import {
  AreaNMs,
  SvcStatNMs,
  MaxClassNMs,
  PayAtNMs,
} from "../../assets/data/PubSvcFilter.data";
import FilterContainer from "../SearchFilter/FilterContainer";

const PubSvcFilterList = () => {
  //   const createFilterObject = (filterType, selectedValues) => {
  //     return {
  //       filterType: filterType,
  //       selectedValues: selectedValues,
  //     };
  //   };

  //   const createFilterObject = (filterType, selectedValues) => {
  //     const filterObject = {
  //       [filterType]: selectedValues,
  //     };
  //     return filterObject;
  //   };

  //   const handleFilterUpdate = (filterType, selectedValues) => {
  //     const filterObject = createFilterObject(filterType, selectedValues);
  //     console.log("Filter Object:", filterObject);
  //   };

  //handleFilterUpdate(filterTitle, selectedFilterValues);

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
        Filters={AreaNMs}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("AreaNMs", selectedValues)
        }
      />
      <FilterContainer
        Filters={SvcStatNMs}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("SvcStatNMs", selectedValues)
        }
      />
      <FilterContainer
        Filters={MaxClassNMs}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("MaxClassNMs", selectedValues)
        }
      />
      <FilterContainer
        Filters={PayAtNMs}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("PayAtNMs", selectedValues)
        }
      />
    </FilterContainerStyled>
  );
};

const FilterContainerStyled = styled.div`
  display: flex;
`;

export default PubSvcFilterList;
