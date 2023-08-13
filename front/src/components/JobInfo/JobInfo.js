import { useState } from "react";
import { styled } from "styled-components";
import JobInfoContainer from "./JobInfo.Container";
import SearchImg from "../../assets/images/search.png";
import ResetImg from "../../assets/images/reset.png";
import JobInfoFilterList from "./JobInfoFilter";
import { axiosPubSvcFind } from "../../api/axios/axios.PubSvc";
import NoResults from "../SearchFilter/NoResults";

function JobInfo() {
  const [searchText, setSearchText] = useState("");
  const [searchResults, setSearchResults] = useState([]);
  const [showNoResult, setShowNoResult] = useState(false);

  const handleSearchChange = (event) => {
    setSearchText(event.target.value);
  };

  const handleSearchClick = async () => {
    if (searchText.trim().length < 2) {
      return alert("두 글자 이상의 검색어를 입력해주세요.");
    }
    try {
      const results = await axiosPubSvcFind(searchText);
      setSearchResults(results);
      setShowNoResult(results.length === 0);
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
    <JobInfoWrapped>
      <JobInfoTop>
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
        </SearchBarStyled>
        <FilterStyled>
          <ResetBtn onClick={handleResetClick}>
            <img src={ResetImg} alt="reset" />
            검색 초기화
          </ResetBtn>
          <JobInfoFilterList />
        </FilterStyled>
      </JobInfoTop>
      {showNoResult ? (
        <NoResults />
      ) : (
        <JobInfoContainer searchResults={searchResults} subscription="false" />
      )}
    </JobInfoWrapped>
  );
}

const JobInfoWrapped = styled.div`
  width: 100%;
`;

const JobInfoTop = styled.div`
  width: 100%;
  height: 370px;
  background-color: #ffb287;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
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

const FilterStyled = styled.div`
  display: flex;
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
  margin: 25px 10px 0 0;
  font-size: 20px;
  cursor: pointer;
  img {
    margin-right: 12px;
  }
`;

export default JobInfo;
