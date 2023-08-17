import { useState, useEffect } from "react";
import JobInfoList from "./JobInfoList";
import JobInfoListM from "./JobInfoListM";
import Pagination from "../../components/Pagination/Pagination";
import { styled } from "styled-components";
import { axiosAllJob } from "../../api/axios/axios.Job";

import { useMediaQuery } from "react-responsive";

function JobInfoContainer({ searchResults, subscription }) {
  const [JobInfoData, setJobInfoData] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const [currentPage, setCurrentPage] = useState(0);

  const isMobile = useMediaQuery({ query: "(max-width: 768px)" });

  const itemsPerPage = 6;
  useEffect(() => {
    if (searchResults.length > 0) {
      const totalItems = searchResults.length;
      const totalPages = Math.ceil(totalItems / itemsPerPage);
      setTotalPage(totalPages);
      setJobInfoData(searchResults);
      setCurrentPage(0);
    } else {
      async function getData() {
        try {
          const result = await axiosAllJob();
          const totalItems = result.length;
          const totalPages = Math.ceil(totalItems / itemsPerPage);
          setTotalPage(totalPages);
          setJobInfoData(result);
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

  const currentPageData = JobInfoData.slice(startIndex, endIndex);

  return (
    <JobInfoListStyled subscription={subscription}>
      {isMobile ? (
        <JobInfoListM JobInfoLists={currentPageData} />
      ) : (
        <JobInfoList JobInfoLists={currentPageData} />
      )}

      <PaginationStyled>
        <Pagination
          currentPage={currentPage}
          totalPage={totalPage}
          onPageChange={handlePageChange}
        />
      </PaginationStyled>
    </JobInfoListStyled>
  );
}

const JobInfoListStyled = styled.div`
  width: 100%;
  height: 700px;
  position: relative;
  justify-content: center;
  background-color: white;
  display: flex;
  align-items: center;
  background-color: ${(props) =>
    props.subscription === "true" ? "#fff0e8" : "white"};
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

export default JobInfoContainer;
