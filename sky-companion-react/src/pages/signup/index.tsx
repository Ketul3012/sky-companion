/**
 * This code is a React component that renders a sign-up page and handles its business logic. 
 * The purpose of this code is to provide a user interface for users to sign up for an application. 
 * It collects user input for fields such as first name, last name, email, password, date of birth, 
 * and mobile number.

 * The code is written in order to:
 * Render the sign-up form with input fields and a sign-up button.
 * Handle user input and update the component's state accordingly.
 * Validate the form data to ensure that all required fields are filled out correctly.
 */

import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { PrimaryButton } from '../../components/button/PrimaryButton';
import { TextInput } from '../../components/input/TextInput';
import { FullScreenLoading } from '../../components/loading/FullScreenLoading';
import { HeaderText } from '../../components/text/HeaderText';
import { useSignUp } from './hook/useSignUp';

/**
 * This function renders the login page and contains it's business logic
 * @returns
 */
export const SignUp = () => {
  const { isLoading, mutateAsync } = useSignUp();
  const navigate = useNavigate();

  const [email, setEmail] = useState<string>('');
  const [firstName, setFirstName] = useState<string>('');
  const [lastName, setLastName] = useState<string>('');
  const [password, setPassword] = useState<string>('');
  const [confirmPassword, setConfirmPassword] = useState<string>('');
  const [dob, setDob] = useState<string>('');
  const [mobileNumber, setMobileNumber] = useState<string>('');

  /**
   * Validates the form data for the signup page.
   *
   * This function checks if all the required fields are filled out correctly.
   * If any required field is missing or if the passwords do not match, it displays an error toast message.
   *
   * @returns {boolean} Returns true if the form data is valid, otherwise false.
   */
  const validateForm = () => {
    if (!firstName) {
      toast.error('First Name is required');
      return false;
    }

    if (!lastName) {
      toast.error('Last Name is required');
      return false;
    }

    if (!email) {
      toast.error('Email is required');
      return false;
    }

    if (password !== confirmPassword) {
      toast.error('Passwords do not match');
      return false;
    }

    if (!password) {
      toast.error('Password is required');
      return false;
    }

    if (!confirmPassword) {
      toast.error('Confirm Password is required');
      return false;
    }

    if (!dob) {
      toast.error('Date of Birth is required');
    }

    if (!mobileNumber) {
      toast.error('Mobile Number is required');
      return false;
    }
    return true;
  };

  const callSignUp = async () => {
    if (validateForm()) {
      const response = await mutateAsync({
        firstName: firstName,
        lastName: lastName,
        email: email,
        password: password,
        confirmPassword: confirmPassword,
        dob: dob,
        mobileNumber: mobileNumber,
      });
      if (response.statusCode === 401 || response.statusCode === 400) {
        toast.error(response.statusMessage);
      }
      if (response.statusCode === 200) {
        toast.success('Successfully signed up Please verify email to login.');
        navigate('/', { replace: true });
      }
    }
  };

  return (
    <>
      <>
        {isLoading ? (
          <FullScreenLoading text=" Please wait while we sign you in" />
        ) : (
          <div className="my-5 text-center">
            <HeaderText label="Sign Up" />
            <div className="w-1/3 text-center p-2 mx-auto my-2">
              <TextInput
                labelPosition="top"
                placeholderText="First Name"
                value={firstName}
                onChange={(value) => setFirstName(value)}
              />
              <TextInput
                labelPosition="top"
                placeholderText="Last Name"
                value={lastName}
                onChange={(value) => setLastName(value)}
              />
              <TextInput
                labelPosition="top"
                placeholderText="Email"
                value={email}
                onChange={(value) => setEmail(value)}
              />
              <TextInput
                type="date"
                labelPosition="top"
                placeholderText="Date of Birth"
                value={dob}
                onChange={(value) => setDob(value)}
              />
              <TextInput
                type="tel"
                labelPosition="top"
                placeholderText="Mobile Number"
                value={mobileNumber}
                onChange={(value) => setMobileNumber(value)}
              />
              <TextInput
                type="password"
                labelPosition="top"
                placeholderText="Password"
                value={password}
                onChange={(value) => setPassword(value)}
              />
              <TextInput
                type="password"
                labelPosition="top"
                placeholderText="Confirm Password"
                value={confirmPassword}
                onChange={(value) => setConfirmPassword(value)}
              />
              <PrimaryButton
                className="my-3 py-2 px-8 rounded-lg"
                buttonText="Sign Up"
                onClick={async () => {
                  await callSignUp();
                }}
              />
              <p className="text-center cursor-pointer">
                Already a User?{' '}
                <Link to={'/login'}>
                  <span className="cursor-pointer">Login</span>
                </Link>
              </p>
            </div>
          </div>
        )}
      </>
    </>
  );
};
