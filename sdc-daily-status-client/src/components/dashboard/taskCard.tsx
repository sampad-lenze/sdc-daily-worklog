import {
  Card,
  CardHeader,
  Flex,
  Heading,
  CardBody,
  CardFooter,
  Box,
  Text,
  Tooltip,
  Divider,
  Avatar,
  Checkbox,
  Spacer,
  IconButton,
  ButtonGroup,
} from "@chakra-ui/react";
import { FaEdit, FaRegCalendarCheck } from "react-icons/fa";
import { Task } from "./tasksLoader";

export const TaskCard = (task: Task) => {
  const textStyle = {
    maxWidth: "100%",
    display: "-webkit-box",
    WebkitBoxOrient: "vertical",
    WebkitLineClamp: 1,
    overflow: "hidden",
    textOverflow: "ellipsis",
  };

  return (
    <>
      <Card key={task.id}>
        <CardHeader>
          <Flex flex="1" gap="4" alignItems="center" flexWrap="wrap">
            <Box w="60px" h="60px">
              <Avatar name={task.userName} src={task.avatar} /> 
            </Box>
            <Box>
              <Heading size="sm">{task.projectName}</Heading>
              <Text color="blue.600">by {task.userName}</Text>
            </Box>
          </Flex>
        </CardHeader>
        <CardBody color="grey.500">
          <Tooltip label={task.workDetails} aria-label="A tooltip">
            <Text sx={textStyle}>{task.workDetails}</Text>
          </Tooltip>
        </CardBody>
        <Divider />
        <CardFooter>
          <Flex flex="1" gap="1" alignItems="center" flexWrap="wrap">
            <Checkbox
              size="md"
              colorScheme={task.status === 'done' ? "green" : "orange"}
              defaultChecked
            >
              {task.status === 'done' ? "Done" : "In progress"}
            </Checkbox>
            <Spacer />
            <Flex>
              <FaRegCalendarCheck />
              {new Date(task.date).toLocaleDateString()}
            </Flex>
          </Flex>
        </CardFooter>
      </Card>
    </>
  );
};