/**
 * This file contains the definition of the Language interface and the LANGUAGES array.
 * The Language interface is used to declare the type of a language object, which consists of an id and a name.
 * The LANGUAGES array is an array of Language objects, representing different languages supported by the project.
 *
 * This file is important to the project because it provides a centralized location to define and manage the supported languages.
 * It allows other parts of the project to easily access and utilize the language data, ensuring consistency and reusability.
 * By using the Language interface and the LANGUAGES array, the project can handle multilingual functionality and provide language-specific features.
 */

export interface Language {
  id: number;
  name: string;
}

// eslint-disable-next-line react-refresh/only-export-components
export const LANGUAGES: Language[] = [
  {
    id: 1,
    name: 'English',
  },
  {
    id: 2,
    name: 'French',
  },
  {
    id: 3,
    name: 'Hindi',
  },
  {
    id: 4,
    name: 'Gujarati',
  },
  {
    id: 5,
    name: 'Tamil',
  },
];
