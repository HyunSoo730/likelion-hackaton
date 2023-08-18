import { useState, useEffect } from "react";
import { styled } from "styled-components";
import JobInfo from "../components/JobInfo/JobInfo";
import JobInfoContainer from "../components/JobInfo/JobInfo.Container";
import Navbar from "../components/navbar/Navbar";
import Footer from "../components/footer/Footer";
import { axiosInterstJob } from "../api/axios/axios.Job";
import { axiosUserinterest } from "../api/axios/axios.Alarm";
import NoInterest from "../components/SearchFilter/Nointerest";

import { useMediaQuery } from "react-responsive";

const Job = () => {
  const [activeTab, setActiveTab] = useState("JobInfoContainer");
  const [subscription, setSubscription] = useState(true);
  const [interstResults, setInterstResults] = useState([]);
  const [selectedFilters, setSelectedFilters] = useState([]);

  const isMobile = useMediaQuery({ query: "(max-width: 768px)" });
  const userId = localStorage.getItem("user_id");

  const handleTabClick = (tabName) => {
    setActiveTab(tabName);
    if (tabName === "JobInfoContainer") {
      setSubscription(true);
    } else {
      setSubscription(false);
    }
  };

  useEffect(() => {
    const fetchUserInterestData = async () => {
      try {
        const jobData = await axiosUserinterest(userId);
        setSelectedFilters(jobData);

        if (jobData.length > 0) {
          const interstJobData = await axiosInterstJob(userId);
          setInterstResults(interstJobData);
        }
      } catch (error) {
        console.error("구직 정보 가져오기 실패!!!:", error);
      }
    };

    fetchUserInterestData();
  }, []);

  if (interstResults.length > 0) {
  }

  return (
    <JobWrapped>
      {/* Desktop-specific content */}
      <Navbar />
      <JobIndex>
        <TabButton
          onClick={() => handleTabClick("JobInfoContainer")}
          data-active={activeTab === "JobInfoContainer"}
        >
          관심 지역 구직 정보
        </TabButton>
        <TabButton
          onClick={() => handleTabClick("JobInfo")}
          data-active={activeTab === "JobInfo"}
        >
          통합 구직 정보
        </TabButton>
      </JobIndex>
      {activeTab === "JobInfoContainer" ? (
        interstResults.length > 0 ? (
          <>
            <JobInfoContainer
              searchResults={interstResults}
              subscription="true"
            />
          </>
        ) : (
          <NoInterest />
        )
      ) : (
        <JobInfo />
      )}
      <Footer />
    </JobWrapped>
  );
};

const JobWrapped = styled.div`
  width: 100%;
`;

const JobIndex = styled.div`
  width: 100%;
  height: 150px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
`;

const TabButton = styled.button.attrs((props) => ({
  "data-active": props["data-active"] ? "true" : undefined,
}))`
  z-index: 1;
  border-radius: 50px;
  background-color: ${(props) =>
    props["data-active"] ? "#ffb287" : "#ACACAC"};
  border: none;
  color: white;
  box-shadow: 0px 4px 5px 0px #0000001a;
  transition: background-color 0.3s, color 0.3s;

  @media (max-width: 768px) {
    font-size: 16px;
    font-weight: bold;
    height: 50px;
    width: 42%;
  }

  @media (min-width: 769px) {
    font-size: 26px;
    font-weight: 550;
    height: 70px;
    width: 30%;
  }
`;

const JobIndexM = styled.div`
  width: 100%;
  height: 70px;
  display: flex;
  justify-content: space-evenly;
  align-items: center;
`;

const TabButtonM = styled.button.attrs((props) => ({
  "data-active": props["data-active"] ? "true" : undefined,
}))`
  z-index: 1;
  width: 41%;
  height: 40px;
  border-radius: 50px;
  background-color: ${(props) =>
    props["data-active"] ? "#ffb287" : "#ACACAC"};
  border: none;
  font-size: 16px;
  font-weight: bold;
  color: white;
  box-shadow: 0px 4px 5px 0px #0000001a;
  transition: background-color 0.3s, color 0.3s;
`;

const InterestAreaStyled = styled.div`
  display: flex;
  align-items: center;
  flex-wrap: wrap;
  margin-bottom: 10px;
`;

const SelectedFilterItem = styled.div`
  background-color: #ffb287;
  color: white;
  border-radius: 20px;
  padding: 4px 10px;
  margin: 0 5px 5px 0;
`;

export default Job;
