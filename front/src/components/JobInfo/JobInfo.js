import { useState } from "react";
import { styled } from "styled-components";
import JobInfoContainer from "./JobInfo.Container";
import SearchImg from "../../assets/images/search.png";
import ResetImg from "../../assets/images/reset.png";
import JobInfofFilterList from "./JobInfoFilter";
import { axiosFindJob } from "../../api/axios/axios.Job";
import NoResults from "../SearchFilter/NoResults";
import Frame from "../../assets/images/Frame.png";

function JobInfo() {
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
      const results = await axiosFindJob(searchText.trim(), filterData);
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
          <JobInfofFilterList
            filterData={filterData}
            setFilterData={setFilterData}
          />
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
  @media (max-width: 768px){
    height: 300px;
  }
  @media (min-width: 769px){
    height: 370px;
  }
  background-color: #ffb287;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;
  background-image: url(${Frame});
  background-size: cover;
  padding-top: 5px
`;

const SearchBarStyled = styled.div`
  display: flex;
  justify-content: center;
  width: 100%;
  margin:-5px;
`;

const SearchBar = styled.div`
  
  @media (max-width: 768px){
    width: 85%;
    height: 40px;
    border-radius: 10px;
  }
  @media (min-width: 769px){
    width: 60%;
    height: 60px;
    border-radius: 15px;
  }
  
  background-color: white;
  box-shadow: 0px 4px 48px 0px #0000001a;
  display: flex;
  justify-content: center;
  align-items: center;

  input {
    width: 87%;

    @media (max-width: 768px){
      font-size: 16px;
      margin-left: 10px;
    }
    @media (min-width: 769px){
      font-size: 24px;
      margin-left: 20px;
    }

    color: #767676;
    flex: 1;
    border: none;
    border-radius: 15px 0 0 15px;
    outline: none;
  }

  button {
    @media (max-width: 768px){
      width: 30px;
      height: 27px;
      border-radius: 10px;
    }
    @media (min-width: 769px){
      width: 60px;
      height: 48px;
      border-radius: 15px;
    }
    float: right;
    background-color: #ff8643;
    margin: 6px;
    border: none;
    cursor: pointer;
  }

  button:hover {
    background-color: #ffb287;
  }

  img {
    @media (max-width: 768px){
      width: 15px;
    }
  }
    margin:-5px
`;

const FilterStyled = styled.div`
  display: flex;
  @media (max-width: 768px){
    flex-direction: column-reverse;
    justify-content: center;
    align-items: center;
  }
`;

const ResetBtn = styled.div`
  border-radius: 15px;
  background-color: white;
  color: #767676;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
  img {
    margin-right: 12px;
    @media (max-width: 768px){
      width: 20px
    }
  }
  @media (max-width: 768px){
    font-size: 14px;
    width: 130px;
    height: 40px;
  }
  @media (min-width: 769px){
    font-size: 20px;
    margin: 25px 10px 0 0;
    width: 180px;
    height: 60px;
  }
`;

export default JobInfo;
