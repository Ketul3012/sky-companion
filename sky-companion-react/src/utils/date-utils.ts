/**
 * Utility functions for formatting dates using the dayjs library.
 * This file provides functions to format date strings into different formats.
 * These functions are important to the project as they allow for consistent and standardized formatting of dates throughout the application.
 */

import dayjs from 'dayjs/esm';

/**
 * Formats the given date string to the "MM-DD-YYYY HH:mm:ss" format using dayjs.
 * @param date - The date string to be formatted.
 * @returns The formatted date string.
 */
export const formatToMMDDYYYY = (date: string): string => {
  return dayjs(date).format('MM-DD-YYYY HH:mm:ss');
};

/**
 * Formats the given date string to the "MM-DD-YYYY" format using dayjs.
 * @param date - The date string to be formatted.
 * @returns The formatted date string.
 */
export const formatToMMDDYYYYWithoutTimestamp = (date: string): string => {
  return dayjs(date).format('MM-DD-YYYY');
};

/**
 * Formats the given date string to the "YYYY-MM-DD" format using dayjs.
 * @param date - The date string to be formatted.
 * @returns The formatted date string.
 */
export const formatToYYYYMMDDWithoutTimeStamp = (date: string): string => {
  return dayjs(date).format('YYYY-MM-DD');
};

/**
 * Formats the given date string to the "YYYY-MM-DD HH:mm:ss" format using dayjs.
 * @param date - The date string to be formatted.
 * @returns The formatted date string.
 */
export const formatToYYYYMMDD = (date: string): string => {
  return dayjs(date).format('YYYY-MM-DD HH:mm:ss');
};
