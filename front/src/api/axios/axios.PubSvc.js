import axios from "axios";

const axiosGetPubSvsUrl = "/api/public_service/all_data";

export const axiosGetPubSvc = async () => {
  try {
    const response = await axios.get(axiosGetPubSvsUrl);
    return response.data;
  } catch (error) {
    throw error;
  }
};

const axioPostPubSvcUrl = "/api/public_service/filter_data";

export const axiosPostPubSvc = async (selecPubSvc) => {
  try {
    const response = await axios.post(axioPostPubSvcUrl, selecPubSvc);
    return response.data;
  } catch (error) {
    throw error;
  }
};

const axiosGetPubSvcFindUrl = "/api/public_service/find";

export const axiosPubSvcFind = async (serviceName) => {
  try {
    const response = await axios.get(axiosGetPubSvcFindUrl, {
      params: {
        serviceName: serviceName,
      },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
