/**
 * This file contains the implementation of the "List Trips" page component.
 * The "List Trips" page allows users to view and search for available trips.
 *
 * The component uses the `useListTrips` hook to fetch the necessary data and manage the state.
 *
 * @returns JSX.Element representing the "List Trips" page component.
 */

import { useEffect } from 'react';
import { useLocation, useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { PrimaryButton } from '../../components/button/PrimaryButton';
import { TextInput } from '../../components/input/TextInput';
import { FullScreenLoading } from '../../components/loading/FullScreenLoading';
import { HeaderText } from '../../components/text/HeaderText';
import { formatToMMDDYYYY } from '../../utils/date-utils';
import { LANGUAGES, Language } from '../../utils/languages';
import { NoData } from '../../views/NoData';
import { useLoginStore } from '../login/store';
import { useDeleteTrip } from './hook/useDeleteTrip';
import {
  ListTripItem,
  ListTripsResponse,
  useListTrips,
} from './hook/useListTrips';
import { useSaveSearchAlerts } from './hook/useSaveSearchAlert';

export const ListTrips = () => {
  const {
    data,
    isLoading,
    isRefetching,
    to,
    setTo,
    from,
    setFrom,
    date,
    setDate,
    user,
    setUser,
    companionCity,
    setCompanionCity,
    languageIds,
    setLanguageIds,
    maximumCost,
    setMaximumCost,
  } = useListTrips();

  const { isLoading: saveSearchAlertsLoading, mutateAsync } =
    useSaveSearchAlerts();

  const { mutateAsync: deleteTrip } = useDeleteTrip();

  const { loginResponse } = useLoginStore();

  const location = useLocation();

  const navigate = useNavigate();

  /**
   * this method url parameter and set it in state
   */
  useEffect(() => {
    const urlSearchParams = new URLSearchParams(
      location.search.replace('?token=', ''),
    );
    setFrom(urlSearchParams.get('from') ?? undefined);
    setTo(urlSearchParams.get('to') ?? undefined);
    setUser(urlSearchParams.get('user') ?? undefined);
    setCompanionCity(urlSearchParams.get('companion-city') ?? undefined);
    setMaximumCost(parseFloat(urlSearchParams.get('maximum-cost') ?? '500.0'));
    setDate(urlSearchParams.get('date') ?? '');
    if (urlSearchParams.get('language-ids') !== null) {
      const languageIdsString = urlSearchParams.get('language-ids') ?? '';
      setLanguageIds(
        languageIdsString.split(',').map((item) => parseInt(item)),
      );
    }
  }, [
    location,
    setCompanionCity,
    setDate,
    setFrom,
    setLanguageIds,
    setMaximumCost,
    setTo,
    setUser,
  ]);

  /**
   * Search filter component
   * @returns
   */
  const _renderSearch = () => {
    return (
      <div className="flex flex-row w-full mb-2 gap-16">
        <TextInput
          label="From"
          labelPosition="top"
          placeholderText="ex: Ahmadabad"
          value={from}
          onChange={(value) => {
            setFrom(value);
          }}
          divClassName="flex-1"
        />
        <TextInput
          label="To"
          labelPosition="top"
          placeholderText="ex: Halifax"
          value={to}
          onChange={(value) => {
            setTo(value);
          }}
          divClassName="flex-1"
        />
        <TextInput
          label="Date"
          labelPosition="top"
          type="date"
          value={date}
          onChange={(value) => {
            setDate(value);
          }}
          divClassName="flex-1"
        />
        <TextInput
          label="User"
          labelPosition="top"
          placeholderText="ex: Alias"
          value={user}
          onChange={(value) => {
            setUser(value);
          }}
          divClassName="flex-1"
        />
        <TextInput
          label="Companion City"
          labelPosition="top"
          placeholderText="ex: New York"
          value={companionCity}
          onChange={(value) => {
            setCompanionCity(value);
          }}
          divClassName="flex-1"
        />
      </div>
    );
  };

  /**
   * this function returns a checkbox with language given to it and manage callback
   * @param item
   * @param index
   * @returns
   */
  const _renderLanguageCheckbox = (item: Language, index: number) => {
    return (
      <label key={index} className="block">
        <input
          type="checkbox"
          value={item.id}
          checked={languageIds?.includes(item.id)}
          onChange={() => handleLanguageCheckboxChange(item.id)}
          className="mr-2"
        />
        {item.name}
      </label>
    );
  };

  /**
   * this method handle checkbox change callback for language
   * @param languageId
   */
  const handleLanguageCheckboxChange = (languageId: number) => {
    if (languageIds.includes(languageId)) {
      setLanguageIds(languageIds.filter((id) => id !== languageId));
    } else {
      setLanguageIds([...(languageIds ?? []), languageId]);
    }
  };

  /**
   * this method handle change callback for cost range
   * @param minimumCost
   * @param maximumCost
   */
  const handleCostRangeChange = (maximumCost: number) => {
    setMaximumCost(maximumCost);
  };

  /**
   * render filters like languages, cost range,
   * @returns
   */
  const _renderFilters = () => {
    return (
      <div className="w-[150px] mt-4 p-4">
        <PrimaryButton
          buttonText="Add trip"
          onClick={() => {
            navigate('/new-trip');
          }}
        />
        <div className="w-full">
          <HeaderText label="Languages" />
          <div className="mt-2">
            {LANGUAGES.map((item, index) =>
              _renderLanguageCheckbox(item, index),
            )}
          </div>
        </div>
        <div className="w-full mt-4">
          <HeaderText label="Cost" />
          <div className="mt-2">
            <input
              type="range"
              min="0"
              max="1000"
              step="10"
              value={maximumCost}
              onChange={(e) =>
                handleCostRangeChange(parseFloat(e.target.value))
              }
              className="w-full"
            />
            <p>Maximum Cost : {maximumCost} CAD</p>
          </div>
        </div>
        <div className="w-full mt-4">
          <h1 className="text-[12px]">
            Fill the desired search and filters then click save to get notified
            when similar posts are available.
          </h1>
          {saveSearchAlertsLoading ? (
            <FullScreenLoading />
          ) : (
            <PrimaryButton
              buttonText="Save Search Alert"
              className="mt-2"
              onClick={async () => {
                const response = await mutateAsync({
                  companionCity: companionCity,
                  date: date,
                  from: from,
                  to: to,
                  maximumCost: maximumCost,
                  user: user,
                  languageIds: languageIds,
                });
                if (response.statusCode === 200) {
                  toast.success(response.data);
                } else {
                  toast.error(response.statusMessage);
                }
              }}
            />
          )}
        </div>
      </div>
    );
  };

  /**
   * Trip card component with key and item {@link ListTripItem}
   * @param key
   * @param item
   * @returns
   */
  const _renderTripCard = (key: number, item: ListTripItem) => {
    return (
      <div
        className="flex flex-col min-w-[calc(100%/5)] grid-rows-auto bg-primary rounded-lg px-6 py-4 z-5 justify-between shadow-md shadow-blue-400"
        key={key}
      >
        <div>
          <h1 className="text-primary">
            Route: {item.source}-{item.destination}
          </h1>
          <h1 className="text-primary text-ellipsis">
            Stops:{' '}
            {item.stops
              ? item.stops.flatMap((item) => item.name).join('-')
              : 'No stops'}
          </h1>
          <h1 className="text-primary">
            Date: {formatToMMDDYYYY(item.departure)}
          </h1>
          <h1 className="text-primary">
            Person: {item.user.firstName + ' ' + item.user.lastName}
          </h1>
          <h1 className="text-primary">
            Price: {item.price === 0 ? 'Free' : item.price + ' CAD'}
          </h1>
          <h1 className="text-primary">
            Languages:{' '}
            {item.user.languages !== undefined
              ? item.user.languages.flatMap((item) => item.language).join(',')
              : '-'}
          </h1>
          <h1 className="text-primary">
            City/State:{' '}
            {item.user.address?.city !== undefined
              ? `${item.user.address.city},${item.user.address.province}`
              : '-'}
          </h1>
        </div>
        <div className="flex flex-row justify-between mt-2 gap-2">
          {loginResponse?.id === item.userId ? null : (
            <PrimaryButton
              className="flex-1"
              buttonText="Connect"
              onClick={() => {
                navigate('/user-details/' + item.userId.toString());
              }}
            />
          )}
          {loginResponse?.id === item.userId ? (
            <PrimaryButton
              buttonText="View"
              className="flex-1"
              onClick={() => {
                navigate('/view-edit-trip/' + item.id);
              }}
            />
          ) : null}
          {loginResponse?.id === item.userId ? (
            <PrimaryButton
              buttonText="Delete"
              className="flex-1"
              onClick={async () => {
                const response = await deleteTrip(item.id);
                if (response.statusCode === 200) {
                  toast.success(response.statusMessage);
                } else {
                  toast.error(response.statusMessage);
                }
              }}
            />
          ) : null}
        </div>
      </div>
    );
  };

  /**
   * Trip list component to render trip listing
   * @param data
   * @returns
   */
  const _renderTripList = (data: ListTripsResponse) => {
    return (
      <div className="w-full grid grid-cols-4 grid-flow-row gap-x-8 gap-y-4 mt-3">
        {data.trips.map((item, index) => _renderTripCard(index, item))}
      </div>
    );
  };

  return (
    <div className="bg-white w-full p-2 mb-4 flex flex-col">
      <div className="flex flex-row">
        <div className="flex-[1]">{_renderFilters()}</div>
        <div className="flex-[5]">
          {_renderSearch()}
          {isLoading || isRefetching || data === undefined ? (
            <FullScreenLoading />
          ) : (
            <> {data.count === 0 ? <NoData /> : _renderTripList(data)}</>
          )}
        </div>
      </div>
    </div>
  );
};
