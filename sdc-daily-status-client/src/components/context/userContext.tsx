import { createContext, ReactNode, useContext, useState } from "react";

interface UserContextType {
    selectedUser: string | null;
    setSelectedUser: React.Dispatch<React.SetStateAction<string | null>>;
  }

const defaultUser : UserContextType = {
    selectedUser: null,
    setSelectedUser: () => {},
}
const UserContext = createContext(defaultUser);

export const useUser = () => {
    return useContext(UserContext);
}

interface UserProviderProps {
    children: ReactNode;
  }
  

export const UserProvider = ({children} : UserProviderProps) => {
    const [selectedUser, setSelectedUser] = useState<string | null>(null);
    return (<UserContext.Provider value={{selectedUser, setSelectedUser}}>
        {children}</UserContext.Provider>);
}