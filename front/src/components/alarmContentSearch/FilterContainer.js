import { useState } from "react";
import FilterToggle from "./FilterToggle";
import Swal from "sweetalert2";

const FilterContainer = ({ Filters, selectedValues, onFilterUpdate }) => {
  const initialSelectedFilter = Filters.map((filter) =>
    selectedValues.includes(filter)
  );

  const [selectedFilter, setSelectedFilter] = useState(initialSelectedFilter);

  const handleAreaToggle = (index) => {
    const selectedCount = selectedFilter.filter(Boolean).length;

    if (!selectedFilter[index] && selectedCount >= 3) {
      Swal.fire({
        icon: "warning",
        title: "최대 3곳까지</br> 선택할 수 있습니다!",
        html: "더 이상 추가 선택할 수 없습니다.",
        confirmButtonColor: "#8C8C8C",
      });
    } else {
      const updatedFilter = [...selectedFilter];
      updatedFilter[index] = !updatedFilter[index];
      setSelectedFilter(updatedFilter);

      const selectedFilterValues = Filters.filter(
        (_, idx) => updatedFilter[idx]
      );

      onFilterUpdate(selectedFilterValues);
    }
  };

  return (
    <FilterToggle
      filterList={Filters}
      selectedFilter={selectedFilter}
      handleAreaToggle={handleAreaToggle}
    />
  );
};

export default FilterContainer;
