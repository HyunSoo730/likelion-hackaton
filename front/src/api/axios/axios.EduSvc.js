import axios from "axios";

const axiosEduSvcUrl = "/api/employment/data";

export const axiosGetEduSvc = async () => {
  try {
    const response = await axios.get(axiosEduSvcUrl);
    return response.data;
  } catch (error) {
    throw error;
  }
};
