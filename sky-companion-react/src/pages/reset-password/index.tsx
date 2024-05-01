/**
 * This code is a React component called ResetPassword that renders a reset password page 
 * and handles the logic for resetting a user's password.

 * The purpose of this code is to provide a user interface for resetting a password 
 * and to handle the necessary API calls and validations. It allows users to enter a 
 * new password and confirm it, and then calls the reset password API endpoint to 
 * update the user's password in the backend.
 */

import { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { PrimaryButton } from '../../components/button/PrimaryButton';
import { TextInput } from '../../components/input/TextInput';
import { FullScreenLoading } from '../../components/loading/FullScreenLoading';
import { HeaderText } from '../../components/text/HeaderText';
import { ApiSuccessResponse } from '../../model/api-success-response';
import { useResetPassword } from './hook/useResetPassword';

/**
 * This function renders the reset password page and contains it's business logic
 * @returns
 */
export const ResetPassword = () => {
  const {
    isLoading,
    mutateAsync,
    newPassword,
    setNewPassword,
    confirmPassword,
    setConfirmPassword,
    setToken,
    token,
  } = useResetPassword();

  const navigate = useNavigate();

  const location = useLocation();

  /**
   * this method get token param and set it in state
   */
  useEffect(() => {
    setToken(location.search.replace('?token=', ''));
  }, [setToken, location]);

  /**
   * this method validates reset-password form
   * @returns true or false based on validation result
   */
  const validateForm = () => {
    if (!token || token === '') {
      toast.error('Invalid reset password link');
      return false;
    }

    if (!newPassword) {
      toast.error('Password is required');
      return false;
    }

    if (newPassword !== confirmPassword) {
      toast.error('New Password and Confirm Password should match');
      return;
    }

    return true;
  };

  /**
   * Calls the reset password API endpoint to reset the user's password.
   *
   * This function is important to the project as it handles the logic for resetting the password
   * by making an API call and updating the user's password in the backend. It also handles the
   * success and error scenarios by displaying appropriate toast messages and navigating the user
   * to the login page if the password reset is successful.
   */
  const _callResetPassword = async () => {
    if (validateForm() ?? false) {
      const response: ApiSuccessResponse<string> = await mutateAsync({
        newPassword: newPassword,
        token: token,
      });
      if (response.statusCode === 200) {
        toast.success(response.data);
        navigate('/login', { replace: true });
      } else {
        toast.error(response.statusMessage);
      }
    }
  };

  return (
    <>
      {isLoading ? (
        <FullScreenLoading text=" Please wait while we send you mail" />
      ) : (
        <div className="my-5 text-center">
          <HeaderText label="Reset password" />
          <div className="w-1/3 text-center p-2 mx-auto my-2">
            <TextInput
              labelPosition="top"
              placeholderText="New Password"
              value={newPassword}
              onChange={(value) => setNewPassword(value)}
              type="password"
            />

            <TextInput
              labelPosition="top"
              placeholderText="Confirm Password"
              value={confirmPassword}
              onChange={(value) => setConfirmPassword(value)}
              type="password"
            />
            <PrimaryButton
              buttonText="Reset Password"
              onClick={async () => {
                await _callResetPassword();
              }}
            />
          </div>
        </div>
      )}
    </>
  );
};
