import axios from "axios";

const axiosGetAlarmUrl = "/api/user/notifications";

export const axiosGetAlarm = async (userData) => {
  try {
    const response = await axios.post(axiosGetAlarmUrl, userData);
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const axiosPatchAlarm = async (userData) => {
  try {
    const response = await axios.patch(axiosGetAlarmUrl, userData);
    return response.data;
  } catch (error) {
    throw error;
  }
};
