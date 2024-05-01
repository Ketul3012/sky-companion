/**
 * Renders a full screen loading component with a white background and a spinner.
 * This component is used to indicate that the application is loading or performing a task.
 * It is important to this project as it provides a visual feedback to the user during loading or processing operations.
 *
 * @param {object} props - The component props.
 * @param {string} [props.text] - The optional text to display below the spinner.
 * @returns {JSX.Element} The rendered FullScreenLoading component.
 */
import { Spinner } from '../spinner/Spinner';

export interface FullScreenLoadingProps {
  text?: string;
}

export const FullScreenLoading = ({ text }: FullScreenLoadingProps) => {
  return (
    <div className="w-full h-full bg-white flex flex-col items-center justify-center">
      <Spinner spinnerVariant="border-footer" />
      <div className="loader-message">{text}</div>
    </div>
  );
};
