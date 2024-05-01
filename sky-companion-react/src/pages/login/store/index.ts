/**
 * This file exports the `useLoginStore` function, which creates and configures the login store for the application.
 * The login store is responsible for managing the state related to the login functionality.
 * It uses the `create` function from the `zustand` library to create a store with a defined state and actions.
 * The store is also persisted using the `persist` function from the `zustand-persist` library, which allows the state to be saved and retrieved from local storage.
 * This ensures that the login state is preserved even if the user refreshes the page or navigates away from the login screen.
 * The `useLoginStore` function returns the configured login store, which can be used by other components to access and update the login state.
 *
 * @returns The configured login store.
 */

import { create } from 'zustand';
import { createJSONStorage, persist } from 'zustand/middleware';
import { LoginSlice, createLoginSlice } from './slice';

type LoginStore = LoginSlice;

export const useLoginStore = create(
  persist<LoginStore>(
    (set, get, api) => ({
      ...createLoginSlice(set, get, api),
    }),
    {
      name: 'login-state',
      storage: createJSONStorage(() => localStorage),
    },
  ),
);
