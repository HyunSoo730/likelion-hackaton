import Modal from "react-modal";
import styled, { css } from "styled-components";
import React, { useEffect, useRef } from "react";
import useOutSideClick from "../hooks/useOutSideClick";

function HeartModal({ onClose, SvcClick }) {
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
        <Contents>
          <div>
            선택한 구직 정보를 카카오톡으로
            <br />
            전달 받겠습니까?{" "}
          </div>
        </Contents>
        <ButtonStyle>
          <Button onClick={SvcClick}>확 인</Button>
          <Button onClick={handleClose}>취 소</Button>
        </ButtonStyle>
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
  @media (max-width: 768px) {
    width: 300px;
    border: 3px solid;
    border-color: #ffb287;
    height: 200px;
  }
  @media (min-width: 769px) {
    width: 600px;
    border: 5px solid;
    border-color: #ffb287;
    height: 290px;
  }

  border-radius: 15px;
  background-color: #fff;
  position: absolute;
  display: flex;
  align-items: center;
  justify-content: center;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  flex-direction: column;
`;

const Contents = styled.div`
  font-weight: 600;
  text-align: center;
  @media (max-width: 768px) {
    line-height: 27px;
    font-size: 16px;
    margin: 20px;
    margin-bottom: 20px;
  }
  @media (min-width: 769px) {
    line-height: 35px;
    font-size: 20px;
    margin: 40px;
    margin-bottom: 40px;
  }
`;
const Button = styled.button`
  display: block;
  padding: 10px 20px;
  border: none;
  background-color: #ff8643;
  border-radius: 10px;
  color: white;
  font-weight: 200;
  cursor: pointer;
  align-items: center;
  margin: 0 auto;
  justify-content: center;
  font-weight: bold;
  &:hover {
    background-color: #ff6c1b;
  }

  @media (max-width: 768px) {
    font-size: 14px;
    width: 40%;
    margin-bottom: 15px;
  }
  @media (min-width: 769px) {
    font-size: 19px;
    width: 30%;
    margin-bottom: 20px;
  }
`;

const ButtonStyle = styled.div`
  width: 80%;
  display: flex;
`;
export default HeartModal;
