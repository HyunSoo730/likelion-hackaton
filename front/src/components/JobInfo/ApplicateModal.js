import Modal from "react-modal";
import styled, { css } from "styled-components";
import React, { useEffect, useRef } from "react";
import useOutSideClick from "../hooks/useOutSideClick";

function ApplicateModal({ onClose, data }) {
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
            <strong>⚬ 접수 방법</strong> : {data.acptMthdCd} 접수
          </div>
          <div>
            <strong>⚬ 접수 장소</strong> : {data.plDetAddr}{" "}
          </div>
          <div>
            <strong>⚬ 담당자</strong> : {data.clerk}
          </div>
          <div>
            <strong>⚬ 전화번호</strong> :{" "}
            {data.clerkContt || "등록된 전화번호가 없습니다."}
          </div>
          <div>
            <strong>⚬ 채용공고형태</strong> : {data.emplymShpNm}
          </div>
        </Contents>
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
  align-items: flex-end;
  justify-content: center;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
`;

const Contents = styled.div`
  font-weight: 500;
  line-height: 60px;
  font-size: 20px;
  padding-top: 35px;
  @media (max-width: 768px) {
    line-height: 22px;
    font-size: 13px;
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
  margin-right: 20px;
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
export default ApplicateModal;
