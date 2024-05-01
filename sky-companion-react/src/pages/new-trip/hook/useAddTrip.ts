/**
 * The provided code is a custom hook called useAddTrip that is used for adding a new trip.
 * It utilizes the useMutation hook from the react-query library to handle the mutation for adding a new trip.
 */
import { useMutation } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { postRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';

export interface AddTripRequest {
  price: number;
  from: string;
  to: string;
  departureTime: string;
  arrivalTime: string;
  flightNumber: string;
  pnr: string;
  stops: StopsRequest[];
}

export interface StopsRequest {
  name: string;
  departureTime: string;
  arrivalTime: string;
}

/**
 * updater method which call add-trip endpoint
 * @param request addTrip request with all details
 * @returns response or error message
 */
const updater = async (request: AddTripRequest) => {
  const response = await postRequest<ApiSuccessResponse<string>>(
    ApiEndpoints.ADD_TRIP,
    request,
  );
  return response.data;
};

/**
 * Custom hook for adding a new trip.
 *
 * This hook utilizes the `useMutation` hook from React Query to handle the mutation for adding a new trip.
 * It provides the necessary mutation details and can be used in the component that needs to add a trip.
 *
 * @returns An object containing the mutation details provided by the `useMutation` hook.
 */
export const useAddTrip = () => {
  const useMutationDetails = useMutation({
    mutationFn: updater,
    mutationKey: [ReactQueryNames.ADD_TRIP],
  });

  return {
    ...useMutationDetails,
  };
};
