import { useState, useEffect } from "react";
import EducationList from "./EducationList";
import Pagination from "../Pagination/Pagination";
import { styled } from "styled-components";
import { axiosGetEduSvc } from "../../api/axios/axios.EduSvc";

function EducationInfoContainer({ searchResults }) {
  const [educationInfoData, setEducationInfoData] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const [currentPage, setCurrentPage] = useState(0);

  const itemsPerPage = 6;

  useEffect(() => {
    if (searchResults.length > 0) {
      const totalItems = searchResults.length;
      const totalPages = Math.ceil(totalItems / itemsPerPage);
      setTotalPage(totalPages);
      setEducationInfoData(searchResults);
      setCurrentPage(0);
    } else {
      async function getData() {
        try {
          const result = await axiosGetEduSvc();
          const totalItems = result.length;
          const totalPages = Math.ceil(totalItems / itemsPerPage);
          setTotalPage(totalPages);
          setEducationInfoData(result);
        } catch (error) {
          console.error("Error getting data:", error);
        }
      }
      getData();
    }
  }, [searchResults]);

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentPageData = educationInfoData.slice(startIndex, endIndex);

  return (
    <EducationInfoListStyled>
      <EducationList EducationLists={currentPageData} />
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
  justify-content: center;
`;

const PaginationStyled = styled.div`
  position: absolute;
  display: flex;
  justify-content: space-between;
  align-items: center;
  left: 50%;
  transform: translateX(-50%);
  width: 90%;
`;

export default EducationInfoContainer;
