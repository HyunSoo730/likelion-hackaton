import React from 'react'

import Navbar from '../../components/navbar/Navbar'
import AlarmLayout from '../../components/alarmLayout/AlarmLayout'
import AlarmContent from '../../components/alarmContent/AlarmContent'
import Footer from '../../components/footer/Footer'


function Main() {
    return (
        <div className="alarmservice">
            <Navbar />
            <AlarmLayout />
            <AlarmContent />
            <Footer />
        </div>
    );
  }
  
export default Main;