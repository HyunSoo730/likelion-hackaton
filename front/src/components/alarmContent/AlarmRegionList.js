import { useState } from "react";
import { styled } from "styled-components";
import Swal from "sweetalert2";
import {
  AreaNMs,
} from "../../assets/data/AlarmFilter.data";
import FilterContainer from "../alarmContentSearch/FilterContainer";

const AlarmRegionList = () => {

    const [filterData, setFilterData] = useState({});
    const handleFilterUpdate = (filterType, selectedValues) => {
        setFilterData((prevData) => ({
            ...prevData,
            [filterType]: selectedValues,
          }));

        // const selectedCount = Object.values({
        //     ...filterData,
        //     [filterType]: selectedValues
        // }).reduce((count, values) => count + values.length, 0);

        // if (selectedCount > 3) {
        //     Swal.fire({
        //         icon: "warning",
        //         title: "최대 3곳까지</br> 선택할 수 있습니다!",
        //         html: "더 이상 추가 선택할 수 없습니다.",
        //     });
        //     setFilterData(updatedFilterData);
        //     return;
        // }
    };

  return (
    <FilterContainerStyled>
      <FilterContainer
        Filters={AreaNMs}
        onFilterUpdate={(selectedValues) =>
          handleFilterUpdate("AreaNMs", selectedValues)
        }
      />
    </FilterContainerStyled>
  );
};

const FilterContainerStyled = styled.div`
  display: flex;
`;

export default AlarmRegionList;