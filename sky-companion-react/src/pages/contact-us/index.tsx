/**
 * Renders the Contact Us page.
 *
 * This component displays the contact information and location of the Sky Companion team.
 * It includes a banner image, a title, a main paragraph with contact details, and a Google Maps embed.
 *
 * @returns The Contact Us page component.
 */

export const ContactUs = () => {
  return (
    <div className="flex flex-col w-ful">
      <div className="w-[60%] flex flex-col self-center my-6">
        <div className="bg-blue-950 rounded-t-[10px] w-full flex justify-center p-[10px] font-bold">
          <h1 className="text-white">Contact Us</h1>
        </div>
        <div className="bg-footer rounded-b-[10px] p-[10px] flex flex-col justify-center items-center">
          <p className="w-full text-center text-black text-1xl font-semibold p-4">
            Sky Companion team can be reached at hello@skycompanion.ca <br />
            or at 6050 University Ave, Halifax, NS B3H 1W5
          </p>
          <br />
          <iframe
            src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2839.0210622941454!2d-63.58997062326576!3d44.637486488216545!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x4b5a223ad1a74675%3A0xda7cf9c26983ebf9!2sGoldberg%20Computer%20Science%20Building!5e0!3m2!1sen!2sca!4v1698106359667!5m2!1sen!2sca"
            className="w-full h-[450px]"
          ></iframe>
        </div>
      </div>
    </div>
  );
};
