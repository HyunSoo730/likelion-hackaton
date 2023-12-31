import ScrollToTop from "./components/js/ScrollUp";
import Main from "./routes/main/Main";
import SignIn from "./routes/SignIn";
import JobSearch from "./routes/JobSearch";
import PublicService from "./routes/PublicService";
import AlarmService from "./routes/alarmservice/AlarmService";
import MyPage from "./routes/MyPage";
import Auth from "./routes/Auth";
import Job from "./routes/Job";
import EduationInfo from "./routes/EducationInfo";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { createGlobalStyle } from "styled-components";
//import Auth1 from "./routes/Test";

function App() {
  return (
    <Router>
      <ScrollToTop />
      <GlobalStyles />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/mypage" element={<MyPage />} />
        <Route path="/jobsearch" element={<JobSearch />} />
        <Route path="/alarmservice" element={<AlarmService />} />
        <Route path="/publicservice" element={<PublicService />} />
        <Route path="/jobinfo" element={<Job />} />
        <Route path="/educationinfo" element={<EduationInfo />} />
        <Route path="/auth/kakao/callback" element={<Auth />} />
      </Routes>
    </Router>
  );
}

const GlobalStyles = createGlobalStyle`
  body {
    margin: 0;
    overflow-x:hidden;
  }
`;
export default App;
