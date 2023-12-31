import React from 'react'
import { useMediaQuery } from "react-responsive";

import Navbar from '../../components/navbar/Navbar'
import Layout from '../../components/layout/Layout'
import Content from '../../components/content/Content'
import Footer from '../../components/footer/Footer'

function Main() {
    const isMobile = useMediaQuery({ query: "(max-width: 768px)" });

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