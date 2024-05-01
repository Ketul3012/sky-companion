/**
 * This code is a TypeScript implementation of a custom hook called useLogin.
 * The purpose of this hook is to handle a login functionality using the react-query library.
 */

import { useMutation } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { postRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';

/**
 * request interface used to send request to API call
 */
interface LoginRequest {
  email: string;
  password: string;
}

/**
 * Response interface used to store response data got from server
 */
export interface LoginResponse {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
  jwtToken: string;
}

/**
 *
 * @param request login request class
 * updater method used by login react-query mutation
 * inside the method it calls login endpoint using axios postRequest method
 * @returns
 */
const updater = async (request: LoginRequest) => {
  const response = await postRequest<ApiSuccessResponse<LoginResponse>>(
    ApiEndpoints.LOGIN,
    request,
  );
  return response.data.data;
};

/**
 * react-query mutation used to maintain loading and error state with API call
 * @returns react-query mutation
 */
export const useLogin = () => {
  return useMutation({
    mutationKey: [ReactQueryNames.LOGIN],
    mutationFn: updater,
  });
};
