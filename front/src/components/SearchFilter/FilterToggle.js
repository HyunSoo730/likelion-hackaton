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
@media (max-width: 768px){
  width: 100px;
  height: 170px;
}
@media (min-width: 769px){
  width: 180px;
  height: 208px;
}
  background-color: white;
  border-radius: 10px;
  margin: 10px;
  overflow: hidden;
<<<<<<< HEAD

  @media screen and (max-width: 768px) {
    width: 80px;
    height: 106px;
    margin: 0px 5px;
  }
=======
  box-shadow: 4px 4px 15px 0px #0000001a;
>>>>>>> 0ac4e60beb6ec366b00212d0117eabf488008ab7
`;

const FilterTitle = styled.div`
  width: 100%;
  height: 48px;
  @media (max-width: 768px){
    font-size: 15px;
  }
  @media (min-width: 769px){
    font-size: 24px;
  }
  font-weight: 400;
  border-bottom: 1px solid #acacac;
  display: flex;
  justify-content: center;
  align-items: center;
<<<<<<< HEAD

  @media screen and (max-width: 768px) {
    height: 26px;
    font-size: 12px;
  }
=======
  font-weight:bold;
>>>>>>> 0ac4e60beb6ec366b00212d0117eabf488008ab7
`;

const ScrollableList = styled.div`
@media (max-width: 768px){
  font-size: 12px;
  max-height: 110px;
}
@media (min-width: 769px){
  font-size: 20px;
  max-height: 150px;
}
  overflow-y: auto;
  color: #111111;
  padding: 5px;

  @media screen and (max-width: 768px) {
    font-size: 10px;'
    max-height: 60px;
  }
`;

const CheckBoxLabel = styled.label`
  display: flex;
  align-items: center;
  cursor: pointer;
  user-select: none;
  @media (max-width: 768px){
    margin-bottom: 7px;
  }
  @media (min-width: 769px){
    margin-bottom: 15px;
  }
`;

const CheckBoxInput = styled.input`
  appearance: none;
  
  @media (max-width: 768px){
    width: 17px;
    height: 17px;
    margin-right: 5px;
  }
  @media (min-width: 769px){
    width: 20px;
    height: 20px;
    margin-right: 10px;
  }
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
    color: white;
    margin-left: 2px;
    @media (max-width: 768px){
      font-size: 13px;
    }
    @media (min-width: 769px){
      font-size: 16px;
    }
  }

  @media screen and (max-width: 768px) {
    width: 10px;
    height: 10px;
    margin-right: 5px;
  }
`;

const CheckBoxText = styled.span`
  color: ${(props) => (props.checked ? "#ff8643" : "#111111")};
  font-weight: ${(props) => (props.checked ? "bold" : "normal")};
  @media (max-width: 768px){
    padding-bottom:3px;
  }
`;

export default FilterToggle;
