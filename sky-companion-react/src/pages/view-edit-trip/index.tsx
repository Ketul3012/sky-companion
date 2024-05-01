/**
 * This code is a React component called ViewEditTrip.
 * It is responsible for rendering a form that allows users to view and edit trip details.
 * The component renders a form that displays trip information, including the trip title, 
 * a main paragraph describing the purpose of the trip, an image, and various input fields 
 * for editing trip details. The form includes radio buttons to select the payment type, 
 * a text input field for entering the price, and text input fields for entering the source, 
 * destination, departure time, and arrival time of the trip. It also dynamically renders 
 * additional text input fields for each stop in the trip.

 * The component includes a button labeled "Edit" that toggles the disabled state of the 
 * input fields, allowing the user to edit the trip details. When the user clicks the 
 * "Save Changes" button, the component calls the mutateAsync function to update the trip
 * details with the new price. If the update is successful, a success toast notification 
 * is displayed; otherwise, an error toast notification is displayed.
 */

import { useState } from 'react';
import { useParams } from 'react-router-dom';
import { toast } from 'react-toastify';
import addTripImage from '../../assets/addTripImage.jpeg';
import { PrimaryButton } from '../../components/button/PrimaryButton';
import { TextInput } from '../../components/input/TextInput';
import { FullScreenLoading } from '../../components/loading/FullScreenLoading';
import { HeaderText } from '../../components/text/HeaderText';
import { formatToYYYYMMDD } from '../../utils/date-utils';
import { useEditTrip } from './hook/useEditTrip';
import { useGetTrip } from './hook/useGetTrip';

/**
 * View and edit trip component to manage trip details
 */
const ViewEditTrip = () => {
  const { id } = useParams();

  const { data, isLoading } = useGetTrip(parseInt(id || ''), !!id);

  const { mutateAsync, isLoading: editLoading } = useEditTrip();

  const [paymentType, setPaymentType] = useState<'volunteer' | 'paid'>(
    (data?.price || 0) === 0 ? 'volunteer' : 'paid',
  );
  const [price, setPrice] = useState<number>(data?.price || 0);

  const [disabled, setDisabled] = useState(true);

  const handlePaymentTypeChange = (value: 'volunteer' | 'paid') => {
    setPaymentType(value);
    if (value === 'volunteer') {
      setPrice(0);
    }
  };

  /**
   * Handles the change of price value.
   *
   * @param value - The new price value as a string.
   *
   * @returns void
   */
  const handlePriceChange = (value: string) => {
    const tempPrice = parseInt(value);
    if (tempPrice < 0) {
      toast.error('Price cannot be less than zero');
      return;
    }
    if (tempPrice > 1000) {
      toast.error('Price cannot be greater than thousand');
      return;
    }
    setPrice(tempPrice);
  };

  return (
    <div className="h-full w-full">
      <div className="flex flex-row justify-center flex-end p-8">
        <div className="flex flex-col flex-1 justify-left">
          {/* Title - Start */}
          <div className="flex justify-center">
            <HeaderText
              label="View and Edit your trip"
              className="bg-blue-950 w-full rounded-t-[10px] p-[20px] text-white text-center"
            />
          </div>
          {/* Title - End */}
          {/* Main Paragraph Start */}
          <div className="flex justify-center">
            <div className="p-[10px] rounded-b-[10px] bg-footer w-full flex justify-start flex-col items-center">
              <p className="w-[90%] max-w-full text-left  text-black text-1xl">
                Sky Companion recognizes the significance of human connection
                and the power of community. Traveling alone can often be a
                daunting and lonely experience, especially for individuals who
                may feel anxious or unfamiliar with the process. As a Sky
                Companion, you can help travelers like this and make the world a
                better place, one journey at a time. <br />
                <br /> The trip you view and update here will be visible to
                fellow travelers looking for a companion. They will be able to
                contact you if their journey aligns with yours. All you have to
                do is Indicate if you want to volunteer as a companion or will
                be charging for it and provide the flight details.
              </p>{' '}
              <br />
              <img
                src={addTripImage}
                alt="View Edit Trip"
                className="align-right shrink-0 justify-self-end max-w-[90%]"
              />
            </div>
          </div>
          {/* Main Paragraph End */}
          <h2 className="flex w-full text-left justify-left"></h2>
        </div>
        <div className="flex flex-col flex-1 items-center max-w-full ml-4">
          {isLoading || editLoading ? (
            <FullScreenLoading />
          ) : (
            <>
              <div className="flex flex-row self-end justify-end w-full">
                <PrimaryButton
                  buttonText="Edit"
                  onClick={() => {
                    setDisabled(!disabled);
                  }}
                />
              </div>
              <div className="w-full flex justify-center pt-10%">
                <label className="flex items-center mr-4">
                  <input
                    type="radio"
                    value="volunteer"
                    checked={paymentType === 'volunteer'}
                    onChange={() => handlePaymentTypeChange('volunteer')}
                    disabled={disabled}
                  />
                  <span className="ml-2">I want to be a Volunteer</span>
                </label>
                <label className="flex items-center">
                  <input
                    type="radio"
                    value="paid"
                    checked={paymentType === 'paid'}
                    onChange={() => handlePaymentTypeChange('paid')}
                    disabled={disabled}
                  />
                  <span className="ml-2">
                    I want to charge for companionship
                  </span>
                </label>
              </div>

              {paymentType === 'paid' && (
                <div className="w-full flex justify-center pt-10%">
                  <TextInput
                    type="number"
                    label="How much would you like to charge?"
                    placeholderText="Price"
                    divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                    value={price.toString()}
                    onChange={handlePriceChange}
                    labelPosition={'top'}
                  />
                </div>
              )}

              <div className="w-full flex justify-center pt-10%">
                <TextInput
                  type="text"
                  label="From"
                  placeholderText="Delhi"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  labelPosition={'top'}
                  value={data?.source}
                  disabled
                />
                <TextInput
                  type="datetime-local"
                  label="Departure Time"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  labelPosition={'top'}
                  value={formatToYYYYMMDD(data?.departure || '')}
                  disabled
                />
              </div>

              <div className="w-full flex justify-center pt-10%">
                <TextInput
                  type="text"
                  label="To"
                  placeholderText="Toronto"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  labelPosition={'top'}
                  value={data?.destination}
                  disabled
                />

                <TextInput
                  type="datetime-local"
                  label="Arrival Time"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  labelPosition={'top'}
                  value={formatToYYYYMMDD(data?.arrival || '')}
                  disabled
                />
              </div>
              {data?.stops?.map((stop, index) => {
                return (
                  <div key={index} className="flex flex-row">
                    <TextInput
                      type="text"
                      label="Place name"
                      placeholderText={`Stop ${index + 1}`}
                      divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mx-2"
                      value={stop.name}
                      labelPosition={'top'}
                      key={'Name ' + index}
                      disabled
                    />

                    <TextInput
                      type="datetime-local"
                      label="Arrival Time"
                      divClassName="border-gray-400 rounded-md p-2 text-center flex-1 justify-center"
                      labelPosition={'top'}
                      value={formatToYYYYMMDD(stop?.arrival)}
                      key={'Arrival Time ' + index}
                      disabled
                    />

                    <TextInput
                      type="datetime-local"
                      label="Departure Time"
                      divClassName="border-gray-400 rounded-md p-2 text-center flex-1 justify-center"
                      labelPosition={'top'}
                      value={formatToYYYYMMDD(stop.departure)}
                      key={'Departure Time ' + index}
                      disabled
                    />
                  </div>
                );
              })}
              <PrimaryButton
                className="mt-8 mb-8 pt-10% flex justify-center items-center"
                buttonText="Save Changes"
                onClick={async () => {
                  if (data) {
                    const response = await mutateAsync({
                      id: data?.id,
                      price: price,
                    });
                    if (response.statusCode === 200) {
                      toast.success(response.statusMessage);
                    } else {
                      toast.error(response.statusMessage);
                    }
                  }
                }}
                disabled={disabled}
              />
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default ViewEditTrip;
