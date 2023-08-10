import ScrollToTop from "./components/js/ScrollUp";
// import Home from "./routes/Home";
import Main from './routes/main/Main'
import SignIn from "./routes/SignIn";
import JobSearch from "./routes/JobSearch";
import PublicService from "./routes/PublicService";
import Education from "./routes/Education";
import AlarmService from "./routes/AlarmService";
import MyPage from "./routes/MyPage";
import Job from "./routes/Job";
import EduationInfo from "./routes/EducationInfo";

import './App.css'

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import { createGlobalStyle } from "styled-components";

function App() {
  return (
    <div className="hi">
    <Router>
      <ScrollToTop />
      <GlobalStyles />
      <Routes>
        <Route path="/" element={<Main />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/mypage" element={<MyPage />} />
        <Route path="/jobsearch" element={<JobSearch />} />
        <Route path="/publicservice" element={<PublicService />} />
        <Route path="/education" element={<Education />} />
        <Route path="/alarmservice" element={<AlarmService />} />
        <Route path="/jobinfo" element={<Job />} />
        <Route path="/educationinfo" element={<EduationInfo />} />
      </Routes>
    </Router>
    </div>
  );
}


const GlobalStyles = createGlobalStyle`
  body {
    margin: 0;
  }
`;
export default App;
