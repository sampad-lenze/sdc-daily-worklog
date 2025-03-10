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
  doneTicketCount: number;
  inProgressTicketCount: number;
  totalWorkingHours: number;
}
