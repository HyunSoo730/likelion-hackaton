import axios from "axios";

const axiosAlarmUrl = "/api/interest_area/update";

export const axiosPostAlarm = async (userData) => {
  try {
    const response = await axios.post(axiosAlarmUrl, userData);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const axiosPostAlarm2 = async (userData) => {
  try {
    const response = await axios.post(axiosAlarmUrl, userData);
    return response.data;
  } catch (error) {
    throw error;
  }
};

const axiosDeleteAlarmUrl = "/api/interest_area/delete";

export const axiosDeleteAlarm = async (userId) => {
  try {
    const response = await axios.post(axiosDeleteAlarmUrl, userId);
    return response.data;
  } catch (error) {
    throw error;
  }
};

const axiosMessageUrl = "/kakao/send_message/want";

export const axiosMessage = async (accessToken, kakaoMessage) => {
  try {
    const response = await axios.post(axiosMessageUrl, kakaoMessage, {
      params: {
        accessToken: accessToken,
      },
      headers: {
        "Content-Type": "application/json",
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

const axiosUserinterestUerl = "/api/interest_area/userarea";
export const axiosUserinterest = async (userData) => {
  const userIdNumber = parseInt(userData);
  try {
    const response = await axios.get(axiosUserinterestUerl, {
      params: {
        kakaoId: userIdNumber,
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
