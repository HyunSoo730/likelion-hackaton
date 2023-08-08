import React from "react";
import styled from "styled-components";
import PaginationBtn from "../../assets/images/paginationBtn.png";

const Pagination = ({ currentPage, totalPage, onPageChange }) => {
  const handleClickPrev = () => {
    if (currentPage > 1) {
      onPageChange(currentPage - 1);
    }
  };

  const handleClickNext = () => {
    if (currentPage < totalPage) {
      onPageChange(currentPage + 1);
    }
  };

  return (
    <PaginationWrapper>
      <PrevBtn onClick={handleClickPrev} disabled={currentPage === 1}>
        <img src={PaginationBtn} alt="Prev" />
      </PrevBtn>
      <NextBtn onClick={handleClickNext} disabled={currentPage === totalPage}>
        <img src={PaginationBtn} alt="Next" />
      </NextBtn>
    </PaginationWrapper>
  );
};

const PaginationWrapper = styled.div`
  width: 100%;
  display: flex;
  justify-content: space-between;
  align-items: center;
`;

const PrevBtn = styled.button`
  cursor: pointer;
  background: none;
  border: none;
  padding: 0;
  &:hover {
    opacity: 0.8;
  }
  &:disabled {
    cursor: not-allowed;
    opacity: 0.5;
  }
`;

const NextBtn = styled.button`
  cursor: pointer;
  background: none;
  border: none;
  padding: 0;
  &:hover {
    opacity: 0.8;
  }
  img {
    transform: rotate(180deg);
  }
  &:disabled {
    cursor: not-allowed;
    opacity: 0.5;
  }
`;

export default Pagination;
