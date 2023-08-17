import { styled } from "styled-components";

const FilterToggle = ({
  filterTitle,
  filterList,
  selectedFilter,
  handleAreaToggle,
}) => {
  return (
    <FilterItemWapped>
      <FilterItem>
        <FilterTitle>{filterTitle}</FilterTitle>
        <ScrollableList>
          {filterList.map((areaName, index) => (
            <CheckBoxLabel key={index}>
              <CheckBoxInput
                type="checkbox"
                checked={selectedFilter[index]}
                onChange={() => handleAreaToggle(index)}
              />
              <CheckBoxText checked={selectedFilter[index]}>
                {areaName}
              </CheckBoxText>
            </CheckBoxLabel>
          ))}
        </ScrollableList>
      </FilterItem>
    </FilterItemWapped>
  );
};

const FilterItemWapped = styled.div`
  margin-top: 5px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const FilterItem = styled.div`
  width: 160px;
  height: 36px;
  background-color: white;
  border-radius: 10px;
  margin: 8px;
  overflow: hidden;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const FilterTitle = styled.div`
  width: 55px;
  font-size: 12px;
  margin-left: 10px;
  font-weight: 400;
  border-right: 1px solid #acacac;
`;

const ScrollableList = styled.div`
  display: flex;
  font-size: 10px;
  max-height: 160px;
  color: #111111;
`;

const CheckBoxLabel = styled.label`
  display: flex;
  align-items: center;
  cursor: pointer;
  user-select: none;
  margin-left: 8px;
`;

const CheckBoxInput = styled.input`
  appearance: none;
  width: 10px;
  height: 10px;
  border: 1px solid #d9d9d9;
  border-radius: 2px;
  margin-top: 0px;
  cursor: pointer;
  &:checked {
    background-color: #ff8643;
    border: 1px solid #ff8643;
  }
  &:checked::after {
    content: "✔";
    font-size: 10px;
    color: white;
    margin-left: 2px;
  }
`;

const CheckBoxText = styled.span`
  color: ${(props) => (props.checked ? "#ff8643" : "#111111")};
  font-weight: ${(props) => (props.checked ? "bold" : "normal")};
`;

export default FilterToggle;
