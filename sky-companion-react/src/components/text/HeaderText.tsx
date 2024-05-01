/**
 * Header Text component used to display the page header name.
 *
 * This component renders an <h1> element with the provided label as its content. It also accepts an optional className prop to apply additional styling.
 *
 * @param label - The text to be displayed as the header.
 * @param className - An optional CSS class name to apply to the <h1> element.
 * @returns The rendered HeaderText component.
 * @remarks
 * The HeaderText component is an important part of the project as it provides a consistent and reusable way to display page headers throughout the application.
 */

export const HeaderText = ({
  label,
  className,
}: {
  label: string;
  className?: string;
}) => {
  return <h1 className={`text-2xl text-primary ${className}`}>{label}</h1>;
};
