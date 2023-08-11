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
  margin-top: 15px;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const FilterItem = styled.div`
  width: 180px;
  height: 208px;
  background-color: white;
  border-radius: 10px;
  margin: 10px;
  overflow: hidden;
`;

const FilterTitle = styled.div`
  width: 100%;
  height: 48px;
  font-size: 24px;
  font-weight: 400;
  border-bottom: 1px solid #acacac;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const ScrollableList = styled.div`
  font-size: 20px;
  max-height: 160px;
  overflow-y: auto;
  color: #111111;
  padding: 10px;
`;

const CheckBoxLabel = styled.label`
  display: flex;
  align-items: center;
  cursor: pointer;
  user-select: none;
  margin-bottom: 15px;
`;

const CheckBoxInput = styled.input`
  margin-right: 10px;
  appearance: none;
  width: 20px;
  height: 20px;
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
    font-size: 16px;
    color: white;
    margin-left: 2px;
  }
`;

const CheckBoxText = styled.span`
  color: ${(props) => (props.checked ? "#ff8643" : "#111111")};
  font-weight: ${(props) => (props.checked ? "bold" : "normal")};
`;

export default FilterToggle;
