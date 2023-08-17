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

const axiosMessageUrl = "/api/interest_area/kakao";

export const axiosMessage = async (kakaoM) => {
  try {
    const response = await axios.post(axiosMessageUrl, kakaoM);
    return response.data;
  } catch (error) {
    throw error;
  }
};
