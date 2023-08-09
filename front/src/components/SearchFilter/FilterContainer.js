import { useState } from "react";
import FilterToggle from "./FilterToggle";

const FilterContainer = ({ Filters, onFilterUpdate }) => {
  const [selectedFilter, setselectedFilter] = useState(
    Array(Filters.length - 1).fill(false)
  );

  const handleAreaToggle = (index) => {
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
      filterTitle={filterTitle}
    />
  );
};

export default FilterContainer;
