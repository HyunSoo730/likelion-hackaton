import { useState } from "react";
import { styled } from "styled-components";
import PublicServiceContainer from "../components/PublicService/PublicService.container";
import SearchImg from "../assets/images/search.png";
import ResetImg from "../assets/images/reset.png";
import PubSvcFilterList from "../components/PublicService/PubSvcFilterList";

function PublicService() {
  const [searchText, setSearchText] = useState("");

  const handleSearchChange = (event) => {
    setSearchText(event.target.value);
  };

  const handleSearchClick = () => {
    // 검색 동작 구현
    console.log("검색어:", searchText);
  };

  const handleResetClick = () => {
    setSearchText("");
  };

  return (
    <PublicServiceWrapped>
      <PublicServiceTop>
        <SearchBarStyled>
          <SearchBar>
            <input
              type="text"
              value={searchText}
              onChange={handleSearchChange}
              placeholder="검색어를 입력하세요"
            />
            <button onClick={handleSearchClick}>
              <img src={SearchImg} alt="search" />
            </button>
          </SearchBar>
          <ResetBtn onClick={handleResetClick}>
            <img src={ResetImg} alt="reset" />
            조건 초기화
          </ResetBtn>
        </SearchBarStyled>
        <PubSvcFilterList />
      </PublicServiceTop>
      <PublicServiceContainer></PublicServiceContainer>
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
`;

const SearchBarStyled = styled.div`
  display: flex;
  justify-content: center;
`;

const SearchBar = styled.div`
  width: 980px;
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

  img {
    margin-right: 12px;
  }
`;

export default PublicService;
