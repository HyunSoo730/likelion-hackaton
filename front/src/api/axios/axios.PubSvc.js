import axios from "axios";

const axiosPubSvsUrl = "/api/public_service";

export const axiosGetPubSvc = async () => {
  try {
    const response = await axios.get(axiosPubSvsUrl);
    return response.data;
  } catch (error) {
    throw error;
  }
};

const axioPostPubSvsUrl = "/api/public_service";

export const axiosPostPubSvc = async (selecPubSvc) => {
  try {
    const response = await axios.post(axioPostPubSvsUrl, selecPubSvc);
    return response.data;
  } catch (error) {
    throw error;
  }
};
