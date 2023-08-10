import axios from "axios";

export const axiostest = async (startIndex, endIndex) => {
  try {
    const response = await axios.get("/employeement/test", {
      params: {
        startIndex: startIndex,
        endIndex: endIndex,
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export default axiostest;
