import { styled } from "styled-components";
import {
  AreaNMs,
  SvcStatNMs,
  MaxClassNMs,
  PayAtNMs,
} from "../../assets/data/PubSvcFilter.data";
import FilterContainer from "../SearchFilter/FilterContainer";

const PubSvcFilterList = () => {
  return (
    <FilterContainerStyled>
      <FilterContainer Filters={AreaNMs}></FilterContainer>
      <FilterContainer Filters={SvcStatNMs}></FilterContainer>
      <FilterContainer Filters={MaxClassNMs}></FilterContainer>
      <FilterContainer Filters={PayAtNMs}></FilterContainer>
    </FilterContainerStyled>
  );
};

const FilterContainerStyled = styled.div`
  display: flex;
`;

export default PubSvcFilterList;
