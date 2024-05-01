/**
 * Renders a component that displays reviews for a particular item.
 *
 * @param {number} overallRating - The overall rating for the item.
 * @param {Reviews[]} reviewsList - The list of reviews for the item.
 * @returns {JSX.Element} The rendered component.
 */

import { Reviews } from '../../pages/list-trips/hook/useListTrips';
import { formatToMMDDYYYY } from '../../utils/date-utils';

export const DisplayReview = ({
  overallRating,
  reviewsList,
}: {
  overallRating: number;
  reviewsList: Reviews[];
}) => {
  return (
    <>
      <div className="w-[60%]">
        <p>Overall Rating: {overallRating}</p>
        <div className="max-h-96 overflow-y-auto p-4">
          {reviewsList.map((review, index) => (
            <div key={index} className="flex flex-col py-2">
              <p>
                User: {review.userFirstName} {review.userLastName}
              </p>
              <p>Rating: {review.rating}</p>
              <p>Description: {review.description}</p>
              <p>Date: {formatToMMDDYYYY(review.createdStamp)}</p>
              <hr />
            </div>
          ))}
        </div>
      </div>
    </>
  );
};
