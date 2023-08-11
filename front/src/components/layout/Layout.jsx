import React from 'react'
import './layout.css'
// const INTRO_IMAGE = '/img/layoutBgrd.jpg'

export default function Layout() {
    return (
        <div className="layout">
           {/* <img className="bgrd" src={process.env.PUBLIC_URL + '/img/layoutBgrd.jpg'} alt='logo image'/> */}
           {/* <img src={INTRO_IMAGE} alt="intro picture" /> */}
            <div className="title">더 멋진 노후를 위해 - 시니어 구직 정보 알림 서비스 "다오"</div>
            <div className="info">
                다오는 어르신들의 더 멋진 노후를 돕기 위한 서비스 입니다.
            </div>
            <div className="info2">
                행복하고 활기찬 노후를 위해서 다양한 구직 정보와 공공 서비스 정보를 제공하여
            </div>
            <div className="info2">
                어르신들이 삶의 끝까지 즐겁고 의미있는 시간을 보내실 수 있도록 도와드립니다.
            </div>
        </div>
    )
}