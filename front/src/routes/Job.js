import { useState } from "react";
import { styled } from "styled-components";
import JobInfo from "../components/JobInfo/JobInfo";
import JobInfoContainer from "../components/JobInfo/JobInfo.Container";
import Data1 from "../assets/data/Data1";
import Navbar from "../components/navbar/Navbar";
import NavbarM from "../components/navbar/NavbarM";
import Footer from "../components/footer/Footer";

import { useMediaQuery } from "react-responsive";

const Job = () => {
  const [activeTab, setActiveTab] = useState("JobInfoContainer");
  const [subscription, setSubscription] = useState(true);
  
  const isMobile = useMediaQuery({ query: "(max-width: 768px)" });

  const handleTabClick = (tabName) => {
    setActiveTab(tabName);
    if (tabName === "JobInfoContainer") {
      setSubscription(true);
    } else {
      setSubscription(false);
    }
  };

  return (
    <JobWrapped>

      {isMobile ? (
        <div>
        {/* Mobile-specific content */}
          <NavbarM/>
          <JobIndexM>
            <TabButtonM
              onClick={() => handleTabClick("JobInfoContainer")}
              data-active={activeTab === "JobInfoContainer"}
            >
              관심 지역 구직 정보
            </TabButtonM>
            <TabButtonM
              onClick={() => handleTabClick("JobInfo")}
              data-active={activeTab === "JobInfo"}
            >
              통합 구직 정보
            </TabButtonM>
          </JobIndexM>
          {activeTab === "JobInfoContainer" ? (
            <JobInfoContainer Data1={Data1} subscription="true" />
          ) : (
            <JobInfo />
          )}
        </div>
      ) : (
        <div>
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
            <JobInfoContainer Data1={Data1} subscription="true" />
          ) : (
            <JobInfo />
          )}
          <Footer />
        </div>
      )}
      
      
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
  width: 25%;
  height: 88px;
  border-radius: 50px;
  background-color: ${(props) =>
    props["data-active"] ? "#ffb287" : "#ACACAC"};
  border: none;
  font-size: 26px;
  color: white;
  box-shadow: 0px 4px 5px 0px #0000001a;
  transition: background-color 0.3s, color 0.3s;
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


export default Job;
