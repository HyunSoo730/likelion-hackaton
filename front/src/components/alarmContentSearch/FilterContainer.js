import { useState } from "react";
import FilterToggle from "./FilterToggle";
import Swal from "sweetalert2";

const FilterContainer = ({ Filters, onFilterUpdate }) => {
  const [selectedFilter, setselectedFilter] = useState(
    Array(Filters.length - 1).fill(false)
  );

  const handleAreaToggle = (index) => {
  
    const selectedCount = Object.values({
      selectedFilter
    }).reduce((values) => values.length, 0);

    if (selectedCount > 2 ) {
        Swal.fire({
            icon: "warning",
            title: "최대 3곳까지</br> 선택할 수 있습니다!",
            html: "더 이상 추가 선택할 수 없습니다.",
        });
        const updatedFilter = [...selectedFilter];
        const selectedFilterValues = Filters.slice(1).filter(
          (_, idx) => updatedFilter[idx]
        );
        onFilterUpdate(selectedFilterValues);
        return;
    }


    const updatedFilter = [...selectedFilter];
    updatedFilter[index] = !updatedFilter[index];
    setselectedFilter(updatedFilter);
    
    const selectedFilterValues = Filters.slice(1).filter(
      (_, idx) => updatedFilter[idx]
    );

    // 선택한 필터 값들을 부모 컴포넌트로 전달
    onFilterUpdate(selectedFilterValues);
  };

  const filterTitle = Filters[0];

  return (
    <FilterToggle
      filterList={Filters.slice(1)}
      selectedFilter={selectedFilter}
      handleAreaToggle={handleAreaToggle}
    />
  );
};

export default FilterContainer;