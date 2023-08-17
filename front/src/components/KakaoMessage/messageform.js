const generateMessage = (wantedTitle, emplymShpNm, acptMthdCd, contactInfo) => {
  const messageTemplate = {
    object_type: "feed",
    content: {
      title: "회원님의 좋아요 누른 구직 정보가 도착했어요!",
      image_url: "https://ifh.cc/g/jZROxM.png",
      image_width: 640,
      image_height: 300,
      link: {
        web_url: "http://www.naver.com",
        mobile_web_url: "www.naver.com",
      },
    },
    item_content: {
      items: [
        {
          item: "채용제목",
          item_op: wantedTitle,
        },
        {
          item: "채용공고형태",
          item_op: emplymShpNm,
        },
        {
          item: "접수방법",
          item_op: acptMthdCd,
        },
        {
          item: "담당자 연락처",
          item_op: contactInfo,
        },
      ],
    },
  };

  return messageTemplate;
};

export default generateMessage; // 함수 이름에 따라 export 문 수정
