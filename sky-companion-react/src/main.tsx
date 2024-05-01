/**
 * This file serves as the main entry point of the React application.
 * It imports necessary dependencies and sets up the React Query client.
 * The React Query client is wrapped with the QueryClientProvider to make it available throughout the application.
 * The React Router is used to handle routing.
 * The <App /> component is rendered inside the Router, which serves as the root component of the application.
 * This file is important as it initializes the necessary dependencies and sets up the application's structure.
 */

import { QueryClient, QueryClientProvider } from '@tanstack/react-query';
import React from 'react';
import ReactDOM from 'react-dom/client';
import { BrowserRouter as Router } from 'react-router-dom';
import { App } from './App';
import './index.css';

/**
 * react-query client to setup retry and it's delay if query call fails
 */
export const queryClient = new QueryClient({
  defaultOptions: {
    mutations: {
      retry: (failureCount, error: unknown) =>
        failureCount < 2 && error !== undefined, // retry on 5xx errors for 1 times
      retryDelay: (attemptIndex) => Math.min(1000 * 1 ** attemptIndex, 20000), // retry after 1s, 3s, 9s, 27s, 81s, 243s, 729s, 2187s, 6561s, 20000s
    },
    queries: {
      retry: (failureCount, error: unknown) =>
        failureCount < 2 && error !== undefined, // retry on 5xx errors for 1 times
      retryDelay: (attemptIndex) => Math.min(1000 * 1 ** attemptIndex, 20000), // retry after 1s, 3s, 9s, 27s, 81s, 243s, 729s, 2187s, 6561s, 20000s
      refetchOnWindowFocus: false,
    },
  },
});

/**
 * main entry point of react api, here we have wrapped react-query client so that it can be available throughout application
 */
ReactDOM.createRoot(document.getElementById('root')!).render(
  <React.StrictMode>
    <QueryClientProvider client={queryClient}>
      <Router basename="/">
        <App />
      </Router>
    </QueryClientProvider>
  </React.StrictMode>,
);
