import { useState, useEffect } from "react";
import { styled } from "styled-components";
import { axiosGetPubSvc, axiosPostPubSvc } from "../../api/axios/axios.PubSvc";
import Data from "../../assets/data/Data2";
import PublicServiceListM from "./PublicServiceListM";

const PublicServiceContainerM = ({ searchResults }) => {
  const [publicServiceData, setPublicServiceData] = useState([]);
  const [totalPage, setTotalPage] = useState(0);
  const [currentPage, setCurrentPage] = useState(0);

  const itemsPerPage = 6;

  useEffect(() => {
    if (searchResults.length > 0) {
      const totalItems = searchResults.length;
      const totalPages = Math.ceil(totalItems / itemsPerPage);
      setTotalPage(totalPages);
      setPublicServiceData(searchResults);
      setCurrentPage(0);
    } else {
      async function getData() {
        try {
          const result = await axiosGetPubSvc();
          const totalItems = result.length;
          const totalPages = Math.ceil(totalItems / itemsPerPage);
          setTotalPage(totalPages);
          setPublicServiceData(result);
        } catch (error) {
          console.error("Error getting data:", error);
        }
      }
      getData();
    }
  }, [searchResults]);

  //   async function getData() {
  //     try {
  //       const result = await axiosGetPubSvc();
  //       const totalItems = result.length;
  //       const totalPages = Math.ceil(totalItems / itemsPerPage);
  //       setTotalPage(totalPages);
  //       setPublicServiceData(result);
  //     } catch (error) {
  //       console.error("Error getting data:", error);
  //     }
  //   }

  //   async function getData() {
  //     try {
  //       const result = await axiosPostPubSvc(postData);
  //       const itemsPerPage = 6;
  //       const totalItems = result.length;
  //       const totalPages = Math.ceil(totalItems / itemsPerPage);
  //       setTotalPage(totalPages);

  //       const startIndex = currentPage * itemsPerPage;
  //       const endIndex = startIndex + itemsPerPage;
  //       const currentPageData = result.slice(startIndex, endIndex);

  //       setPublicServiceData(currentPageData);
  //     } catch (error) {
  //       console.error("Error getting data:", error);
  //     }
  //   }

  //   useEffect(() => {
  //     getData();
  //   }, []);

  const handlePageChange = (newPage) => {
    setCurrentPage(newPage);
  };

  const startIndex = currentPage * itemsPerPage;
  const endIndex = startIndex + itemsPerPage;
  const currentPageData = publicServiceData.slice(startIndex, endIndex);

  return (
    <PublicServiceListStyled>
      <PublicServiceListM publicServiceLists={Data} />
    </PublicServiceListStyled>
  );
};

const PublicServiceListStyled = styled.div`
  width: 100%;
  position: relative;
  display: flex;
  justify-content: center;
  align-items: center;
`;

export default PublicServiceContainerM;
