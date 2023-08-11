import ScrollToTop from "./components/js/ScrollUp";
import Home from "./routes/Home";
import SignIn from "./routes/SignIn";
import JobSearch from "./routes/JobSearch";
import PublicService from "./routes/PublicService";
import AlarmService from "./routes/AlarmService";
import MyPage from "./routes/MyPage";
import Auth from "./routes/Auth";
import "./App.css";

import { BrowserRouter as Router, Routes, Route } from "react-router-dom";

function App() {
  return (
    <Router>
      <ScrollToTop />

      <Routes>
        <Route path="/home" element={<Home />} />
        <Route path="/signin" element={<SignIn />} />
        <Route path="/mypage" element={<MyPage />} />
        <Route path="/jobsearch" element={<JobSearch />} />
        <Route path="/" element={<PublicService />} />
        <Route path="/alarmservice" element={<AlarmService />} />
        <Route path="/auth/kakao/callback" element={<Auth />} />
      </Routes>
    </Router>
  );
}

export default App;
