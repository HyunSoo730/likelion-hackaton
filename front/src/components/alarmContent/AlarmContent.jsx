import React, { useState, useEffect } from "react";
import "./alarmContent.css";
import Swal from "sweetalert2";
import AlarmRegionList from "./AlarmRegionList";
import {
  axiosPostAlarm,
  axiosPostAlarm2,
  axiosDeleteAlarm,
} from "../../api/axios/axios.Alarm";
import { axiosUserinterest } from "../../api/axios/axios.Alarm";

export default function AlarmContent() {
  const [userNoti, setUserNoti] = useState(
    localStorage.getItem("userNoti") === "true" ? true : false
  );
  const userName = localStorage.getItem("userName");
  const accessToken = localStorage.getItem("access_token");
  const userId = localStorage.getItem("user_id");

  const [userArea, setUserArea] = useState("");

  async function getUserInterest() {
    try {
      const result = await axiosUserinterest(userId);
      setUserArea(result.area);
    } catch (error) {
      console.error("Error getting user interest:", error);
    }
  }

  useEffect(() => {
    getUserInterest();
  }, []);

  const swalWithBootstrapButtons = Swal.mixin({
    customClass: {
      confirmButton: "btn btn-success",
      cancelButton: "btn btn-danger",
    },
    confirmButtonColor: "#FF8643",
    cancelButtonColor: "#8C8C8C",
  });

  const handleButtonSave = async () => {
    if (isApplyButtonDisabled) {
      return;
    }

    const userData = {
      kakaoId: parseInt(userId),
      area: selectedFilters.area,
    };

    console.log(userData);

    try {
      const response = await axiosPostAlarm2(userData);
      if (response == "등록완료") {
        swalWithBootstrapButtons.fire(
          "관심 지역 변경 완료!",
          "관심 지역이 변경되었습니다.",
          "success"
        );
        localStorage.setItem("selected", JSON.stringify(selectedFilters));
      } else {
        console.error("관심 지역 변경 실패: 응답 상태 코드", response);
      }
    } catch (error) {
      console.error("관심 지역 변경 실패:", error);
    }
  };

  const handleButtonTerminate = () => {
    swalWithBootstrapButtons
      .fire({
        title: "해지하시겠습니까?",
        icon: "question",
        showCancelButton: true,
        confirmButtonText: "해지",
        cancelButtonText: "취소",
      })
      .then(async (result) => {
        if (result.isConfirmed) {
          const userData = {
            userId: parseInt(userId),
          };
          try {
            const response = await axiosDeleteAlarm(userData);
            if (response == "삭제 완료") {
              setUserNoti(false);
              localStorage.removeItem("selectedFilters");
              localStorage.removeItem("selected");
              swalWithBootstrapButtons.fire(
                "알림 서비스 해지",
                "알림 서비스가 해지되었습니다.",
                "success"
              );
            } else {
              console.error("알림 서비스 해지 실패: 응답 상태 코드", response);
            }
          } catch (error) {
            console.error("알림 서비스 해지 실패:", error);
          }
        } else if (
          /* Read more about handling dismissals below */
          result.dismiss === Swal.DismissReason.cancel
        ) {
        }
      });
  };

  const [selectedFilters, setSelectedFilters] = useState(() => {
    const savedFilters = localStorage.getItem("selected");
    return savedFilters ? JSON.parse(savedFilters) : {};
  });
  const [isApplyButtonDisabled, setIsApplyButtonDisabled] = useState(true);

  const handleFilterUpdate = (newFilterData) => {
    setSelectedFilters(newFilterData);
    setIsApplyButtonDisabled(newFilterData.area.length === 0);

    localStorage.setItem("selectedFilters", JSON.stringify(newFilterData));
  };

  const handleButtonApply = async () => {
    if (isApplyButtonDisabled) {
      return;
    }

    const userData = {
      kakaoId: parseInt(userId),
      area: selectedFilters.area,
    };

    console.log(userData);

    try {
      const response = await axiosPostAlarm(userData);
      if (response == "등록완료") {
        setUserNoti(true);
        Swal.fire({
          icon: "success",
          iconColor: "#FF8643",
          title: `${userName}님의<br/> 구직 정보 알림 신청이 <br/>완료되었습니다!`,
          text: "카카오톡으로 알림이 전송됩니다.",
          confirmButtonText: "확인",
          confirmButtonColor: "#FF8643",
        });
        localStorage.setItem("selected", JSON.stringify(selectedFilters));
      } else {
        console.error("알림 서비스 신청 실패: 응답 상태 코드", response);
      }
    } catch (error) {
      console.error("알림 서비스 신청 실패:", error);
    }
  };

  useEffect(() => {
    localStorage.setItem("userNoti", userNoti.toString());
  }, [userNoti]);

  return (
    <div className="alarm">
      {!userNoti && (
        <div className="alarmContent">
          <div className="alarmMenu">알림 서비스 신청하기</div>
          <div className="alarmLct">
            <div className="alarmContentTitle">
              1. 관심 지역 설정하기 (최대 3곳)
            </div>
            <div className="alarmDtl1">
              <AlarmRegionList onFilterUpdate={handleFilterUpdate} />
            </div>
            <div className="alarmContentTitle">2. 신청하기</div>
            <div className="alarmDtl2">
              관심 지역에 새로운 공고가 올라올 때마다 카카오톡으로 알려드립니다.
              <button
                className="applyBtn"
                onClick={() => handleButtonApply({ userName: userName })}
                disabled={isApplyButtonDisabled}
                style={{
                  cursor: isApplyButtonDisabled ? "not-allowed" : "pointer",
                }}
              >
                신청하기
              </button>
            </div>
          </div>
        </div>
      )}
      {userNoti && (
        <div className="alarmContent">
          <div className="alarmMenu">관심 지역 변경하기</div>
          <div className="alarmLct">
            <div className="alarmDtl1">
              <AlarmRegionList
                onFilterUpdate={handleFilterUpdate}
                selectedFilters={selectedFilters}
              />
              <button
                className="saveBtn"
                onClick={handleButtonSave}
                disabled={isApplyButtonDisabled}
                style={{
                  cursor: isApplyButtonDisabled ? "not-allowed" : "pointer",
                }}
              >
                변경하기
              </button>
            </div>
          </div>

          <div className="alarmMenu1">알림 서비스 해지하기</div>
          <div className="alarmLct">
            <div className="alarmDtl2">
              알림 서비스를 해지하시면 더이상 다오에서 제공하는 각종 구직정보에
              대한 카카오톡 알림을 받으실 수 없습니다. 그래도 해지하시겠습니까?
              <button className="cancelBtn" onClick={handleButtonTerminate}>
                해지하기
              </button>
            </div>
          </div>
        </div>
      )}
    </div>
  );
}
