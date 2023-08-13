import React from "react";
import "./alarmContent.css";
import Swal from "sweetalert2";
import AlarmRegionList from "./AlarmRegionList";



const handleButtonApply = () => { 
        Swal.fire({
            icon: "success",
            iconColor: "#FF8643",
            title: "OOO 님의</br>구직 정보 알림 신청이</br>완료 되었습니다!",
            text: "카카오톡으로 알림이 전송됩니다.",
            confirmButtonText: "확인",
            confirmButtonColor: "#FF8643",
        })
};

const swalWithBootstrapButtons =  Swal.mixin({
    customClass: {
    confirmButton: 'btn btn-success',
    cancelButton: 'btn btn-danger'
    },
    confirmButtonColor: "#438eff",
    cancelButtonColor:"#e61f1f",
})

const handleButtonSave = () => {
    swalWithBootstrapButtons.fire({
        title: '변경하시겠습니까?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: '예, 변경합니다.',
        cancelButtonText: '아니요, 취소합니다.',
    }).then((result) => {
        if (result.isConfirmed) {
        swalWithBootstrapButtons.fire(
            'Deleted!',
            'Your file has been deleted.',
            'success'
        )
        } else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
        ) {
        swalWithBootstrapButtons.fire(
            'Cancelled',
            'Your imaginary file is safe :)',
            'error'
        )
        }
    })
};

const handleButtonTerminate = () => {
    swalWithBootstrapButtons.fire({
        title: '해지하시겠습니까?',
        icon: 'question',
        showCancelButton: true,
        confirmButtonText: '예, 해지합니다.',
        cancelButtonText: '아니요, 취소합니다.',
    }).then((result) => {
        if (result.isConfirmed) {
        swalWithBootstrapButtons.fire(
            'Deleted!',
            'Your file has been deleted.',
            'success'
        )
        } else if (
        /* Read more about handling dismissals below */
        result.dismiss === Swal.DismissReason.cancel
        ) {
        swalWithBootstrapButtons.fire(
            'Cancelled',
            'Your imaginary file is safe :)',
            'error'
        )
        }
    })
};

export default function AlarmContent() {

const isLoggedIn = true;
  return (
    <div className="alarm">
        {!isLoggedIn && (
            <div className="alarmContent">
                <div className="alarmMenu">
                    알림 서비스 신청하기
                </div>
                <div className="alarmLct">
                    <div className="alarmContentTitle">
                        1. 관심지역 설정하기 (최대 3곳)
                    </div>
                    <div className="alarmDtl1">
                        <AlarmRegionList />
                    </div>
                    <div className="alarmContentTitle">
                        2. 신청하기
                    </div>
                    <div className="alarmDtl2">
                            관심 지역에 새로운 공고가 올라올 때마다 카카오톡으로 알려드립니다.
                        <button className="applyBtn" onClick={handleButtonApply}>
                            신청하기
                        </button>
                    </div>
                </div>
            </div>
        )}
        
        {isLoggedIn && (
            <div className="alarmContent">
                <div className="alarmMenu">
                    알림 서비스 변경하기
                </div>
                <div className="alarmLct">
                    <div className="alarmDtl1">
                        <AlarmRegionList />
                        <button className="saveBtn" onClick={handleButtonSave}>
                            저장하기
                        </button>
                    </div>
                </div>
                <div className="alarmMenu">
                    알림 서비스 해지하기
                </div>
                <div className="alarmLct">
                    <div className="alarmDtl2">
                        알림 서비스를 해지하시면 더이상 다오에서 제공하는 각종 구직정보에 대한
                        카카오톡 알림을 받으실 수 없습니다. 그래도 해지하시겠습니까?
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