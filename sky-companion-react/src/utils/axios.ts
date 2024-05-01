/**
 * This file contains utility functions for making HTTP requests using axios.
 * It sets up global configurations for axios, such as base URL and timeout.
 * It also includes interceptors for request and response, allowing for adding authorization headers and handling errors.
 * The file provides functions for making GET, POST, PUT, and DELETE requests.
 * These functions accept the endpoint URL, request parameters, headers, and request configuration as parameters.
 * The functions return promises that resolve to the AxiosResponse object or reject with an AxiosError object.
 * This file is important to the project as it provides a centralized and reusable way to make HTTP requests throughout the application.
 */

import axios, {
  AxiosError,
  AxiosHeaderValue,
  AxiosRequestConfig,
  AxiosResponse,
  HeadersDefaults,
} from 'axios';
import { toast } from 'react-toastify';
import { LoginResponse } from '../pages/login/hook/useLogin';
// set global values for all axios requests
axios.defaults.baseURL = import.meta.env.VITE_PUBLIC_API_BASE_URL as string;
axios.defaults.timeout = 60000;
/**
 * intercept the request and add authorization header
 */
axios.interceptors.request.use(
  (request) => {
    try {
      const loginStateJsonString = localStorage.getItem('login-state');
      if (loginStateJsonString != null) {
        // eslint-disable-next-line @typescript-eslint/no-unsafe-assignment
        const loginState: {
          state: { loginResponse: LoginResponse };
          version: number;
        } = JSON.parse(loginStateJsonString);

        request.headers.Authorization =
          'Bearer ' + loginState.state.loginResponse.jwtToken;
      }
      return request;
    } catch (e) {
      return request;
    }
  },
  async (err) => {
    return Promise.reject(err);
  },
);

/**
 * intercept the response to see if there's error or not and show the toast
 */
axios.interceptors.response.use(
  (response) => {
    return response;
  },
  (error) => {
    if (import.meta.env.DEV) {
      console.log('axios api error', error);
    }
    // eslint-disable-next-line @typescript-eslint/no-unsafe-argument, @typescript-eslint/no-unsafe-member-access
    toast.error(error?.response?.data?.message ?? 'Internal api error');
    return Promise.reject(error);
  },
);

/**
 * This method run the GET call for endpoints using axios
 */
export function getRequest<T>(
  endPoint: string,
  parameters: unknown = '',
  url = '',
  header: HeadersDefaults & Record<string, AxiosHeaderValue> = axios.defaults
    .headers,
) {
  if (url !== '') {
    axios.defaults.baseURL = url;
  }
  axios.defaults.headers = {
    ...header,
  };

  return new Promise<AxiosResponse<T>>((resolve, reject) => {
    axios
      .get(endPoint, {
        params: parameters ?? '',
      })
      .then(function (response: AxiosResponse<T>) {
        resolve(response);
      })
      .catch(function (error: AxiosError) {
        reject(error);
      });
  });
}

/**
 * This method run the POST call for endpoints using axios and accept the request
 */
export function postRequest<T>(
  endPoint: string,
  parameters: unknown = {},
  header: HeadersDefaults & Record<string, AxiosHeaderValue> = axios.defaults
    .headers,
  requestConfig: AxiosRequestConfig | undefined = undefined,
) {
  // set this to token returned by server after user logs in
  axios.defaults.headers = {
    ...header,
  };

  return new Promise<AxiosResponse<T>>((resolve, reject) => {
    axios
      .post(endPoint, parameters, requestConfig)
      .then((response: AxiosResponse<T>) => {
        resolve(response);
      })
      .catch((error: AxiosError) => {
        reject(error);
      });
  });
}

/**
 * This method run the PUT call for endpoints using axios and accept the request
 */
export function putRequest<T>(
  endPoint: string,
  parameters: unknown = {},
  header: HeadersDefaults & Record<string, AxiosHeaderValue> = axios.defaults
    .headers,
  requestConfig: AxiosRequestConfig | undefined = undefined,
) {
  // set this to token returned by server after user logs in
  axios.defaults.headers = {
    ...header,
  };

  return new Promise<AxiosResponse<T>>((resolve, reject) => {
    axios
      .put(endPoint, parameters, requestConfig)
      .then((response: AxiosResponse<T>) => {
        resolve(response);
      })
      .catch((error: AxiosError) => {
        reject(error);
      });
  });
}

/**
 * This method run the DELETE call for endpoints using axios and accept the request
 */
export function deleteRequest<T>(
  endPoint: string,
  parameters: unknown = '',
  url = '',
  header: HeadersDefaults & Record<string, AxiosHeaderValue> = axios.defaults
    .headers,
) {
  if (url !== '') {
    axios.defaults.baseURL = url;
  }
  axios.defaults.headers = {
    ...header,
  };

  return new Promise<AxiosResponse<T>>((resolve, reject) => {
    axios
      .delete(endPoint, {
        params: parameters ?? '',
      })
      .then(function (response: AxiosResponse<T>) {
        resolve(response);
      })
      .catch(function (error: AxiosError) {
        reject(error);
      });
  });
}
