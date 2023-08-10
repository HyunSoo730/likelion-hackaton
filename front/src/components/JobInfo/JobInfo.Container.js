import { useState, useEffect } from "react";
import JobInfoList from "./JobInfoList";
import Pagination from "../../components/Pagination/Pagination";
import { styled, css } from "styled-components";
import { axiosGetPubSvc } from "../../api/axios/axios.PubSvc";
import Data from "../../assets/data/Data1";

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
  height: 676px;
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
