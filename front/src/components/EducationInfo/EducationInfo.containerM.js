import { useState, useEffect } from "react";
import EducationListM from "./EducationListM";
import Pagination from "../Pagination/Pagination";
import { styled } from "styled-components";
import { axiosGetEduSvc } from "../../api/axios/axios.EduSvc";
import Data from "../../assets/data/Data3";

function EducationInfoContainerM() {
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

  return (
    <EducationInfoListStyled>
      <EducationListM EducationLists={Data} />
    </EducationInfoListStyled>
  );
}

const EducationInfoListStyled = styled.div`
  width: 100%;
  height: 676px;
  position: relative;
  display: flex;
  align-items: center;
  justify-content: center;
`;

export default EducationInfoContainerM;
