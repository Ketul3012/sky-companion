/**
 * This code is a custom hook called useForgotPassword that is used for
 * handling the forgot password functionality in a React application.
 * In this specific code, the useForgotPassword hook is responsible for
 * managing the state of the email input field and providing a mutation
 * function for sending the forgot password request.
 */
import { useMutation } from '@tanstack/react-query';
import { useState } from 'react';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { postRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';

/**
 * request interface used to send request to API call
 */
interface ForgotPasswordRequest {
  email: string;
}

/**
 *
 * @param request forgot password request class
 * updater method used by forgot-password react-query mutation
 * inside the method it calls forgot password endpoint using axios postRequest method
 * @returns
 */
const updater = async (request: ForgotPasswordRequest) => {
  const response = await postRequest<ApiSuccessResponse<string>>(
    ApiEndpoints.FORGOT_PASSWORD,
    request,
  );
  return response.data;
};

/**
 * react-query mutation used to maintain loading and error state with API call
 * Custom hook for handling the forgot password functionality.
 * This hook manages the state of the email input field and provides a mutation function for sending the forgot password request.
 *
 * @returns An object containing the mutation function and the email state value and setter.
 */
export const useForgotPassword = () => {
  const [email, setEmail] = useState<string>('');

  const mutation = useMutation({
    mutationKey: [ReactQueryNames.FORGOT_PASSWORD],
    mutationFn: updater,
  });
  return {
    ...mutation,
    email,
    setEmail,
  };
};
