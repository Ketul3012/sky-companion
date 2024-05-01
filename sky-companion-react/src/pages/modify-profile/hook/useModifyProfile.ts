/**
 * Represents the request object for modifying a user's profile.
 * This interface defines the structure of the data required to update a user's profile.
 * It includes properties such as id, firstname, lastname, address, city, province, mobile number, country, date of birth, postal code, and languages.
 * The id property represents the unique identifier of the user.
 * The firstname and lastname properties represent the user's first name and last name respectively.
 * The addressLine1 and addressLine2 properties represent the user's address.
 * The city and province properties represent the user's city and province respectively.
 * The mobileNumber property represents the user's mobile number.
 * The country property represents the user's country.
 * The dob property represents the user's date of birth.
 * The postalCode property represents the user's postal code.
 * The languages property is an array of numbers representing the languages known by the user.
 *
 * This file is important to the project as it provides the type definition for the request object used in the modify profile functionality.
 * It ensures that the data passed to the modify profile API adheres to the specified structure and data types.
 */

// useModifyProfile.ts
import { useMutation } from '@tanstack/react-query';
import { ApiSuccessResponse } from '../../../model/api-success-response';
import { ApiEndpoints } from '../../../utils/api-endpoints';
import { postRequest } from '../../../utils/axios';
import { ReactQueryNames } from '../../../utils/react-query-names';

interface ModifyProfileRequest {
  id: number;
  firstname: string;
  lastname: string;
  addressLine1: string;
  addressLine2: string;
  city: string;
  province: string;
  mobileNumber: string;
  country: string;
  dob: string;
  postalCode: string;
  languages: number[];
}

const updateProfileUpdater = async (request: ModifyProfileRequest) => {
  const response = await postRequest<ApiSuccessResponse<string>>(
    ApiEndpoints.MODIFY_PROFILE,
    request,
  );
  return response.data;
};

export const useModifyProfile = () => {
  const mutation = useMutation({
    mutationKey: [ReactQueryNames.MODIFY_PROFILE],
    mutationFn: updateProfileUpdater,
  });

  return {
    ...mutation,
  };
};
