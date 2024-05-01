/**
 * Represents a Spinner component that renders a loading spinner.
 * This component is used to indicate that a process is in progress and provides visual feedback to the user.
 *
 * @param {SpinnerProps} props - The props for the Spinner component.
 * @param {string} props.label - The label to be displayed alongside the spinner.
 * @param {'bottom' | 'top'} props.labelPosition - The position of the label relative to the spinner.
 * @param {'border-black' | 'border-primary' | 'border-white' | 'border-footer'} props.spinnerVariant - The variant of the spinner to be displayed.
 *
 * @returns {JSX.Element} The rendered Spinner component.
 */

export interface SpinnerProps {
  label?: string;
  labelPosition?: 'bottom' | 'top';

  spinnerVariant?:
    | 'border-black'
    | 'border-primary'
    | 'border-white'
    | 'border-footer';
}

/**
 * Spinner component to render a loading
 * @param param0
 * @returns
 */
export const Spinner = ({ spinnerVariant = 'border-black' }: SpinnerProps) => {
  return (
    <div
      className={`inline-block h-8 w-8 animate-spin rounded-full border-4 border-r-transparent align-[-0.125em] ${spinnerVariant} z-50`}
    ></div>
  );
};
