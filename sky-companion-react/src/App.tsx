/**
 * The main component of the application that declares all routes available in the application.
 * This component also includes a common header and footer component.
 *
 * @returns The rendered JSX of the App component.
 */

import { Route, Routes } from 'react-router-dom';
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import { AboutUs } from './pages/about-us';
import { ContactUs } from './pages/contact-us';
import { ForgotPassword } from './pages/forgot-password';
import { Home } from './pages/home';
import { ListTrips } from './pages/list-trips';
import { Login } from './pages/login';
import { useLoginStore } from './pages/login/store';
import { ModifyProfile } from './pages/modify-profile';
import Newtrip from './pages/new-trip';
import { NoMatch } from './pages/no-match';
import { ResetPassword } from './pages/reset-password';
import { SignUp } from './pages/signup';
import { UserDetails } from './pages/user-details';
import ViewEditTrip from './pages/view-edit-trip';
import { ProtectedRoute } from './utils/protected-route';
import { Footer } from './views/Footer';
import { Header } from './views/Header';

export const App = () => {
  const { loginResponse } = useLoginStore();

  return (
    <div className="w-screen h-screen bg-white flex flex-col overflow-y-auto">
      <Header />
      <div className="flex flex-col flex-1 justify-center">
        <ToastContainer />
        <Routes>
          <Route
            path="/"
            Component={() => (
              <ProtectedRoute auth={!!loginResponse}>
                <Home />
              </ProtectedRoute>
            )}
          />
          <Route
            path="/list-trips"
            Component={() => (
              <ProtectedRoute auth={!!loginResponse}>
                <ListTrips />
              </ProtectedRoute>
            )}
          />
          <Route
            path="/user-details/:id"
            Component={() => (
              <ProtectedRoute auth={!!loginResponse}>
                <UserDetails />
              </ProtectedRoute>
            )}
          />
          <Route
            path="/new-trip"
            Component={() => (
              <ProtectedRoute auth={!!loginResponse}>
                <Newtrip />
              </ProtectedRoute>
            )}
          />
          <Route
            path="/view-edit-trip/:id"
            Component={() => (
              <ProtectedRoute auth={!!loginResponse}>
                <ViewEditTrip />
              </ProtectedRoute>
            )}
          />
          <Route
            path="/modify-profile"
            Component={() => (
              <ProtectedRoute auth={!!loginResponse}>
                <ModifyProfile />
              </ProtectedRoute>
            )}
          />
          <Route path="/login" Component={Login} />
          <Route path="/signup" Component={SignUp} />
          <Route path="/forgot-password" Component={ForgotPassword} />
          <Route path="/reset-password" Component={ResetPassword} />
          <Route path="/about" Component={AboutUs} />
          <Route path="/contact" Component={ContactUs} />
          <Route path="/home" Component={Home} />
          <Route path="*" Component={NoMatch} />
        </Routes>
      </div>
      <Footer />
    </div>
  );
};
