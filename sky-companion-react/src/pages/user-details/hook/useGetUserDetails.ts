/**
 * This code defines a custom hook called useGetUserDetails that is used to fetch user details
 * based on the provided ID.
 */
import { useQuery } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { getRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';
import { UserDTO } from '../../list-trips/hook/useListTrips';

/**
 * fetcher method which call get-user endpoint
 * @param request
 * @returns
 */
const fetcher = async (id: number) => {
  const response = await getRequest<ApiSuccessResponse<UserDTO>>(
    ApiEndpoints.GET_USER + '/' + id,
  );
  return response.data.data;
};

/**
 * Custom hook to fetch user details based on the provided ID.
 *
 * @param id - The ID of the user.
 * @param enabled - Determines whether the query should be enabled or not.
 * @returns An object containing the query details.
 */
export const useGetUserDetails = (id: number, enabled: boolean) => {
  const useQueryDetails = useQuery({
    queryFn: () => fetcher(id),
    queryKey: [ReactQueryNames.GET_USER],
    enabled: enabled,
  });
  return {
    ...useQueryDetails,
  };
};
