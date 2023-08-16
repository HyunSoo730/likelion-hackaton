import axios from "axios";

const axiosAllJobUrl = "/api/job_search/all_data";

export const axiosAllJob = async () => {
  try {
    const response = await axios.get(axiosAllJobUrl);
    return response.data;
  } catch (error) {
    throw error;
  }
};

const axiosFindJobUrl = "/api/job_search/filter_data";

export const axiosFindJob = async (svcNM, filterData) => {
  try {
    const response = await axios.post(axiosFindJobUrl, {
      svcNM,
      ...filterData,
    });
    return response.data;
  } catch (error) {
    throw error;
  }
};
