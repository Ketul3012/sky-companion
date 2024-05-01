/**
 * Renders the given component if the user is authenticated, otherwise navigates to the login page.
 *
 * @param auth - A boolean value indicating whether the user is authenticated or not.
 * @param children - The component to render when the user is authenticated.
 * @returns The rendered component if the user is authenticated, otherwise a navigation to the login page.
 *
 * This file is important to the project because it provides a convenient way to protect routes and control access to certain parts of the application based on the user's authentication status.
 */

import { Navigate } from 'react-router-dom';
import { JSX } from 'react/jsx-runtime';

/**
 * this function navigate to login page if user is not authenticated else render the given component
 * @param auth - boolean to check user is authenticated or not
 * @param children - children to render when user is authenticated
 * @returns
 */
export const ProtectedRoute = ({
  auth,
  children,
}: {
  auth: boolean;
  children: JSX.Element;
}) => {
  if (!auth) {
    return <Navigate to="/login" replace />;
  }

  return children;
};
