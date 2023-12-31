import { useState } from "react";
import { styled } from "styled-components";
import PublicServiceContainer from "../components/PublicService/PublicService.container";
import SearchImg from "../assets/images/search.png";
import ResetImg from "../assets/images/reset.png";
import Frame from "../assets/images/Frame.png";
import PubSvcFilterList from "../components/PublicService/PubSvcFilterList";
import { axiosPubSvcFind } from "../api/axios/axios.PubSvc";
import NoResults from "../components/SearchFilter/NoResults";
import Navbar from "../components/navbar/Navbar";
import Footer from "../components/footer/Footer";

function PublicService() {
  const [searchText, setSearchText] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [showNoResult, setShowNoResult] = useState(false);
  const [filterData, setFilterData] = useState({});

  const handleSearchChange = (event) => {
    setSearchText(event.target.value);
  };

  const handleSearchClick = async () => {
    // if (searchText.trim().length < 2) {
    //   return alert("두 글자 이상의 검색어를 입력해주세요.");
    // }
    try {
      const results = await axiosPubSvcFind(searchText.trim(), filterData);
      setShowNoResult(results.length === 0);
      setSearchResults(results);
    } catch (error) {
      console.error("검색 중 오류 발생:", error);
    }
  };

  const handleKeyPress = (event) => {
    if (event.key === "Enter") {
      handleSearchClick();
    }
  };

  const handleResetClick = () => {
    setSearchResults([]);
    setSearchText("");
    setShowNoResult(false);
  };

  return (
    <PublicServiceWrapped>
      <Navbar />
      <PublicServiceTop>
        <SearchBarStyled>
          <SearchBar>
            <input
              type="text"
              value={searchText}
              onChange={handleSearchChange}
              onKeyDown={handleKeyPress}
              placeholder="검색어를 입력하세요"
            />
            <button onClick={handleSearchClick}>
              <img src={SearchImg} alt="search" />
            </button>
          </SearchBar>
          <ResetBtn onClick={handleResetClick}>
            <img src={ResetImg} alt="reset" />
            검색 초기화
          </ResetBtn>
        </SearchBarStyled>
        <PubSvcFilterList
          filterData={filterData}
          setFilterData={setFilterData}
        />
      </PublicServiceTop>
      {showNoResult ? (
        <NoResults />
      ) : (
        <PublicServiceContainer searchResults={searchResults} />
      )}
      <Footer />
    </PublicServiceWrapped>
  );
}

const PublicServiceWrapped = styled.div`
  width: 100%;
`;

const PublicServiceTop = styled.div`
  width: 100%;
  height: 370px;
  background-color: #ffb287;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-image: url(${Frame});
  background-size: cover;
`;

const SearchBarStyled = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
`;

const SearchBar = styled.div`
  width: 60%;
  height: 60px;
  border-radius: 15px;
  background-color: white;
  box-shadow: 0px 4px 48px 0px #0000001a;
  display: flex;
  justify-content: center;
  align-items: center;

  input {
    width: 87%;
    font-size: 24px;
    color: #767676;
    flex: 1;
    border: none;
    border-radius: 15px 0 0 15px;
    outline: none;
    margin-left: 30px;
  }

  button {
    width: 60px;
    height: 48px;
    float: right;
    background-color: #ff8643;
    margin: 6px;
    border: none;
    border-radius: 15px;
    cursor: pointer;
  }

  button:hover {
    background-color: #ffb287;
  }
`;

const ResetBtn = styled.div`
  width: 180px;
  height: 60px;
  border-radius: 15px;
  background-color: white;
  color: #767676;
  display: flex;
  justify-content: center;
  align-items: center;
  margin-left: 20px;
  font-size: 20px;
  cursor: pointer;
  img {
    margin-right: 12px;
  }
`;

export default PublicService;
