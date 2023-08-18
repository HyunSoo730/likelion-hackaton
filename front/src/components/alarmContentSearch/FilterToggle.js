import { styled } from "styled-components";

const FilterToggle = ({ filterList, selectedFilter, handleAreaToggle }) => {
  return (
    <FilterItemWapped>
      <FilterItem>
        <ScrollableList>
          <CheckBoxRow>
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
          </CheckBoxRow>
        </ScrollableList>
      </FilterItem>
    </FilterItemWapped>
  );
};

const FilterItemWapped = styled.div`
  justify-content: center;
  align-items: center;
`;

const FilterItem = styled.div`
  height: 200px;
  border-radius: 10px;
`;

const ScrollableList = styled.div`
  font-size: 18px;
  max-height: 120px;
  color: #111111;
  padding: 10px;
`;

const CheckBoxRow = styled.div`
  display: flex;
  flex-wrap: wrap;
`;

const CheckBoxLabel = styled.label`
  align-items: center;
  cursor: pointer;
  user-select: none;
  width: 140px;
  margin-bottom: 15px;
  display: flex;
  align-items: center;
  @media screen and (max-width: 768px) {
    width: 60px;
    font-size: 10px;
    margin-bottom: 5px;
  }
`;

const CheckBoxInput = styled.input`
  margin-right: 15px;
  appearance: none;
  width: 20px;
  height: 20px;
  border: 1px solid #d9d9d9;
  border-radius: 2px;
  cursor: pointer;
  @media screen and (max-width: 768px) {
    width: 10px;
    height: 10px;
  }
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
  margin-right: 30px;
  color: ${(props) => (props.checked ? "#ff8643" : "#111111")};
  font-weight: ${(props) => (props.checked ? "bold" : "normal")};
`;

export default FilterToggle;
