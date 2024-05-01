/**
 * Renders the Home page component.
 *
 * This component displays a banner image and two sections:
 * - "Looking for a companions to help you in your trips?" section, which provides information about finding travel companions.
 * - "Want to help someone along your journey?" section, which provides information about publishing trip details to help others find travel companions.
 *
 * This file is important to the project as it defines the Home page, which is a key component of the application's user interface.
 * It allows users to easily navigate to the sections related to finding or being a travel companion.
 */

import banner from '../../assets/banner.jpg';
import beCompanion from '../../assets/beCompanion.png';
import lookingCompanion from '../../assets/lookingCompanion.jpg';

export const Home = () => {
  return (
    <div>
      <img src={banner} alt="Logo" className="w-full h-auto " />

      <div className="flex flex-row w-full justify-evenly my-4">
        <div className="max-w-sm bg-blue-950 rounded-lg z-[5px] shadow-md pb-8">
          <img
            className="w-full h-[240px] rounded-t-lg"
            src={lookingCompanion}
            alt=""
          />
          <div className="p-5">
            <h5 className="mb-2 text-2xl font-bold tracking-tight text-white">
              Looking for a companions to help you in your trips?
            </h5>
            <p className="mb-3 font-normal text-gray-100">
              Here find the friendly companions who will assist you in your
              trips.
            </p>
            <a
              href="/list-trips"
              className="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-500 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300"
            >
              View Trips
              <svg
                className="rtl:rotate-180 w-3.5 h-3.5 ms-2"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 14 10"
              >
                <path
                  stroke="currentColor"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M1 5h12m0 0L9 1m4 4L9 9"
                />
              </svg>
            </a>
          </div>
        </div>
        <div className="max-w-sm bg-blue-950 rounded-lg z-[5px] shadow-md pb-8">
          <img
            className="w-full h-[240px] rounded-t-lg"
            src={beCompanion}
            alt=""
          />
          <div className="p-5">
            <h5 className="mb-2 text-2xl font-bold tracking-tight text-white">
              Want to help someone along your journey?
            </h5>
            <p className="mb-3 font-normal text-gray-100">
              Here publish your trip details and help others find you to be
              their travel companion.
            </p>
            <a
              href="/new-trip"
              className="inline-flex items-center px-3 py-2 text-sm font-medium text-center text-white bg-blue-500 rounded-lg hover:bg-blue-800 focus:ring-4 focus:outline-none focus:ring-blue-300"
            >
              Be Companion
              <svg
                className="rtl:rotate-180 w-3.5 h-3.5 ms-2"
                aria-hidden="true"
                xmlns="http://www.w3.org/2000/svg"
                fill="none"
                viewBox="0 0 14 10"
              >
                <path
                  stroke="currentColor"
                  strokeLinecap="round"
                  strokeLinejoin="round"
                  strokeWidth="2"
                  d="M1 5h12m0 0L9 1m4 4L9 9"
                />
              </svg>
            </a>
          </div>
        </div>
      </div>
    </div>
  );
};
