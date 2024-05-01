import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { PrimaryButton } from '../../components/button/PrimaryButton';
import { TextInput } from '../../components/input/TextInput';
import { FullScreenLoading } from '../../components/loading/FullScreenLoading';
import { HeaderText } from '../../components/text/HeaderText';
import { ApiSuccessResponse } from '../../model/api-success-response';
import { useForgotPassword } from './hook/useForgotPassword';

/**
 * This function renders the forgot password page and contains it's business logic
 * @returns
 */
export const ForgotPassword = () => {
  const { isLoading, mutateAsync, email, setEmail } = useForgotPassword();
  const navigate = useNavigate();

  /**
   * this method validates reset-password form
   * @returns true or false based on validation result
   */
  const validateForm = () => {
    if (!email) {
      toast.error('Email is required');
      return false;
    }

    return true;
  };

  /**
   * Calls the forgot password API endpoint to reset the user's password.
   *
   * This function is responsible for validating the form inputs and making an API call to the forgot password endpoint.
   * If the form inputs are valid, it sends an email to the provided email address with instructions to reset the password.
   * If the API call is successful, it displays a success message and navigates the user to the home page.
   * If the API call fails, it displays an error message.
   */
  const _callForgotPassword = async () => {
    if (validateForm()) {
      const response: ApiSuccessResponse<string> = await mutateAsync({
        email: email,
      });
      if (response.statusCode === 200) {
        toast.success(response.data);
        navigate('/', { replace: true });
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
          <HeaderText label="Forgot password" />
          <div className="w-1/3 text-center p-2 mx-auto my-2">
            <h1>
              Please enter your registered email address, we will send you reset
              password link.
            </h1>
            <TextInput
              labelPosition="top"
              placeholderText="Email"
              value={email}
              onChange={(value) => setEmail(value)}
              type="email"
            />
            <PrimaryButton
              buttonText="Send Email"
              onClick={async () => {
                await _callForgotPassword();
              }}
            />
          </div>
        </div>
      )}
    </>
  );
};
