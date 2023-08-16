import React from 'react'
import './layoutM.css'
import grndprtImg from "../../assets/images/grndprtImg2.png";

export default function Layout() {
    return (
        <div className="layoutM">
           <img src={grndprtImg} className="grndprtM" width="220px" alt="" />
            <div className="infoM">
                <div className="title1M">더 멋진 노후를 위해 - </div>
                <div className="title2M">시니어 구직 정보 알림 서비스 "다오"</div>
                <div className="info1M">
                    다오는 어르신들의 더 멋진 노후를 돕기
                </div>
                <div className="info2M">
                    위해서 다음과 같은 서비스를 제공합니다.
                </div>
            </div>
        </div>
    )
}