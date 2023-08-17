import React from "react";

import Navbar from "../../components/navbar/Navbar";
import AlarmLayout from "../../components/alarmLayout/AlarmLayout";
import AlarmContent from "../../components/alarmContent/AlarmContent";
import Footer from "../../components/footer/Footer";
import { styled } from "styled-components";

function Main() {
  return (
    <div className="alarmservice">
      <Navbar />
      <AlarmLayout />
      <AlarmContent />
      <FooterWrapper>
        <Footer />
      </FooterWrapper>{" "}
    </div>
  );
}

const FooterWrapper = styled.div`
  @media screen and (max-width: 768px) {
    display: none;
  }
`;

export default Main;
