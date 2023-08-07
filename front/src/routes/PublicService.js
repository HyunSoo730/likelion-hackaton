import { useState, useEffect } from "react";
import PublicServiceList from "../components/PublicService/PublicServiceList";
import Pagination from "../components/PublicService/Pagination";
import { styled } from "styled-components";
import { axiosGetPubSvc } from "../api/axios/axiosPubSvc";

function PublicService() {
  const [publicServiceData, setPublicServiceData] = useState([]);
  const [totalPage, setTotalPage] = useState(1);
  const [currentPage, setCurrentPage] = useState(1);

  async function getData(page) {
    try {
      const result = await axiosGetPubSvc(page);
      setTotalPage(result.totalPages);
      setPublicServiceData(result.content);
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
    <PublicServiceListStyled>
      <PublicServiceList publicServiceLists={publicServiceData} />
      <PaginationStyled>
        <Pagination
          currentPage={currentPage}
          totalPage={totalPage}
          onPageChange={handlePageChange}
        />
      </PaginationStyled>
    </PublicServiceListStyled>
  );
}

const PublicServiceListStyled = styled.div`
  width: 100%;
  height: 100%;
  position: relative;
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
export default PublicService;
