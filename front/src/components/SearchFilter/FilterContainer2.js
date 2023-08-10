import { useState } from "react";
import FilterToggle from "./FilterToggle2";

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
