import axios from "axios";

const axiosPubSvsUrl = "/api/public_service";

export const axiosGetPubSvc = async (page) => {
  try {
    const response = await axios.get(axiosPubSvsUrl, {
      params: {
        page: page,
        size: 6,
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};

export const axiosPostPubSvc = async (requestData) => {
  try {
    const response = await axios.post(axiosPubSvsUrl, requestData);
    return response.data;
  } catch (error) {
    throw error;
  }
};
