import { useState } from "react";
import FilterToggle from "./FilterToggle";

const FilterContainer = ({ Filters }) => {
  const [selectedFilter, setselectedFilter] = useState(
    Array(Filters.length - 1).fill(false)
  );

  const handleAreaToggle = (index) => {
    const updatedFilter = [...selectedFilter];
    updatedFilter[index] = !updatedFilter[index];
    const clickedArea = Filters[index + 1];
    console.log(clickedArea);
    setselectedFilter(updatedFilter);
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
