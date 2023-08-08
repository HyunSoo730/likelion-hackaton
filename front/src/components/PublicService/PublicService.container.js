import { useState, useEffect } from "react";
import PublicServiceList from "./PublicServiceList";
import Pagination from "../Pagination/Pagination";
import { styled } from "styled-components";
import { axiosGetPubSvc, axiosPostPubSvc } from "../../api/axios/axios.PubSvc";
//import Data from "../assets/data/Data2";

const postData = {
  areaNM: "광진구",
  svcStatNM: "",
  maxClassNM: "",
  minClassNM: "",
  payAtNM: "",
};
const PublicServiceContainer = () => {
  const [publicServiceData, setPublicServiceData] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const [currentPage, setCurrentPage] = useState(0);

  // async function getData() {
  //   try {
  //     const result = await axiosGetPubSvc();
  //     const itemsPerPage = 6;
  //     const totalItems = result.length;
  //     const totalPages = Math.ceil(totalItems / itemsPerPage);
  //     setTotalPage(totalPages);

  //     const startIndex = currentPage * itemsPerPage;
  //     const endIndex = startIndex + itemsPerPage;
  //     const currentPageData = result.slice(startIndex, endIndex);

  //     setPublicServiceData(currentPageData);
  //   } catch (error) {
  //     console.error("Error getting data:", error);
  //   }
  // }

  async function getData() {
    try {
      const result = await axiosPostPubSvc(postData);
      const itemsPerPage = 6;
      const totalItems = result.length;
      const totalPages = Math.ceil(totalItems / itemsPerPage);
      setTotalPage(totalPages);

      const startIndex = currentPage * itemsPerPage;
      const endIndex = startIndex + itemsPerPage;
      const currentPageData = result.slice(startIndex, endIndex);

      setPublicServiceData(currentPageData);
    } catch (error) {
      console.error("Error getting data:", error);
    }
  }

  useEffect(() => {
    getData(currentPage);
  }, [currentPage]);

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
    getData(newPage);
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
};

const PublicServiceListStyled = styled.div`
  width: 100%;
  height: 100%;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
`;

const PaginationStyled = styled.div`
  position: absolute;
  display: flex;
  justify-content: space-between;
  align-items: center;
  left: 50%;
  transform: translateX(-50%);
  width: 85%;
`;
export default PublicServiceContainer;
