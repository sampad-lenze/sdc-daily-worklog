import {
  Box,
  Flex,
  useColorModeValue,
  Drawer,
  DrawerContent,
  useDisclosure,
  Heading,
  SimpleGrid,
  Spacer,
  Button,
  HStack,
} from "@chakra-ui/react";
import { useEffect, useState } from "react";
import { Header } from "./header";
import { Footer } from "./footer";

import { TaskCard } from "./taskCard";
import { SearchBar } from "./searchBar";
import { Task } from "./tasksLoader";
import { MobileNav, SidebarContent } from "./navigationBar";
import { useUser } from "../context/userContext";

export const Projects = () => {
  const { isOpen, onOpen, onClose } = useDisclosure();
  const [tasks, setTasks] = useState<Task[]>([]);
  const { selectedUser } = useUser();

  useEffect(() => {
    const fetchTasksForUser = async () => {
      if (selectedUser) {
        try {
          const response = await fetch(`http://localhost:8088/api/worklog/daily/${selectedUser}`);
          const data = await response.json();
          setTasks(data);
        } catch (error) {
          console.error('Error fetching tasks:', error);
        }
      }
      // else{
      //   try{
      //   const response = await fetch(`http://localhost:8088/api/worklog/daily`);
      //     const data = await response.json();
      //     setTasks(data);
      //   } catch (error) {
      //     console.error('Error fetching tasks:', error);
      //   }
      // }
    };
  
    fetchTasksForUser();
  }, [selectedUser]);

  const [filteredTasks, setFilteredTasks] = useState<Task[]>([]);
  const [currentMonth, setCurrentMonth] = useState(new Date());

  const fetchTasks = async () => {
    try {
      const response = await fetch(`http://localhost:8088/api/worklog/daily`);
      const data = await response.json();
      setTasks(data);
    } catch (error) {
      console.error('Error fetching tasks:', error);
    }
  };

  useEffect(() => {
    fetchTasks();
  }, []);

  useEffect(() => {
    filterTasksByMonth(currentMonth);
  }, [tasks, currentMonth]);

  const filterTasksByMonth = (month: Date) => {
    const year = month.getFullYear();
    const monthIndex = month.getMonth() + 1; // getMonth() returns 0-11, so add 1 for 1-12

    const filtered = tasks.filter((task) => {
      const taskDate = new Date(task.date);
      return taskDate.getFullYear() === year && taskDate.getMonth() + 1 === monthIndex;
    });

    setFilteredTasks(filtered);
  };

  const handlePreviousMonth = () => {
    setCurrentMonth(new Date(currentMonth.setMonth(currentMonth.getMonth() - 1)));
  };

  const handleNextMonth = () => {
    setCurrentMonth(new Date(currentMonth.setMonth(currentMonth.getMonth() + 1)));
  };

  return (
    <>
      <Header />
      <Flex bg="gray.100">
        <Box h="full" bg={useColorModeValue("gray.100", "gray.900")}>
          <SidebarContent
            onClose={() => onClose}
            display={{ base: "none", md: "block" }}
          />
          <Drawer
            isOpen={isOpen}
            placement="left"
            onClose={onClose}
            returnFocusOnClose={false}
            onOverlayClick={onClose}
            size="full"
          >
            <DrawerContent>
              <SidebarContent onClose={onClose} />
            </DrawerContent>
          </Drawer>
          {/* mobilenav */}
          <MobileNav display={{ base: "flex", md: "none" }} onOpen={onOpen} />
        </Box>
        <Box ml={{ base: 0, md: 60 }} p="6" style={{ width: "100%" }}>
          <Flex justifyContent={"space-evenly"} mb="20px">
            <Heading>
              <HStack>
            <Button onClick={handlePreviousMonth}>Previous</Button>
              <h1>{currentMonth.toLocaleString('en-EN', { month: "long" })} {currentMonth.getFullYear()}</h1>
            <Button onClick={handleNextMonth}>Next</Button>
            </HStack>
            </Heading>
            <Spacer />
            <SearchBar/>
          </Flex>
          <SimpleGrid columns={5} spacingX="30px" spacingY="20px">
            {/* {tasks && tasks.map((t) => <TaskCard key={t.id} {...t} />)} */}
            {filteredTasks && filteredTasks.map((t) => <TaskCard key={t.id} {...t} />)}
          </SimpleGrid>
        </Box>
      </Flex>
      <Footer />
    </>
  );
};