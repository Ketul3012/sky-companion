/**
 * Creates a login slice for the login store.
 * The login slice contains the login response and a setter function to update the login response.
 *
 * @param set - A function provided by the Zustand library to update the state.
 * @returns The login slice object.
 */

import { StateCreator } from 'zustand';
import { LoginResponse } from '../hook/useLogin';

export interface LoginSlice {
  loginResponse?: LoginResponse;
  setLoginResponse: (response?: LoginResponse) => void;
}

export const createLoginSlice: StateCreator<LoginSlice> = (set) => ({
  loginResponse: undefined,
  setLoginResponse: (response?: LoginResponse) => {
    set((state) => ({ ...state, loginResponse: response }));
  },
});
