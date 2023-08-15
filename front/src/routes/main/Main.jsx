import React from 'react'
import { useMediaQuery } from "react-responsive";

import Navbar from '../../components/navbar/Navbar'
import Layout from '../../components/layout/Layout'
import Content from '../../components/content/Content'
import Footer from '../../components/footer/Footer'

import NavbarMainM from '../../components/navbar/NavbarMainM'
import LayoutM from '../../components/layout/LayoutM'
import ContentM from '../../components/content/ContentM'
import FooterM from '../../components/footer/FooterM'

function Main() {
    const isMobile = useMediaQuery({ query: "(max-width: 768px)" });

    return (
        <div className="main">
            {isMobile ? (
                <div>
                {/* Mobile-specific content */}
                    <NavbarMainM/>
                    <LayoutM />
                    <ContentM />
                    <FooterM />
                </div>
            ) : (
                <div>
                {/* Desktop-specific content */}
                    <Navbar />
                    <Layout />
                    <Content />
                    <Footer />
                </div>
            )}
        </div>
    );
  }
  
export default Main;