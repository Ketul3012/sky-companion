/**
 * This file is the configuration file for Tailwind CSS.
 * It defines the theme, colors, spacing, font sizes, font weights, and other customizations for the project.
 * The `content` property specifies the files that Tailwind CSS should scan for classes to generate CSS for.
 * This file is important because it allows developers to customize the appearance of the project using Tailwind CSS utility classes.
 * By modifying the values in this file, developers can easily change the styling of the project without writing custom CSS.
 */

/** @type {import('tailwindcss').Config} */
export default {
  content: ['./index.html', './src/**/*.{js,ts,jsx,tsx}'],
  theme: {
    extend: {
      colors: {
        transparent: 'transparent',
        primary: '#c3e1f6',
        header: '#E6E6E6',
        footer: '#c3e1f6',
        buttonColor: '#4063ff',
      },
      textColor: {
        primary: '#000000',
        secondary: '#FFFFFF',
      },
      borderRadius: {
        none: '0',
        sm: '.125rem',
        DEFAULT: '.25rem',
        lg: '.5rem',
        full: '9999px',
      },
      fontSize: {
        sm: '14px',
        header: '30px',
        base: '16px',
        lg: '20px',
        xlg: '32px',
      },
      spacing: {
        sm: '8px',
        md: '12px',
        lg: '16px',
        xl: '24px',
      },
      fontWeights: {
        thin: 200,
        light: 300,
        medium: 500,
        bold: 700,
        extrabold: 800,
        black: 900,
      },
    },
  },
  plugins: [],
};
