import React from 'react'
import { useMediaQuery } from "react-responsive";
import './layout.css'
import grndprtImg from "../../assets/images/grndprtImg.png";
import grndprtImg2 from "../../assets/images/grndprtImg2.png";

export default function Layout() {
    const isMobile = useMediaQuery({ query: "(max-width: 768px)" });

    return (
        <div className="layout">
            
            {isMobile ? (
                <img src={grndprtImg2} className="grndprt" width="650px" alt="" />
            ) : (
                <img src={grndprtImg} className="grndprt" width="650px" alt="" />
            )}
            <div className="info">
                <div className="titles">
                    <div className="title1">더 멋진 노후를 위해 - </div>
                    <div className="title2">시니어 구직 정보 알림 서비스 "다오"</div>
                </div>
                <div className="info1_s">
                    <div className="info1_1">
                        다오는 어르신들의 더 멋진 노후를 돕기 
                    </div>
                    <div className="info1_2">
                        위한 서비스 입니다.
                    </div>
                </div>
                <div className="info2">
                    행복하고 활기찬 노후를 위해서 다양한 구직 정보와 공공 서비스 정보를 제공하여
                </div>
                <div className="info2">
                    어르신들이 삶의 끝까지 즐겁고 의미있는 시간을 보내실 수 있도록 도와드립니다.
                </div>
            </div>
        </div>
    )
}