/**
 * Represents a primary button component with a specific design.
 * This component is used to render a button with primary styling in the project.
 * It accepts various props to customize the button's appearance and behavior.
 *
 * @remarks
 * This component is an essential part of the project's button component library.
 * It provides a consistent and visually appealing primary button style across the application.
 */

export interface PrimaryButtonProps {
  buttonText: string;
  onClick: () => void;
  disabled?: boolean;
  className?: string;
  rounded?: string;
}

/**
 * Button with Primary design
 * @param param0
 * @returns
 */
export const PrimaryButton = ({
  buttonText,
  onClick,
  className,
  disabled = false,
  rounded = 'rounded-md',
}: PrimaryButtonProps) => {
  return (
    <button
      className={`w-fit px-4 h-12 text-1xl hover:bg-blue-950 z-5 shadow-md text-white bg-blue-500 disabled:bg-blue-400 ${className} ${rounded}`}
      onClick={() => {
        onClick();
      }}
      disabled={disabled}
    >
      {buttonText}
    </button>
  );
};
