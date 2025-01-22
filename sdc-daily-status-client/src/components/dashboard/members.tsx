import { IconType } from "react-icons";
import { FaMale, FaFemale } from "react-icons/fa";

interface MemberProps {
  name: string;
  icon: IconType;
  role: string;
  avatar: string;
  leaves: Date[];
  trainingModule: string[];
}
export const Month = [
  "January",
  "February",
  "March",
  "April",
  "May",
  "June",
  "July",
  "August",
  "September",
  "October",
  "November",
  "December",
];

export const Members: Array<MemberProps> = [
  {
    name: "Shailesh Srivastava",
    icon: FaMale,
    role: "Coach",
    avatar: "https://bit.ly/dan-abramov",
    leaves: [
      new Date("01/27/2023"),
      new Date("01/20/2023"),
      new Date("03/02/2023"),
      new Date("04/15/2023"),
      new Date("07/16/2023"),
      new Date("08/07/2023"),
      new Date("10/06/2023"),
      new Date("11/09/2023"),
    ],
    trainingModule: ["Cyber Security", "GDPR", "Sexual Harassment"],
  },
  {
    name: "Kedar Nandimath",
    icon: FaMale,
    role: "Dev Lead",
    avatar: "https://bit.ly/kent-c-dodds",
    leaves: [
      new Date("02/20/2023"),
      new Date("04/18/2023"),
      new Date("06/15/2023"),
      new Date("07/16/2023"),
      new Date("08/12/2023"),
      new Date("09/07/2023"),
      new Date("11/18/2023"),
    ],
    trainingModule: ["Diversity Training", "HIPPA", "HR compliance"],
  },
  {
    name: "Aditya Medhe",
    icon: FaMale,
    role: "Sr. Software Engineer",
    avatar: "https://bit.ly/ryan-florence",
    leaves: [
      new Date("02/20/2023"),
      new Date("04/18/2023"),
      new Date("06/15/2023"),
      new Date("07/16/2023"),
      new Date("08/12/2023"),
      new Date("09/07/2023"),
      new Date("11/18/2023"),
    ],
    trainingModule: ["GDPR", "HIPPA", "Diversity Training"],
  },
  {
    name: "Sampad Barman",
    icon: FaMale,
    role: "Sr. Software Engineer",
    avatar: "https://bit.ly/prosper-baba",
    leaves: [
      new Date("02/20/2023"),
      new Date("04/18/2023"),
      new Date("06/15/2023"),
      new Date("07/16/2023"),
      new Date("08/12/2023"),
      new Date("09/07/2023"),
      new Date("11/18/2023"),
    ],
    trainingModule: ["Business ethics", "HIPPA", "Diversity Training"],
  },
  {
    name: "Dhanashri Mundada",
    icon: FaFemale,
    role: "Software Engineer",
    avatar: "https://bit.ly/code-beast",
    leaves: [
      new Date("02/20/2023"),
      new Date("04/18/2023"),
      new Date("06/15/2023"),
      new Date("07/16/2023"),
      new Date("08/12/2023"),
      new Date("09/07/2023"),
      new Date("11/18/2023"),
    ],
    trainingModule: [
      "Business ethics",
      "Workplace Safety",
      "Diversity Training",
    ],
  },
  {
    name: "Sandeep Raina",
    icon: FaMale,
    role: "Test Lead",
    avatar: "https://bit.ly/sage-adebayo",
    leaves: [
      new Date("02/20/2023"),
      new Date("04/18/2023"),
      new Date("06/15/2023"),
      new Date("07/16/2023"),
      new Date("08/12/2023"),
      new Date("09/07/2023"),
      new Date("11/18/2023"),
    ],
    trainingModule: ["GDPR", "Workplace Safety", "Diversity Training"],
  },
  {
    name: "Bijimol CT",
    icon: FaFemale,
    role: "Sr. Test Engineer",
    avatar: "",
    leaves: [
      new Date("02/20/2023"),
      new Date("04/18/2023"),
      new Date("06/15/2023"),
      new Date("07/16/2023"),
      new Date("08/12/2023"),
      new Date("09/07/2023"),
      new Date("11/18/2023"),
    ],
    trainingModule: ["Cyber Security", "GDPR", "Sexual Harassment"],
  },
];
