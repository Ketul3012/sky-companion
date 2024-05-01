/**
 * This file contains the implementation of the `useGetTrip` hook, which is used to fetch trip details from the server.
 * The hook utilizes the `useQuery` function from the `react-query` library to handle the data fetching and caching.
 *
 * The `useGetTrip` hook takes an `id` parameter representing the trip ID and an `enabled` parameter indicating whether the hook should be enabled or not.
 * When enabled, the hook makes a GET request to the server using the `fetcher` function, which calls the `GET_TRIP` endpoint.
 * The response data is then returned and can be accessed through the `useQueryDetails` object returned by the hook.
 *
 * This file is important to the project as it provides a reusable hook for fetching trip details, allowing other components to easily retrieve and display trip information.
 * It abstracts away the implementation details of the data fetching logic and provides a clean interface for interacting with the server.
 * By using the `react-query` library, it also provides caching and automatic data refetching capabilities, improving performance and user experience.
 */
import { useQuery } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { getRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';
import { ListTripItem } from '../../list-trips/hook/useListTrips';

/**
 * fetcher method which calls the GET_TRIP endpoint to fetch trip details.
 * @param id - The ID of the trip to fetch.
 * @returns The trip details.
 */
const fetcher = async (id: number) => {
  const response = await getRequest<ApiSuccessResponse<ListTripItem>>(
    ApiEndpoints.GET_TRIP + '/' + id,
  );
  return response.data.data;
};

/**
 * Hook method to fetch trip details using the `useQuery` function from `react-query`.
 * @param id - The ID of the trip to fetch.
 * @param enabled - Whether the hook should be enabled or not.
 * @returns An object containing the trip details and query status.
 */
export const useGetTrip = (id: number, enabled: boolean) => {
  const useQueryDetails = useQuery({
    queryFn: () => fetcher(id),
    queryKey: [ReactQueryNames.GET_TRIP],
    enabled: enabled,
  });
  return {
    ...useQueryDetails,
  };
};
