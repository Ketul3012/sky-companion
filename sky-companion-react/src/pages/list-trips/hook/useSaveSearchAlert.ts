/**
 * Updates the search alert by making a POST request to the server.
 * @param request list trips request class
 * updater method used by save search alert react-query mutation
 * inside the method it calls save-search-alert endpoint using axios postRequest method
 * @returns
 */
import { useMutation } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { postRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';
import { ListTripsRequest } from './useListTrips';

const updater = async (request: ListTripsRequest) => {
  const response = await postRequest<ApiSuccessResponse<string>>(
    ApiEndpoints.SAVE_SEARCH_ALERT,
    request,
  );
  return response.data;
};

/**
 * hook method to call save-search-alert endpoint using {@link useQuery} from react-query
 * @returns
 */
export const useSaveSearchAlerts = () => {
  return useMutation({
    mutationKey: [ReactQueryNames.SAVE_SEARCH_ALERT],
    mutationFn: updater,
  });
};
