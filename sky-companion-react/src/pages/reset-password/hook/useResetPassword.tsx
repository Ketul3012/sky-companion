/**
 * This code defines a custom hook called useResetPassword that is used for 
 * handling the functionality of resetting a password.

 * The useResetPassword hook manages the state for the token, new password, 
 * and confirm password fields. It also provides a mutation function for resetting the password.
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
interface ResetPasswordRequest {
  newPassword: string;
  token: string;
}

/**
 *
 * @param request forgot password request class
 * updater method used by reset-password react-query mutation
 * inside the method it calls reset password endpoint using axios postRequest method
 * @returns
 */
const updater = async (request: ResetPasswordRequest) => {
  const response = await postRequest<ApiSuccessResponse<string>>(
    ApiEndpoints.RESET_PASSWORD,
    request,
  );
  return response.data;
};

/**
 * Custom hook for handling reset password functionality.
 * This hook manages the state for token, new password, and confirm password fields.
 * It also provides a mutation function for resetting the password.
 *
 * @returns An object containing the mutation function and state variables for token, new password, and confirm password.
 */
export const useResetPassword = () => {
  const [token, setToken] = useState<string>('');
  const [newPassword, setNewPassword] = useState<string>('');
  const [confirmPassword, setConfirmPassword] = useState<string>('');

  const mutation = useMutation({
    mutationKey: [ReactQueryNames.RESET_PASSWORD],
    mutationFn: updater,
  });
  return {
    ...mutation,
    token,
    setToken,
    newPassword,
    setNewPassword,
    confirmPassword,
    setConfirmPassword,
  };
};
