import {
  Box,
  Button,
  Flex,
  FormControl,
  FormLabel,
  HStack,
  Input,
  Radio,
  RadioGroup,
  Stack,
  Textarea,
  VStack,
  useToast,
  Text,
} from "@chakra-ui/react";
import { useFormik } from "formik";
import { useNavigate } from "react-router-dom";
import axios from "axios";
import { getWeek } from "date-fns";
import * as Yup from "yup";
import "../../style/utility.css";
import { UserRegistration } from "./userRegistration";
import { useEffect, useState } from "react";

export const TaskForm = () => {
  const navigate = useNavigate();
  const toast = useToast();
  const successMessage = () => {
    toast({
      title: `New task has been added successfully.`,
      status: "success",
      isClosable: true,
      position: "top",
    });
  };

  const initialTask = {
    date: undefined,
    workingHours: 0,
    userName: "",
    workDetails: "",
    projectName: "",
    status: undefined,
    weekNumber: 0,
  };

  const formValidationSchema = Yup.object().shape({
    date: Yup.date()
      .max(new Date(), "Date cannot be in the future")
      .required("Required")
      .nonNullable(),
    workingHours: Yup.number()
      .required("Working hours are required")
      .min(0, "Working hours cannot be negative"),
    userName: Yup.string()
      .min(2, "Too short")
      .required("User name is required"),
    workDetails: Yup.string()
      .min(2, "Too short")
      .required("Work details are required"),
    projectName: Yup.string()
      .min(2, "Too short")
      .required("Project name is required"),
    status: Yup.string().nonNullable().required("Status is required"),
  });

  const formik = useFormik({
    initialValues: initialTask,
    validationSchema: formValidationSchema,
    onSubmit: (value, { resetForm }) => {
      const newTask = {
        ...value,
        // workingHours: value.workingHours,
        // userName: value.userName,
        // workDetails: value.workDetails,
        // projectName: value.projectName,
        // status: value?.status,
        date: new Date(value.date ? value.date : ""),
      };
      axios
        .post("http://localhost:8088/api/worklog/daily", newTask)
        .then((res) => {
          successMessage();
          resetForm();
          setRefreshTrigger((prev) => prev + 1);
          navigate("/team");
        })
        .catch((error) => {
          console.error("There was an error making the request:", error);
        });
    },
  });

  const [refreshTrigger, setRefreshTrigger] = useState(0);
  const [pendingUserList, setpendingUserList] = useState([]);
  useEffect(() => {
    const fetchData = async () => {
      try {
        const response = await fetch(
          "http://localhost:8088/api/worklog/daily/NAT/pending"
        );
        const result = await response.json();
        setpendingUserList(result);
      } catch (error) {
        console.error("Error fetching data:", error);
      }
    };
    fetchData();
  }, [refreshTrigger]);

  return (
    <>
      <Flex bg="gray.100">
        <Box bg="white" p={6} rounded="md" w="50%" h="full">
          <form onSubmit={formik.handleSubmit}>
            <VStack spacing={4} align="flex-start">
              <HStack spacing={4} align="flex-start">
                <FormControl>
                  <FormLabel htmlFor="projectName">Project</FormLabel>
                  <Input
                    id="projectName"
                    name="projectName"
                    type="text"
                    variant="filled"
                    onChange={formik.handleChange}
                    value={formik.values.projectName}
                  />
                  {formik.touched.projectName && formik.errors.projectName ? (
                    <div className="error">{formik.errors.projectName}</div>
                  ) : null}
                </FormControl>
                <FormControl>
                  <FormLabel htmlFor="date">Date</FormLabel>
                  <Input
                    id="date"
                    name="date"
                    type="date"
                    variant="filled"
                    onChange={formik.handleChange}
                  />
                  {formik.touched.date && formik.errors.date ? (
                    <div className="error">{formik.errors.date}</div>
                  ) : null}
                </FormControl>
                <FormControl>
                  <FormLabel htmlFor="weekNumber">Week Number</FormLabel>
                  <Input
                    id="weekNumber"
                    name="weekNumber"
                    type="number"
                    variant="filled"
                    onChange={formik.handleChange}
                    value={getWeek(
                      formik.values.date ? formik.values.date : ""
                    )}
                    disabled={true}
                  />
                  {formik.touched.weekNumber && formik.errors.weekNumber ? (
                    <div className="error">{formik.errors.weekNumber}</div>
                  ) : null}
                </FormControl>
              </HStack>
              <FormControl>
                <FormLabel htmlFor="description">Work details</FormLabel>
                <Textarea
                  id="workDetails"
                  name="workDetails"
                  value={formik.values.workDetails}
                  onChange={formik.handleChange}
                  placeholder="Write down your daily task description"
                  size="sm"
                />
                {formik.touched.workDetails && formik.errors.workDetails ? (
                  <div className="error">{formik.errors.workDetails}</div>
                ) : null}
              </FormControl>
              <HStack spacing={4} align="flex-start">
                <FormControl>
                  <FormLabel htmlFor="workingHours">Working hours</FormLabel>
                  <Input
                    id="workingHours"
                    name="workingHours"
                    type="number"
                    variant="filled"
                    onChange={formik.handleChange}
                    value={formik.values.workingHours}
                    // w="25%"
                  />
                  {formik.touched.workingHours && formik.errors.workingHours ? (
                    <div className="error">{formik.errors.workingHours}</div>
                  ) : null}
                </FormControl>
                <FormControl>
                  <FormLabel htmlFor="author">Author</FormLabel>
                  <Input
                    id="userName"
                    name="userName"
                    type="text"
                    variant="filled"
                    onChange={formik.handleChange}
                    value={formik.values.userName}
                    // w="75%"
                  />
                  {formik.touched.userName && formik.errors.userName ? (
                    <div className="error">{formik.errors.userName}</div>
                  ) : null}
                </FormControl>
              </HStack>
              <FormControl>
                <FormLabel htmlFor="Status">Status</FormLabel>
                <RadioGroup defaultValue="2">
                  <Stack spacing={5} direction="row">
                    <Radio
                      id="status"
                      name="status"
                      size="md"
                      colorScheme="orange"
                      onChange={formik.handleChange}
                      value="in progress"
                    >
                      In progress
                    </Radio>
                    <Radio
                      id="status"
                      name="status"
                      size="md"
                      colorScheme="green"
                      onChange={formik.handleChange}
                      value="done"
                    >
                      Done
                    </Radio>
                    <Radio
                      id="status"
                      name="status"
                      size="md"
                      colorScheme="red"
                      onChange={formik.handleChange}
                      value="on leave"
                    >
                      On leave
                    </Radio>
                  </Stack>
                  {formik.touched.status && formik.errors.status ? (
                    <div className="error">{formik.errors.status}</div>
                  ) : null}
                </RadioGroup>
              </FormControl>
              <Button type="submit" colorScheme="purple" width="25%">
                Submit
              </Button>
            </VStack>
          </form>
        </Box>
        <Box bg="palevioletred" rounded="md" w="25%">
          <UserRegistration />
        </Box>
        <Box bg="teal" rounded="md" w="25%">
          <Box p={5}>
            <VStack spacing={4} alignItems="flex-start">
              {pendingUserList.length === 0 ? (
                <Text fontSize="lg">
                  All users have submitted their daily task details.
                </Text>
              ) : (
                <>
                  <Text fontSize="lg">
                    The following users have not yet submitted their daily task
                    details:
                  </Text>
                  {pendingUserList.map((user) => (
                    <li key={user}>{user}</li>
                  ))}
                </>
              )}
            </VStack>
          </Box>
        </Box>
      </Flex>
    </>
  );
};
