import axios from "axios";

const axiosEduSvcUrl = "/api/employment/all_data";

export const axiosGetEduSvc = async () => {
  try {
    const response = await axios.get(axiosEduSvcUrl);
    return response.data;
  } catch (error) {
    throw error;
  }
};

const axiosPostEduSvcUrl = "/api/employment/filter_data";

export const axiosPostEduSvc = async (searchData) => {
  try {
    const response = await axios.post(axiosPostEduSvcUrl, searchData);
    return response.data;
  } catch (error) {
    throw error;
  }
};

// export const axiosPostEduSvc = async (subject, filterData) => {
//   try {
//     const response = await axios.post(axiosPostEduSvcUrl, {
//       subject,
//       filterData,
//     });
//     return response.data;
//   } catch (error) {
//     throw error;
//   }
// };
