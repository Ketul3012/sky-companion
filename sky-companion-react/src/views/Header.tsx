/**
 * This file represents the header component of the application.
 *
 * The Header component is responsible for rendering the top navigation bar of the application.
 * It includes the logo, title, and buttons for login, signup, and account management.
 *
 * This component is important to the project as it provides a consistent and easily accessible
 * navigation interface for users to interact with different pages and features of the application.
 */

import { useNavigate } from 'react-router-dom';
import ReactTyped from 'react-typed';
import logo from '../assets/logo.png';
import { PrimaryButton } from '../components/button/PrimaryButton';
import { useLoginStore } from '../pages/login/store';

export const Header = () => {
  const navigate = useNavigate();
  const { loginResponse, setLoginResponse } = useLoginStore();

  const handleLogout = () => {
    setLoginResponse(undefined);
    navigate('/login');
  };

  return (
    <div className="sticky top-0">
      <div className="flex w-full p-4 border-b-blue-300 shadow-blue-300 shadow-sm z-20 border-2 bg-white justify">
        <div className="flex w-full justify">
          <a href="/home">
            <div className="relative inset-x-0 top-0 flex flex-row items-center">
              <img
                src={logo}
                alt="Logo"
                className="aspect-square w-12 rounded-[50%] border-blue-200 border-[2px]"
              />
              <h1 className="ml-1 text-2xl text-blue-500 font-semibold overflow-hidden inline-block">
                <ReactTyped
                  strings={['Sky Companion']}
                  typeSpeed={100}
                  showCursor={false}
                />
              </h1>
            </div>
          </a>
        </div>
        <div className="flex justify-end w-full">
          {loginResponse ? (
            <>
              <PrimaryButton
                className={`mr-4`}
                rounded="rounded-full"
                onClick={() => {
                  navigate('/modify-profile');
                }}
                buttonText="My Account"
              />

              <PrimaryButton
                rounded="rounded-full"
                onClick={() => {
                  handleLogout();
                }}
                buttonText="Logout"
              />
            </>
          ) : (
            <>
              <PrimaryButton
                className={`mr-4`}
                rounded="rounded-full"
                onClick={() => {
                  navigate('/login');
                }}
                buttonText="Login"
              />
              <PrimaryButton
                rounded="rounded-full"
                onClick={() => {
                  navigate('/signup');
                }}
                buttonText="Signup"
              />
            </>
          )}
        </div>
      </div>
    </div>
  );
};
