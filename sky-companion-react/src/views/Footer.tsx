/**
 * Footer component for the whole site.
 *
 * This component represents the footer section of the website. It displays the company name, links to the "About Us" and "Contact Us" pages, the company logo, and copyright information.
 *
 * The Footer component is an important part of the project as it provides a consistent and visually appealing footer across all pages of the website. It enhances the user experience by providing easy access to important links and reinforcing the branding of the company.
 *
 * @returns The rendered Footer component.
 */

import { Link } from 'react-router-dom';
import logo from '../assets/logo.png';

export const Footer = () => {
  return (
    <div className="flex flex-row w-full px-6 pb-10 pt-4 text-left bg-footer">
      <div className="flex flex-row flex-1">
        <div className="ml-2 mr-10">
          <h1 className="text-2xl text-black">Services</h1>
          <Link
            to="/list-trips"
            className="text-black hover:text-blue-800 text-1xl"
          >
            View Trips
          </Link>
          <br />
          <Link
            to="/new-trip"
            className="text-black hover:text-blue-800 text-1xl"
          >
            Be Companion
          </Link>
        </div>
        <div>
          <h1 className="text-2xl text-black">Company</h1>
          <Link to="/about" className="text-black hover:text-blue-800 text-1xl">
            About Us
          </Link>
          <br />
          <Link
            to="/contact"
            className="text-black hover:text-blue-800 text-1xl"
          >
            Contact Us
          </Link>
        </div>
      </div>

      <div className="relative flex flex-row flex-1 mt-2">
        <div className="flex flex-row-reverse items-center mr-0 flex-1">
          <img
            src={logo}
            alt="Logo"
            className="align-right shrink-0 aspect-square w-20 mr-2 rounded-full justify-self-end border-blue-200 border-[2px]"
          />
        </div>
        <div className="flex-2 self-center">
          <h1 className="flex-1 text-black text-1xl">© 2023 Sky Companion</h1>
          <h1 className="text-black text-1xl">Built by Dalhousie Students</h1>
        </div>
      </div>
    </div>
  );
};
