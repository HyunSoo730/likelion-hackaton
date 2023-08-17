import { useState, useEffect } from "react";
import JobInfoList from "./JobInfoList";
import Pagination from "../../components/Pagination/Pagination";
import { styled, css } from "styled-components";
import { axiosGetPubSvc } from "../../api/axios/axios.PubSvc";
import Data from "../../assets/data/Data1";

import { useMediaQuery } from "react-responsive";

function JobInfoContainer({ Data1, subscription }) {
  const [JobInfoData, setJobInfoData] = useState([]);
  const [totalPage, setTotalPage] = useState(1);
  const [currentPage, setCurrentPage] = useState(1);

  async function getData(page) {
    try {
      const result = await axiosGetPubSvc(page);
      setTotalPage(result.totalPages);
      setJobInfoData(result.content);
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
    <JobInfoListStyled subscription={subscription}>
        <JobInfoList JobInfoLists={Data1 ? Data1 : Data} />
      
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
  height: 100%;
  position: relative;
  justify-content: center;
  background-color: white;
  display: flex;
  align-items: center;
  background-color: ${(props) =>
    props.subscription === "true" ? "#fff0e8" : "white"};
`;

const PaginationStyled = styled.div`
  @media (max-width: 768px){
    display: none;
  }
  @media (min-width: 769px){
    display: flex;
  }

  position: absolute;
  
  justify-content: space-between;
  align-items: center;
  left: 50%;
  transform: translateX(-50%);
  width: 90%;
`;

export default JobInfoContainer;
