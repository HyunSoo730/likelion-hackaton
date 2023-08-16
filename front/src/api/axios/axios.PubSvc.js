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

const axiosPostPubSvcFindUrl = "/api/public_service/filter_data";

export const axiosPubSvcFind = async (svcNM, filterData) => {
  try {
    const response = await axios.post(axiosPostPubSvcFindUrl, {
      svcNM,
      ...filterData,
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
