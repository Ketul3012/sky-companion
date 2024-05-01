/**
 * This is an implementation of a custom hook called useSignUp.
 * The purpose of this hook is to handle the sign-up functionality for a user by making an API request.
 */
import { useMutation } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { postRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';

/**
 * request interface used to send request to API call
 */
interface SignUpRequest {
  email: string;
  password: string;
  firstName: string;
  lastName: string;
  confirmPassword: string;
  dob: string;
  mobileNumber: string;
}

/**
 * Response interface used to store response data got from server
 */
export interface SignUpResponse {
  id: number;
  email: string;
  firstName: string;
  lastName: string;
}

/**
 * This function is responsible for making an API request to sign up a user.
 * It sends a POST request to the SIGNUP endpoint with the provided request data.
 *
 * @param request - The sign up request data.
 * @returns A promise that resolves to the sign up response data.
 */
const updater = async (request: SignUpRequest) => {
  const response = await postRequest<ApiSuccessResponse<SignUpResponse>>(
    ApiEndpoints.SIGNUP,
    request,
  );
  return response.data;
};

/**
 * react-query mutation used to maintain loading and error state with API call
 * @returns react-query mutation
 */
export const useSignUp = () => {
  return useMutation({
    mutationKey: [ReactQueryNames.SIGNUP],
    mutationFn: updater,
  });
};
