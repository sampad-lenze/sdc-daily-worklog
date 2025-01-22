// export const TasksLoader = async () => {
//   const res = await fetch("http://localhost:8088/api/worklog/daily");
//   return await res.json();
// };

// export const UsersLoader = async () => {
//   const res = await fetch("http://localhost:8088/api/users");
//   return await res.json();
// };

export interface Task {
  id: string;
  date: Date;
  workingHours: number,
  userName: string,
  workDetails: string,
  projectName: string,
  status: string,
  weekNumber: number,
  avatar: string
}

export interface User {
  userId: string;
  userName: string;
  designation: string;
  gender: string;
  project: string;
  // avatar: string;
  // leaves: Date[];
  // trainingModule: string[];
  doneTicketCount: number;
  inProgressTicketCount: number;
  totalWorkingHours: number;
}
