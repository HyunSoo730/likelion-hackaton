import axios from "axios";

const axiosEduSvcUrl = "/api/employment";

export const axiosGetEduSvc = async (page) => {
  try {
    const response = await axios.get(axiosEduSvcUrl, {
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
