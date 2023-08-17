import Modal from 'react-modal'
import styled, { css } from "styled-components";
import React, { useEffect, useRef } from "react";
import useOutSideClick from "../hooks/useOutSideClick";
import modalCheck from "../../assets/images/modalCheck.png";

// components/Modal/Modal

function SaveModal({ onClose }) {
  const modalRef = useRef(null);
  const handleClose = () => {
    onClose?.();
  };
  useOutSideClick(modalRef, handleClose);
  useEffect(() => {
    const $body = document.querySelector("body");
    $body.style.overflow = "hidden";
    return () => ($body.style.overflow = "auto");
  }, []);

  return (
      <Overlay>
        <ModalWrap>
          <Check>
            <img src={modalCheck} width="50px" alt="" />
          </Check>
          <Contents>
            <div>OOO 님의 </div>
            <div>관심 지역 설정이 </div>
            <div>변경되었습니다! </div>
          </Contents>
          <Bottom>
          </Bottom>
          <Button onClick={handleClose}>확 인</Button>
        </ModalWrap>
      </Overlay>
  );
}

const Overlay = styled.div`
  position: fixed;
  width: 100%;
  height: 100%;
  top: 0;
  bottom: 0;
  left: 0;
  right: 0;
  background: rgba(0, 0, 0, 0.6);
  z-index: 9999;
`;

const ModalWrap = styled.div`
    @media (max-width: 768px){
        width: 230px;
        height: 240px;
    }
    @media (min-width: 769px){
        width: 350px;
        height: 330px;
    }

    border-radius: 12px;
    background-color: #fff;
    position: absolute;
    display:flex;
    align-items: center;
    justify-content: center;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    flex-direction: column;
`;

const Check = styled.div`
    display:flex;
    @media (max-width: 768px){
        margin-top:60px;
        margin-bottom:15px;

    }
    @media (min-width: 769px){
        margin-top:80px;
        margin-bottom:20px;
    }
`;

const Contents = styled.div`
  font-weight: 600;
  display:static;
  align-items: center;
  justify-content: center;
  @media (max-width: 768px){
    line-height: 27px;
    font-size: 16px;
    margin-bottom: 10px;
  }
  @media (min-width: 769px){
    line-height: 35px;
    font-size: 20px;
    margin-bottom: 10px;
  }
`;

const Bottom = styled.div`
  font-weight: 500;
  color:#696969;
  @media (max-width: 768px){
    line-height: 27px;
    font-size: 12px;
    margin-bottom: 20px;
  }
  @media (min-width: 769px){
    line-height: 35px;
    font-size: 16px;
    padding-bottom: 50px;
  }
`;

const Button = styled.button`
  display:flex;
  border: none;
  background-color: #FF8643;
  color: white;
  font-weight: 200;
  cursor: pointer;
  align-items: center;
  justify-content: center;
  font-weight: bold;
  border-radius: 0 0 12px 12px;
  &:hover {
    background-color: #FF6C1B;
  }
  
  @media (max-width: 768px){
    font-size: 14px;
    width: 100%;
    padding: 12px 20px;
  }
  @media (min-width: 769px){
    font-size: 19px;
    width: 100%;
    padding: 18px 20px;
  }
`;
export default SaveModal;