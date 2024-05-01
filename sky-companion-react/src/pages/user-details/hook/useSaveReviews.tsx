/**
 * This is an implementation of a custom hook called useSaveReviews that is responsible for
 * handling the mutation to save reviews and invalidating the user query cache.
 * It is written to provide a reusable hook for saving reviews and updating
 * user details in a React application.
 */

import { useMutation, useQueryClient } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { postRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';

interface SaveReviewsRequest {
  companionId: number;
  description: string;
  rating: number;
}

export interface SaveReviewsResponse {
  responseData: string;
}

const updater = async (request: SaveReviewsRequest) => {
  const response = await postRequest<ApiSuccessResponse<SaveReviewsResponse>>(
    ApiEndpoints.SAVE_REVIEWS,
    request,
  );
  return response.data.data;
};

/**
 * Custom hook for saving reviews.
 * This hook is responsible for handling the mutation to save reviews and invalidating the user query cache.
 * It is an important file in the project as it provides a reusable hook for saving reviews and updating the user details.
 *
 * @returns The mutation function for saving reviews.
 */
export const useSaveReviews = () => {
  const reactQueryClient = useQueryClient();

  return useMutation({
    mutationKey: [ReactQueryNames.SAVE_REVIEWS],
    mutationFn: updater,
    onSettled: async () => {
      await reactQueryClient.invalidateQueries([ReactQueryNames.GET_USER]);
    },
  });
};
