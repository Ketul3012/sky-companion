/**
 * NewTrip component allows user to create a new trip with details about their travel plans like payment type, price, flight number, source, destination, stops etc.
 * It contains state variables to hold these details entered by user.
 * It has handler methods to update the state when user enters details in the form.
 * It displays the form with text inputs, radio buttons to capture the details.
 * On submit it will send the details entered by user to create a new trip.
 */
import { useState } from 'react';
import { toast } from 'react-toastify';
import addTripImage from '../../assets/addTripImage.jpeg';
import { PrimaryButton } from '../../components/button/PrimaryButton';
import { TextInput } from '../../components/input/TextInput';
import { FullScreenLoading } from '../../components/loading/FullScreenLoading';
import { HeaderText } from '../../components/text/HeaderText';
import { AddTripRequest, StopsRequest, useAddTrip } from './hook/useAddTrip';

const Newtrip = () => {
  const [paymentType, setPaymentType] = useState<'volunteer' | 'paid'>(
    'volunteer',
  );
  const [addTripRequest, setAddTripRequest] = useState<AddTripRequest>({
    arrivalTime: '',
    departureTime: '',
    flightNumber: '',
    from: '',
    price: 0,
    stops: [],
    to: '',
    pnr: '',
  });
  const [numStops, setNumStops] = useState<number>(0);

  const { mutateAsync, isLoading } = useAddTrip();

  /**
   * Handles changes to the payment type (either 'volunteer' or 'paid').
   * @param value A string indicating the selected payment type.
   */
  const handlePaymentTypeChange = (value: 'volunteer' | 'paid') => {
    setPaymentType(value);
    setAddTripRequest({
      ...addTripRequest,
      price: value === 'volunteer' ? 0 : addTripRequest.price,
    });
  };

  /**
   * Handles changes to the price input by the user.
   * Validates and updates the price in the state.
   * @param value A string representing the price entered by the user.
   */
  const handlePriceChange = (value: string) => {
    const price = parseInt(value);
    if (price < 0) {
      toast.error('Price cannot be less than zero');
      return;
    }
    if (price > 1000) {
      toast.error('Price cannot be greater than thousand');
      return;
    }
    setAddTripRequest({
      ...addTripRequest,
      price: price,
    });
  };

  /**
   * Handles changes to the number of stops input by the user.
   * Validates and updates the number of stops in the state.
   * @param value A string representing the number of stops.
   */
  const handleNumStopsChange = (value: string) => {
    const numberOfStops = parseInt(value);
    if (numberOfStops > 3) {
      toast.error('Stops cannot be more than three');
      return;
    }
    setNumStops(numberOfStops);
    setAddTripRequest({
      ...addTripRequest,
      stops: new Array<StopsRequest>(numberOfStops).fill({
        name: '',
        arrivalTime: '',
        departureTime: '',
      }),
    });
  };

  /**
   * Handles changes to the name of a stop.
   * Updates the corresponding stop name in the state.
   * @param value The new name of the stop.
   * @param index The index of the stop being updated.
   */
  const handleStopChange = (value: string, index: number) => {
    const newStops = [...addTripRequest.stops];
    newStops[index] = { ...newStops[index], name: value };
    setAddTripRequest({
      ...addTripRequest,
      stops: newStops,
    });
  };

  /**
   * Handles changes to the departure time of a stop.
   * Updates the corresponding departure time in the state.
   * @param value The new departure time.
   * @param index The index of the stop being updated.
   */
  const handleDepartureTimeChange = (value: string, index: number) => {
    const newStops = [...addTripRequest.stops];
    newStops[index] = { ...newStops[index], departureTime: value };
    setAddTripRequest({
      ...addTripRequest,
      stops: newStops,
    });
  };

  /**
   * Handles changes to the arrival time of a stop.
   * Updates the corresponding arrival time in the state.
   * @param value The new arrival time.
   * @param index The index of the stop being updated.
   */
  const handleArrivalTimeChange = (value: string, index: number) => {
    const newStops = [...addTripRequest.stops];
    newStops[index] = { ...newStops[index], arrivalTime: value };
    setAddTripRequest({
      ...addTripRequest,
      stops: newStops,
    });
  };

  const handleFromChange = (value: string) => {
    setAddTripRequest({
      ...addTripRequest,
      from: value,
    });
  };

  const handleToChange = (value: string) => {
    setAddTripRequest({
      ...addTripRequest,
      to: value,
    });
  };

  const handleFromDepartureTimeChange = (value: string) => {
    setAddTripRequest({
      ...addTripRequest,
      departureTime: value,
    });
  };

  const handleToArrivalTimeChange = (value: string) => {
    setAddTripRequest({
      ...addTripRequest,
      arrivalTime: value,
    });
  };

  const handleFlightDetailsChange = (value: string) => {
    setAddTripRequest({
      ...addTripRequest,
      flightNumber: value,
    });
  };

  /**
   * Validates the add trip request object.
   *
   * @param addTripRequest - The add trip request object to be validated.
   * @returns Returns true if the add trip request is valid, otherwise false.
   *
   * @remarks
   * This function is important to the project as it ensures that the add trip request object
   * contains all the required information before it is submitted. It checks for empty fields
   * such as flight number, PNR number, source place, departure time, destination, arrival time,
   * and stop places (if any). If any of these fields are empty, an error message is displayed
   * using the toast.error() function and false is returned. If all fields are filled, true is returned.
   */
  const validateRequest = (addTripRequest: AddTripRequest): boolean => {
    if (addTripRequest.flightNumber === '') {
      toast.error('Flight number cannot be empty');
      return false;
    }
    if (addTripRequest.pnr === '') {
      toast.error('PNR number cannot be empty');
      return false;
    }
    if (addTripRequest.from === '') {
      toast.error('Source place cannot be empty');
      return false;
    }
    if (addTripRequest.departureTime === '') {
      toast.error('Departure time cannot be empty');
      return false;
    }
    if (addTripRequest.to === '') {
      toast.error('Destination cannot be empty');
      return false;
    }
    if (addTripRequest.arrivalTime === '') {
      toast.error('Arrival time cannot be empty');
      return false;
    }
    if (addTripRequest.stops.length > 0) {
      for (const item of addTripRequest.stops) {
        if (item.name === '') {
          toast.error(
            `Stop place (stop ${
              addTripRequest.stops.indexOf(item) + 1
            }) cannot be empty`,
          );
          return false;
        }
        if (item.arrivalTime === '') {
          toast.error(
            `Arrival time (stop ${
              addTripRequest.stops.indexOf(item) + 1
            }) cannot be empty`,
          );
          return false;
        }
        if (item.departureTime === '') {
          toast.error(
            `Departure time (stop ${
              addTripRequest.stops.indexOf(item) + 1
            }) cannot be empty`,
          );
          return false;
        }
      }
    }
    return true;
  };

  return (
    <div className="h-full w-full">
      <div className="flex flex-row justify-center flex-end p-8">
        <div className="flex flex-col flex-1 justify-left">
          {/* Title - Start */}
          <div className="flex justify-center">
            <HeaderText
              label="Add your trip"
              className="bg-blue-950 w-full rounded-t-[10px] p-[20px] text-white text-center"
            />
          </div>
          {/* Title - End */}
          {/* Main Paragraph Start */}
          <div className="flex justify-center">
            <div className="p-[10px] rounded-b-[10px] bg-footer w-full flex justify-start flex-col items-center">
              <p className="w-[90%] max-w-full text-left text-black text-1xl">
                Sky Companion recognizes the significance of human connection
                and the power of community. Traveling alone can often be a
                daunting and lonely experience, especially for individuals who
                may feel anxious or unfamiliar with the process. As a Sky
                Companion, you can help travelers like this and make the world a
                better place, one journey at a time. <br />
                <br /> The trip you add here will be visible to fellow travelers
                looking for a companion. They will be able to contact you if
                their journey aligns with yours. All you have to do is Indicate
                if you want to volunteer as a companion or will be charging for
                it and provide the flight details.
              </p>
              <br />
              <img
                src={addTripImage}
                alt="Add Trip"
                className="align-right shrink-0 justify-self-end max-w-[90%]"
              />
            </div>
          </div>
          {/* Main Paragraph End */}
          <h2 className="flex w-full text-left justify-left"></h2>
        </div>
        <div className="flex flex-col flex-1 items-center max-w-full ml-4">
          {isLoading ? (
            <FullScreenLoading />
          ) : (
            <>
              <div className="w-full flex justify-center pt-10%">
                <label className="flex items-center mr-4">
                  <input
                    type="radio"
                    value="volunteer"
                    checked={paymentType === 'volunteer'}
                    onChange={() => handlePaymentTypeChange('volunteer')}
                  />
                  <span className="ml-2">I want to be a Volunteer</span>
                </label>
                <label className="flex items-center">
                  <input
                    type="radio"
                    value="paid"
                    checked={paymentType === 'paid'}
                    onChange={() => handlePaymentTypeChange('paid')}
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
                    placeholderText="Price (CAD)"
                    divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                    value={addTripRequest.price.toString()}
                    onChange={handlePriceChange}
                    labelPosition={'top'}
                  />
                </div>
              )}

              <div className="w-full flex justify-center pt-10%">
                <TextInput
                  type="text"
                  label="Flight Number"
                  placeholderText="LH 755"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  value={addTripRequest.flightNumber}
                  onChange={(value) => handleFlightDetailsChange(value)}
                  labelPosition={'top'}
                />
              </div>

              <div className="w-full flex justify-center pt-10%">
                <TextInput
                  type="text"
                  label="PNR"
                  placeholderText="X34RA"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  value={addTripRequest.pnr}
                  onChange={(value) => {
                    setAddTripRequest({
                      ...addTripRequest,
                      pnr: value,
                    });
                  }}
                  labelPosition={'top'}
                />
              </div>
              <div className="w-full flex justify-center pt-10%">
                <TextInput
                  type="text"
                  label="From"
                  placeholderText="Delhi"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  labelPosition={'top'}
                  value={addTripRequest.from}
                  onChange={handleFromChange}
                />
                <TextInput
                  type="datetime-local"
                  label="Departure Time"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  labelPosition={'top'}
                  value={addTripRequest.departureTime}
                  onChange={(value) => handleFromDepartureTimeChange(value)}
                />
              </div>

              <div className="w-full flex justify-center pt-10%">
                <TextInput
                  type="text"
                  label="To"
                  placeholderText="Toronto"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  labelPosition={'top'}
                  value={addTripRequest.to}
                  onChange={handleToChange}
                />

                <TextInput
                  type="datetime-local"
                  label="Arrival Time"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  labelPosition={'top'}
                  value={addTripRequest.arrivalTime}
                  onChange={(value) => handleToArrivalTimeChange(value)}
                />
              </div>

              <div className="w-full flex justify-center pt-10%">
                <TextInput
                  type="number"
                  label="Number of Stops"
                  placeholderText="1"
                  divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mt-4 justify-center"
                  value={numStops.toString()}
                  onChange={handleNumStopsChange}
                  labelPosition={'top'}
                />
              </div>

              {addTripRequest.stops.map((stop, index) => {
                return (
                  <div key={index} className="flex flex-row">
                    <TextInput
                      type="text"
                      label="Place name"
                      placeholderText={`Stop ${index + 1}`}
                      divClassName="border-gray-400 rounded-md p-2 text-center flex-1 mx-2"
                      value={stop.name}
                      onChange={(value) => handleStopChange(value, index)}
                      labelPosition={'top'}
                      key={'Name ' + index}
                    />

                    <TextInput
                      type="datetime-local"
                      label="Arrival Time"
                      divClassName="border-gray-400 rounded-md p-2 text-center flex-1 justify-center"
                      labelPosition={'top'}
                      value={stop.arrivalTime}
                      onChange={(value) =>
                        handleArrivalTimeChange(value, index)
                      }
                      key={'Arrival Time ' + index}
                    />

                    <TextInput
                      type="datetime-local"
                      label="Departure Time"
                      divClassName="border-gray-400 rounded-md p-2 text-center flex-1 justify-center"
                      labelPosition={'top'}
                      value={stop.departureTime}
                      onChange={(value) =>
                        handleDepartureTimeChange(value, index)
                      }
                      key={'Departure Time ' + index}
                    />
                  </div>
                );
              })}
              <PrimaryButton
                className="mt-8 mb-8 pt-10% flex justify-center items-center"
                buttonText="Add Trip"
                onClick={async () => {
                  if (validateRequest(addTripRequest)) {
                    const response = await mutateAsync(addTripRequest);
                    if (response.statusCode === 200) {
                      toast.success(response.statusMessage);
                    } else {
                      toast.error(response.statusMessage);
                    }
                  }
                }}
              />
            </>
          )}
        </div>
      </div>
    </div>
  );
};

export default Newtrip;
