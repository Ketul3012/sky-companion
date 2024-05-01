/**
 * Calls the login API endpoint and handles the response.
 *
 * This function is responsible for validating the login form, making a request to the login API endpoint,
 * and handling the response. If the login is successful, it sets the JWT token in the request headers,
 * updates the login response state, and navigates to the home page. If the login fails, it displays an error message.
 *
 * @returns {Promise<void>} A promise that resolves when the login process is complete.
 */

import axios from 'axios';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { PrimaryButton } from '../../components/button/PrimaryButton';
import { TextInput } from '../../components/input/TextInput';
import { FullScreenLoading } from '../../components/loading/FullScreenLoading';
import { HeaderText } from '../../components/text/HeaderText';
import { LoginResponse, useLogin } from './hook/useLogin';
import { useLoginStore } from './store';

export const Login = () => {
  const { isLoading, mutateAsync } = useLogin();
  const { setLoginResponse } = useLoginStore();
  const navigate = useNavigate();

  const [email, setEmail] = useState<string>('');
  const [password, setPassword] = useState<string>('');

  const validateForm = () => {
    if (!email) {
      toast.error('Email is required');
      return false;
    }

    if (!password) {
      toast.error('Password is required');
      return false;
    }
    return true;
  };

  const callLogin = async (): Promise<void> => {
    if (validateForm()) {
      const response: LoginResponse = await mutateAsync({
        email: email,
        password: password,
      });
      if (response !== undefined) {
        axios.defaults.headers.Authorization = 'Bearer ' + response.jwtToken;
        setLoginResponse(response);
        navigate('/', { replace: true });
      } else {
        toast.error('Invalid Credential !!');
      }
    }
  };

  return (
    <>
      {isLoading ? (
        <FullScreenLoading text=" Please wait while we sign you in" />
      ) : (
        <div className="my-5 text-center">
          <HeaderText label="Login" />
          <div className="w-1/3 text-center p-2 mx-auto my-2">
            <TextInput
              labelPosition="top"
              placeholderText="Email"
              value={email}
              onChange={(value) => setEmail(value)}
              type="email"
            />
            <TextInput
              labelPosition="top"
              placeholderText="Password"
              value={password}
              onChange={(value) => setPassword(value)}
              type="password"
            />
            <p
              className="text-right underline cursor-pointer hover:text-gray-400"
              onClick={() => {
                navigate('/forgot-password');
              }}
            >
              Forgot Password?
            </p>
            <PrimaryButton
              buttonText="Login"
              onClick={async () => {
                await callLogin();
              }}
            />
            <p className="text-center">
              Do not have an account?{' '}
              <span
                className="underline cursor-pointer hover:text-gray-400"
                onClick={() => {
                  navigate('/signup');
                }}
              >
                Register Here
              </span>
            </p>
          </div>
        </div>
      )}
    </>
  );
};
