import React from 'react'

import Navbar from '../../components/navbar/Navbar'
import Layout from '../../components/layout/Layout'
import Content from '../../components/content/Content'
import Footer from '../../components/footer/Footer'

function Main() {
    return (
        <div className="main">
            <Navbar />
            <Layout />
            <Content />
            <Footer />
        </div>
    );
  }
  
export default Main;