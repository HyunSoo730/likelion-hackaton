import { useState } from "react";
import { styled } from "styled-components";
import JobInfo from "../components/JobInfo/JobInfo";
import JobInfoContainer from "../components/JobInfo/JobInfo.Container";
import Data1 from "../assets/data/Data1";

const Job = () => {
  const [activeTab, setActiveTab] = useState("JobInfoContainer");
  const [subscription, setSubscription] = useState(true);

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
  font-size: 36px;
  color: white;
  box-shadow: 0px 4px 5px 0px #0000001a;
  transition: background-color 0.3s, color 0.3s;
`;

export default Job;
