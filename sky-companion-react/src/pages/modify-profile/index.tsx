/**
 * Represents the ModifyProfile component.
 * This component allows users to modify their profile information.
 * The ModifyProfile component in ModifyProfile.tsx is a crucial part of the user interface,
 * allowing users to edit and update their personal profile information. This component is
 * integral to maintaining user engagement and ensuring that the user data is up-to-date
 * and accurate.
 */

// ModifyProfile.tsx
import { useEffect, useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { toast } from 'react-toastify';
import { PrimaryButton } from '../../components/button/PrimaryButton';
import { TextInput } from '../../components/input/TextInput'; // Replace with your TextInput component
import { FullScreenLoading } from '../../components/loading/FullScreenLoading'; // Replace with your FullScreenLoading component
import { HeaderText } from '../../components/text/HeaderText'; // Replace with your HeaderText component
import { formatToYYYYMMDDWithoutTimeStamp } from '../../utils/date-utils';
import { LANGUAGES, Language } from '../../utils/languages';
import { useLoginStore } from '../login/store';
import { useGetUserDetails } from '../user-details/hook/useGetUserDetails';
import { useModifyProfile } from './hook/useModifyProfile';

export const ModifyProfile = () => {
  const { loginResponse } = useLoginStore();
  const { data } = useGetUserDetails(
    loginResponse?.id || 0,
    !!loginResponse?.id,
  );
  const navigate = useNavigate();
  const [firstname, setFirstName] = useState<string>('');
  const [lastname, setLastName] = useState<string>('');
  const [email, setEmail] = useState<string>('');
  const [addressLine1, setAddressLine1] = useState<string>('');
  const [addressLine2, setAddressLine2] = useState<string>('');
  const [city, setCity] = useState<string>('');
  const [province, setProvince] = useState<string>('');
  const [mobileNumber, setMobileNumber] = useState<string>('');
  const [country, setCountry] = useState<string>('');
  const [dob, setDob] = useState<string>('');
  const [postalCode, setPostalCode] = useState<string>('');
  const { mutateAsync: modifyProfile, isLoading } = useModifyProfile();
  const [languageIds, setLanguageIds] = useState<number[]>([]);

  useEffect(() => {
    if (data) {
      setFirstName(data.firstName || '');
      setLastName(data.lastName || '');
      setAddressLine1(data?.address?.addressLine1 || '');
      setAddressLine2(data?.address?.addressLine2 || '');
      setCity(data?.address?.city || '');
      setProvince(data?.address?.province || '');
      setMobileNumber(data?.mobileNumber || '');
      setCountry(data?.address?.country || '');
      setDob(formatToYYYYMMDDWithoutTimeStamp(data?.dob || ''));
      setEmail(data?.email || '');
      setPostalCode(data?.address?.postalCode || '');
      setLanguageIds(data?.languages?.map((item) => item.id));
    }
  }, [data]);

  const handleSaveChanges = async () => {
    const updateData = {
      id: loginResponse?.id || 0,
      firstname,
      lastname,
      addressLine1,
      addressLine2,
      city,
      province,
      mobileNumber,
      country,
      dob,
      postalCode,
      languages: languageIds,
    };

    const response = await modifyProfile(updateData);
    if (response.statusCode === 200) {
      toast.success('Successfully updated the profile.');
      navigate('/home', { replace: true });
    } else {
      toast.error('Error Updating the Profile.');
    }
  };

  if (isLoading) {
    return (
      <FullScreenLoading text="Please wait while we fetch your details." />
    );
  }
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
   * this function returns a checkbox with language given to it and manage callback
   * @param item
   * @param index
   * @returns
   */
  const _renderLanguageCheckbox = (item: Language, index: number) => {
    return (
      <label
        key={index}
        className="flex flex-row items-center justify-center mr-4"
      >
        <input
          type="checkbox"
          value={item.id}
          checked={languageIds?.includes(item.id)}
          onChange={() => handleLanguageCheckboxChange(item.id)}
          className="mr-1"
        />
        {item.name}
      </label>
    );
  };

  return (
    <div className="flex flex-col p-4 ml-6 w-[50%]">
      <HeaderText label="My Account" className="font-semibold" />
      <hr className="my-2" />
      <HeaderText label="Personal Details" className="mt-4" />
      <h1 className="mb-2">
        You can update your personal details here, some of which will be visible
        to other users.
      </h1>
      <hr className="my-2" />

      <TextInput
        labelPosition="top"
        placeholderText="First Name"
        value={firstname || ''}
        onChange={(value) => setFirstName(value)}
      />
      <TextInput
        labelPosition="top"
        placeholderText="Last Name"
        value={lastname || ''}
        onChange={(value) => setLastName(value)}
      />
      <TextInput
        labelPosition="top"
        placeholderText="Email"
        value={email || ''}
        disabled
      />
      <TextInput
        labelPosition="top"
        placeholderText="Mobile Number"
        value={mobileNumber || ''}
        onChange={(value) => setMobileNumber(value)}
      />
      <TextInput
        type="date"
        labelPosition="top"
        placeholderText="Date of Birth"
        value={dob || ''}
        onChange={(value) => setDob(value)}
      />
      <hr className="my-2" />

      <HeaderText label="Location Details" className="mt-4" />
      <h1 className="mb-2">
        You can update your location details here, which will help other users
        find you by similar locations.
      </h1>
      <hr className="my-2" />

      <TextInput
        labelPosition="top"
        placeholderText="Address Line 1"
        value={addressLine1 || ''}
        onChange={(value) => setAddressLine1(value)}
      />

      <TextInput
        labelPosition="top"
        placeholderText="Address Line 2"
        value={addressLine2 || ''}
        onChange={(value) => setAddressLine2(value)}
      />

      <TextInput
        labelPosition="top"
        placeholderText="City"
        value={city || ''}
        onChange={(value) => setCity(value)}
      />

      <TextInput
        labelPosition="top"
        placeholderText="Province"
        value={province || ''}
        onChange={(value) => setProvince(value)}
      />
      <TextInput
        labelPosition="top"
        placeholderText="Postal Code"
        value={postalCode || ''}
        onChange={(value) => setPostalCode(value)}
      />

      <TextInput
        labelPosition="top"
        placeholderText="Country"
        value={country || ''}
        onChange={(value) => setCountry(value)}
      />

      <hr className="my-2" />
      <HeaderText label="Known languages" className="mt-4" />
      <h1 className="mb-2">
        You can update languages, which you are comfortable understanding and
        speaking, so users speaking similar languages connect with you.
      </h1>
      <hr className="my-2" />
      <div className="mt-2 flex flex-row mb-4">
        {LANGUAGES.map((item, index) => _renderLanguageCheckbox(item, index))}
      </div>
      <hr className="my-2" />

      <PrimaryButton
        className="my-3 py-2 px-8 rounded-lg text-center justify-center"
        buttonText="Save Changes"
        onClick={handleSaveChanges}
      />
    </div>
  );
};
