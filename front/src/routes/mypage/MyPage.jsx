import React from 'react'

import Navbar from '../../components/navbarLogin/Navbar'
import Layout from '../../components/layout/Layout'
import Content from '../../components/content/Content'
import Footer from '../../components/footer/Footer'

function MyPage() {
    return (
        <div className="mypage">
            <Navbar />
            <Layout />
            <Content />
            <Footer />
        </div>
    );
}
export default MyPage;