import { styled } from "styled-components";


const style = {
  width:"4000px",
}

const FilterToggle = ({
  filterList,
  selectedFilter,
  handleAreaToggle,
}) => {
  return (
    <FilterItemWapped>
      <FilterItem>
        <ScrollableList>
          {filterList.map((areaName, index) => (
            <CheckBoxLabel key={index} >
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
  justify-content: center;
  align-items: center;
`;

const FilterItem = styled.div`
  width: 960px;
  height: 200px;
  border-radius: 10px;
`;

const ScrollableList = styled.div`
  font-size: 20px;
  max-height: 120px;
  color: #111111;
  padding: 10px;
`;

const CheckBoxLabel = styled.label`
  align-items: center;
  cursor: pointer;
  user-select: none;
  margin-bottom: 40px;
  width: 20%;
`;

const CheckBoxInput = styled.input`
  margin-right: 15px;
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
  margin-right: 30px;
  color: ${(props) => (props.checked ? "#ff8643" : "#111111")};
  font-weight: ${(props) => (props.checked ? "bold" : "normal")};
`;

export default FilterToggle;