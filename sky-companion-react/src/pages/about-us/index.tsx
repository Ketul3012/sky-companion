/**
 * Renders the About Us page component.
 *
 * This component displays information about the project and the development team.
 * It includes a banner image, a title, and a main paragraph describing the project's problem statement,
 * solution, and the members of the development and client teams.
 *
 * @returns The About Us page component.
 */

export const AboutUs = () => {
  return (
    <div className="flex flex-col w-full h-auto">
      <div className="w-[60%] flex flex-col self-center my-6">
        {/* Title - Start */}
        <div className="bg-blue-950 rounded-t-[10px] w-full flex justify-center p-[10px] font-bold">
          <h1 className="text-white">About Us</h1>
        </div>
        {/* Title - End */}

        {/* Main Paragraph Start */}
        <div className="bg-footer rounded-b-[10px] p-[10px] flex justify-start">
          <p className="flex w-full h-auto text-left text-black justify-left text-1xl">
            Sky Companion is created by a group of students from Dalhousie
            University as part of the CSCI 5308 - Advanced Topics in Software
            Development (Fall 2023) course.
            <br />
            <br />
            Problem Statement: <br /> People often require assistance when
            traveling. This frequently happens on flights with multiple
            stopovers. People need to check-in, security check, navigate the
            airport, need in-flight support, change terminals, and even check-in
            their luggage sometimes again on the same route. Through our system,
            users will be able to find companions who will help them on their
            journey. The companion will already be traveling on the same route.
            <br />
            <br />
            Our Solution <br />
            Sky Companion is an affordable way to find companions in the same
            flight. Free companionship is provided by Volunteers. End to end
            Availability on wide range of routes as anyone can be a companion,
            independent of airlines. Provides an option to Customize the routes.
            Profile review and rating along with chat feature.
            <br />
            <br />
            The Development team includes:
            <br />
            1. Divyank Mayankkumar Shah
            <br />
            2. Jahnavi Gajjala
            <br />
            3. Jahnavi Prasad Srirampurapu
            <br />
            4. Ketul Patel
            <br />
            5. Mann Patel
            <br />
            <br />
            The Client team includes:
            <br />
            1. Aditya Maheshbhai Purohit
            <br />
            2. Dharmil Nareshkumar Shah
            <br />
            3. Princess Ashwinbhai Kachhadiya
            <br />
            4. Roshni Vikrambhai Joshi
            <br />
            5. Ubaidullah Muhammad Naeem
            <br />
            <br />
            Course Instructor: Dr. Tushar Sharma
          </p>
        </div>
        {/* Main Paragraph End */}
      </div>
    </div>
  );
};
