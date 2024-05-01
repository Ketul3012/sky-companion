/**
 * Represents a text input component with primary design and various props.
 * This component is used to render a text input field with customizable label, placeholder, value, and other properties.
 * It supports both top and left label positions, and can be disabled if needed.
 * The onChange event handler allows capturing the input value changes.
 *
 * @param label - The label text for the input field.
 * @param labelPosition - The position of the label, either 'top' or 'left'.
 * @param value - The current value of the input field.
 * @param placeholderText - The placeholder text for the input field.
 * @param divClassName - Additional CSS class name for the container div.
 * @param inputClassName - Additional CSS class name for the input field.
 * @param type - The type of the input field (e.g., 'text', 'password', 'email', etc.).
 * @param onChange - The event handler function called when the input value changes.
 * @param disabled - Specifies whether the input field is disabled or not.
 *
 * @returns The TextInput component.
 */

export interface TextInputProps {
  label?: string;
  labelPosition: 'top' | 'left';
  placeholderText?: string;
  value?: string;
  type?: React.HTMLInputTypeAttribute | undefined;
  divClassName?: string;
  inputClassName?: string;
  onChange?: (value: string) => void;
  disabled?: boolean;
}

export const TextInput = ({
  label,
  labelPosition,
  value,
  placeholderText,
  divClassName = '',
  inputClassName = '',
  type = 'text',
  onChange,
  disabled = false,
  ...props
}: TextInputProps) => {
  return (
    <div
      className={`py-2 flex ${
        labelPosition === 'top' ? 'flex-col' : 'flex-row'
      } ${divClassName}`}
    >
      <h1
        className={`text-primary font-semibold text-lg text-left ${
          labelPosition === 'top' ? 'mb-1' : ''
        }`}
      >
        {label}
      </h1>
      <input
        {...props}
        className={`border-gray-400 border-2 rounded-md h-12 px-2 disabled:border-gray-200 ${inputClassName}`}
        type={type}
        placeholder={placeholderText}
        value={value}
        onChange={(e) => {
          onChange && onChange(e.target.value);
        }}
        disabled={disabled}
      ></input>
    </div>
  );
};
