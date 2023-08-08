import { useState, useEffect } from "react";
import EducationList from "../components/EducationInfo/EducationList";
import Pagination from "../components/Pagination/Pagination";
import { styled } from "styled-components";
import { axiosGetEduSvc } from "../api/axios/axios.EduSvc";
import Data from "../assets/data/Data3";

function EducationInfo() {
  const [educationInfoData, setEducationInfoData] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const [currentPage, setCurrentPage] = useState(0);

  async function getData(page) {
    try {
      const result = await axiosGetEduSvc(page);
      setTotalPage(result.totalPages);
      setEducationInfoData(result.content);
    } catch (error) {
      console.error("Error getting data:", error);
    }
  }

  useEffect(() => {
    getData(currentPage);
  }, [currentPage]);

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };

  console.log(Data);
  return (
    <EducationInfoListStyled>
      <EducationList EducationLists={Data} />
      <PaginationStyled>
        <Pagination
          currentPage={currentPage}
          totalPage={totalPage}
          onPageChange={handlePageChange}
        />
      </PaginationStyled>
    </EducationInfoListStyled>
  );
}

const EducationInfoListStyled = styled.div`
  width: 100%;
  height: 676px;
  position: relative;
  display: flex;
  align-items: center;
`;

const PaginationStyled = styled.div`
  position: absolute;
  display: flex;
  justify-content: space-between;
  align-items: center;
  left: 50%;
  transform: translateX(-50%);
  top: 50%;
  width: 90%;
`;

export default EducationInfo;