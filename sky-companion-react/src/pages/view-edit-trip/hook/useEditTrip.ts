/**
 * This code is a part of a larger codebase that implements a custom hook called useEditTrip.
 * The purpose of this hook is to handle the logic for making a mutation request to update
 * a trip with the provided request data.
 */

import { useMutation } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { postRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';

interface EditTripRequest {
  id: number;
  price: number;
}

/**
 * Updates a trip with the provided request data.
 *
 * @param request - The request object containing the trip ID and the updated price.
 * @returns response or error message
 */
const updater = async (request: EditTripRequest) => {
  const response = await postRequest<ApiSuccessResponse<string>>(
    ApiEndpoints.EDIT_TRIP + '/' + request.id,
    {
      price: request.price,
    },
  );
  return response.data;
};

/**
 * hook method to call edit-trip endpoint using {@link useMutation} from react-query
 * @returns
 */
export const useEditTrip = () => {
  const useMutationDetails = useMutation({
    mutationFn: updater,
    mutationKey: [ReactQueryNames.EDIT_TRIP],
  });

  return {
    ...useMutationDetails,
  };
};
