/**
 * Represents a component used to display a message when there is no data in a list.
 * This component is important to the project as it provides a visual indication to the user when there is no data available to display.
 * It renders a div element with the "No Data" message using the HeaderText component.
 */

import { HeaderText } from '../components/text/HeaderText';

export const NoData = () => {
  return (
    <div className="w-full h-full flex flex-row justify-center items-center">
      <HeaderText label="No Data" />
    </div>
  );
};
