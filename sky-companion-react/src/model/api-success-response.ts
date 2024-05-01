/**
 * Represents a generic response model for API success.
 * This interface is used to define the structure of a successful API response.
 * It contains the statusCode, statusMessage, and data properties.
 * - The statusCode property represents the HTTP status code of the response.
 * - The statusMessage property represents the status message of the response.
 * - The data property represents the actual data returned by the API.
 *
 * @template T - The type of the data returned by the API.
 *
 * @remarks
 * This file is important to the project as it provides a standardized structure for handling successful API responses.
 * By using this interface, developers can easily access the statusCode, statusMessage, and data properties of the response.
 * This helps in maintaining consistency and improves code readability when working with API responses.
 */

export interface ApiSuccessResponse<T> {
  statusCode: number;
  statusMessage: string;
  data: T;
}
