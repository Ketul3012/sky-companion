import { useQuery } from '@tanstack/react-query';
import { useState } from 'react';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { postRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';

/**
 * request for trips listing
 */
export interface ListTripsRequest {
  from?: string;
  to?: string;
  date?: string;
  user?: string;
  companionCity?: string;
  maximumCost?: number;
  languageIds?: number[];
}

/**
 * response for trip list item
 */
export interface ListTripItem {
  id: number;
  source: string;
  destination: string;
  departure: string;
  arrival: string;
  userId: number;
  user: UserDTO;
  price: number;
  stops?: Stop[];
}

export interface Stop {
  id: number;
  name: string;
  departure: string;
  arrival: string;
}

export interface UserDTO {
  id: number;
  firstName: string;
  lastName: string;
  email: string;
  isActive: boolean;
  dob: string;
  mobileNumber?: string;
  languages: Language[];
  address: AddressDTO;
  ratings: number;
  reviews: Reviews[];
}

export interface AddressDTO {
  id: number;
  addressLine1: string;
  addressLine2: string;
  postalCode: string;
  city: string;
  province: string;
  country: string;
}

export interface Language {
  id: number;
  language: string;
}

export interface Reviews {
  id: number;
  userId: number;
  userFirstName: string;
  userLastName: string;
  companionId: number;
  companionFirstName: string;
  companionLastName: string;
  rating: number;
  description: string;
  createdStamp: string;
}

/**
 * response for trip listings
 */
export interface ListTripsResponse {
  trips: ListTripItem[];
  count: number;
}

/**
 * fetcher method which call list-trips method
 * @param request
 * @returns
 */
const fetcher = async (request: ListTripsRequest) => {
  const response = await postRequest<ApiSuccessResponse<ListTripsResponse>>(
    ApiEndpoints.LIST_TRIPS,
    request,
  );
  return response.data.data;
};

/**
 * Custom hook for listing trips.
 * This hook manages the state and query for listing trips based on various filters.
 * It provides functions to set the filter values and returns the query result along with the filter values.
 *
 * @returns An object containing the query result and functions to set filter values.
 */
export const useListTrips = () => {
  // State variables for filter values
  const [from, setFrom] = useState<string | undefined>(undefined);
  const [to, setTo] = useState<string | undefined>(undefined);
  const [date, setDate] = useState<string | undefined>(undefined);
  const [user, setUser] = useState<string | undefined>(undefined);
  const [companionCity, setCompanionCity] = useState<string | undefined>(
    undefined,
  );
  const [maximumCost, setMaximumCost] = useState<number>(500);
  const [languageIds, setLanguageIds] = useState<number[]>([]);

  // Query for listing trips based on filter values
  const useListTripsQuery = useQuery({
    queryKey: [
      ReactQueryNames.LIST_TRIPS,
      from,
      to,
      date,
      user,
      companionCity,
      maximumCost,
      languageIds,
    ],
    queryFn: () =>
      fetcher({
        from,
        to,
        date,
        user,
        companionCity,
        maximumCost,
        languageIds,
      }),
  });

  // Return the query result along with functions to set filter values
  return {
    ...useListTripsQuery,
    from,
    setFrom,
    to,
    setTo,
    date,
    setDate,
    user,
    setUser,
    companionCity,
    setCompanionCity,
    maximumCost,
    setMaximumCost,
    languageIds,
    setLanguageIds,
  };
};
