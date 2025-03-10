import { SearchIcon } from "@chakra-ui/icons";
import {
  InputGroup,
  Input,
  InputRightElement,
  Button,
  InputLeftElement,
} from "@chakra-ui/react";
import React, { useEffect, useState } from "react";
import { Task } from "./tasksLoader";
import { useNavigate } from "react-router-dom";

export const SearchBar = () => {
  const API_URL = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();
  const [tasks, setTasks] = useState<Task[]>([]);
  const [searchInput, setSearchInput] = useState("");

  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(`${API_URL}/api/worklog/daily`);
        const result = await response.json();
        setTasks(result);
      } catch (error) {
        console.error('Error fetching data:', error);
      }
    };
    fetchData();
  }, []);

  const handleChange = (e: any) => {
    e.preventDefault();
    setSearchInput(e.target.value);
  };

  const showResult = () => {
    if (searchInput.length > 0) {
      const filteredTasks = tasks.filter((task) => {
        // if (task.workDetails.match(searchInput)) {
          return task.workDetails.match(searchInput)
          || task.userName.match(searchInput);
        // }
      });
      if (filteredTasks.length > 0) {
        // onSearchResults(filteredTasks);
          console.log("filtered ::: " + filteredTasks);
          setSearchInput("");
          navigate("/search-result", { state: { tasks: filteredTasks } })
        }else{
        // onSearchResults([]);
        navigate("/not-found");
      }
    }
    //   throw new Error("No results found...")
  };

  return (
    <InputGroup size="md" w={{ base: '100%', md: '50%', lg: '25%' }}>
      <InputLeftElement
        pointerEvents="none"
        children={<SearchIcon color="gray.600" />}
      />
      <Input
        pr="4.5rem"
        type="search"
        placeholder="Search..."
        value={searchInput}
        onChange={handleChange}
      />
      <InputRightElement width="4.5rem">
        <Button h="1.75rem" size="sm" bg="blue.500" onClick={showResult}>
          Search
        </Button>
      </InputRightElement>
    </InputGroup>
  );
};
