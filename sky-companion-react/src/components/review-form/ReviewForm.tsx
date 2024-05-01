/**
 * Review form component.
 *
 * This component represents a form for users to submit reviews for a companion.
 * It allows users to provide a rating and a review description, and saves the review using the `useSaveReviews` hook.
 * The saved review is associated with a specific companion identified by the `companionId` prop.
 *
 * This component is important to the project as it enables users to provide feedback and reviews for companions,
 * which helps in improving the overall user experience and helps other users in making informed decisions.
 */

import { useState } from 'react';
import StarRatings from 'react-star-ratings';
import { toast } from 'react-toastify';
import {
  SaveReviewsResponse,
  useSaveReviews,
} from '../../pages/user-details/hook/useSaveReviews';
import { PrimaryButton } from '../button/PrimaryButton';
import { TextInput } from '../input/TextInput';
/**
 * Review form component.
 */
export const ReviewForm = ({ companionId }: { companionId: number }) => {
  const [rating, setRating] = useState<number>(0);
  const [review, setReview] = useState<string>('');
  const { mutateAsync } = useSaveReviews();

  const changeRating = (newRating: number) => {
    setRating(newRating);
  };

  const submitReview = async () => {
    if (rating === 0) {
      toast.error('Please provide rating for your review');
      return;
    }
    if (review === '') {
      toast.error('Please provide review description');
      return;
    }
    const response: SaveReviewsResponse = await mutateAsync({
      companionId: companionId,
      description: review,
      rating: rating,
    });

    if (response !== undefined) {
      setRating(0);
      setReview('');
      toast.success('Review saved successfully');
    }
  };

  return (
    <>
      <div className="my-5 text-center w-[60%]">
        <div>
          <p className="text-xl">Give Review</p>
          <StarRatings
            rating={rating}
            starRatedColor="blue"
            starHoverColor="blue"
            changeRating={changeRating}
            numberOfStars={5}
            starDimension="30"
            starSpacing="5px"
            name="rating"
          />
          <TextInput
            labelPosition="top"
            placeholderText="Description"
            value={review}
            onChange={(value) => setReview(value)}
          />
          <PrimaryButton
            buttonText="Submit"
            onClick={async () => {
              await submitReview();
            }}
          />
        </div>
      </div>
    </>
  );
};
