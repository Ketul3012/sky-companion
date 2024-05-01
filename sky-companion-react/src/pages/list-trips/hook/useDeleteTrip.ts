import { useMutation, useQueryClient } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { deleteRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';

/**
 * fetcher method which call delete-trip endpoint
 * @param request
 * @returns
 */
const fetcher = async (id: number) => {
  const response = await deleteRequest<ApiSuccessResponse<string>>(
    ApiEndpoints.DELETE_TRIP + '/' + id,
  );
  return response.data;
};

/**
 * hook method to call delete-trip endpoint using {@link useMutation} from react-query
 * A custom hook for deleting a trip.
 * This hook is used to handle the deletion of a trip in the project.
 * It utilizes React Query for managing the mutation and invalidating the list of trips query.
 *
 * @returns An object containing the mutation details.
 */
export const useDeleteTrip = () => {
  const reactQueryClient = useQueryClient();

  const useMutationDetails = useMutation({
    mutationFn: fetcher,
    mutationKey: [ReactQueryNames.DELETE_TRIP],
    onSettled: async () => {
      await reactQueryClient.invalidateQueries([ReactQueryNames.LIST_TRIPS]);
    },
  });
  return {
    ...useMutationDetails,
  };
};
