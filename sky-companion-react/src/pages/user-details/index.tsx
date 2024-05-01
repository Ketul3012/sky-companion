/**
 * This file contains the implementation of the UserDetails page component.
 *
 * The UserDetails page displays the details of a specific user. It fetches the user details
 * using the `useGetUserDetails` hook and renders the user information on the page.
 *
 * This file is important to the project as it provides the functionality to view and display
 * the details of a user, allowing users to see their own information or the information of
 * other users.
 */
import { useParams } from 'react-router-dom';
import { PrimaryButton } from '../../components/button/PrimaryButton';
import { DisplayReview } from '../../components/display-review/DisplayReview';
import { FullScreenLoading } from '../../components/loading/FullScreenLoading';
import { ReviewForm } from '../../components/review-form/ReviewForm';
import { HeaderText } from '../../components/text/HeaderText';
import { formatToMMDDYYYYWithoutTimestamp } from '../../utils/date-utils';
import { useGetUserDetails } from './hook/useGetUserDetails';

/**
 * This method generate user details component with making API call to fetch details
 * @returns component with user details
 */
export const UserDetails = () => {
  const { id } = useParams();
  const { data, isLoading } = useGetUserDetails(parseInt(id || ''), !!id);

  return isLoading ? (
    <FullScreenLoading />
  ) : (
    <div className="flex flex-col w-full h-full items-start pl-10 mt-4">
      <HeaderText label="User details" className="mb-4 font-bold" />
      <h1 className="text-lg">
        Name: {data?.firstName + ' ' + data?.lastName}
      </h1>
      <div className="flex flex-row items-center">
        <h1 className="text-lg mr-4">Email: {data?.email}</h1>
        <PrimaryButton
          buttonText="Send mail"
          onClick={() => {
            window.location.href = 'mailto:' + data?.email;
          }}
        />
      </div>
      <h1 className="text-lg">
        Date of birth: {formatToMMDDYYYYWithoutTimestamp(data?.dob || '')}
      </h1>
      <h1 className="text-lg">
        Mobile Number: {data?.mobileNumber ? `+1 ${data?.mobileNumber}` : '-'}
      </h1>
      <h1 className="text-lg">
        Known Languages:{' '}
        {data?.languages !== undefined && data?.languages.length > 0
          ? data?.languages.flatMap((item) => item.language).join(',')
          : '-'}
      </h1>
      <h1 className="text-lg">
        Address:{' '}
        {data?.address
          ? `${data?.address.addressLine1}, ${data?.address.addressLine2}, ${data?.address.postalCode}, ${data?.address.city}, ${data?.address.province}, ${data?.address.country}`
          : '-'}
      </h1>
      <HeaderText label="Reviews" className="mt-2 font-bold" />
      <DisplayReview
        overallRating={data?.ratings || 0}
        reviewsList={data?.reviews || []}
      />
      <ReviewForm companionId={data?.id || -1} />
    </div>
  );
};
