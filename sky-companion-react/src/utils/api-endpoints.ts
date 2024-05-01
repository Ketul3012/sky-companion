/**
 * This file contains the definition of the `ApiEndpoints` object, which maintains all API endpoint names.
 *
 * The `ApiEndpoints` object is important to this project as it provides a centralized location to store and access the names of all API endpoints used in the application. By using this object, developers can easily reference the endpoints without hardcoding the URLs throughout the codebase. This promotes code reusability, maintainability, and reduces the risk of introducing errors when modifying or adding new endpoints.
 */

export const ApiEndpoints = {
  LOGIN: '/login',
  LIST_TRIPS: '/list-trips',
  SIGNUP: '/signup',
  SAVE_SEARCH_ALERT: '/save-search-alert',
  FORGOT_PASSWORD: '/forgot-password',
  RESET_PASSWORD: '/reset-password',
  SAVE_REVIEWS: '/save-reviews',
  GET_USER: '/get-user',
  DELETE_TRIP: '/delete-trip',
  GET_TRIP: '/get-trip',
  EDIT_TRIP: '/edit-trip',
  ADD_TRIP: '/add-trip',
  MODIFY_PROFILE: '/modify-profile',
};
