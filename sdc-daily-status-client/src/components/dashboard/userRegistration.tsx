import {
  Box,
  VStack,
  FormControl,
  FormLabel,
  Input,
  RadioGroup,
  Stack,
  Radio,
  Button,
  useToast,
  HStack,
  Text
} from "@chakra-ui/react";
import axios from "axios";
import { useFormik } from "formik";
import React, { useState } from "react";
import { useNavigate } from "react-router-dom";
import * as Yup from "yup";

export const UserRegistration = () => {
  const [showForm, setShowForm] = useState(false);
  const toast = useToast();
  const navigate = useNavigate();
  const handleClick = (shouldClose : boolean) => {
    setShowForm(shouldClose);
  };

  const initialUser = {
    userId: "",
    userName: "",
    designation: "",
    project: "",
    gender: undefined,
  };

  const userRegistrationFormValidation = Yup.object().shape({
    userId: Yup.string()
      .required("User id required"),
    userName: Yup.string()
      .min(2, "Too short")
      .required("User name is required"),
    designation: Yup.string()
      .min(2, "Too short")
      .required("designation is required"),
    project: Yup.string()
      .min(2, "Too short")
      .required("Project name is required"),
    gender: Yup.string().nonNullable().required("gender is required"),
  });

  const formik = useFormik({
    initialValues: initialUser,
    validationSchema: userRegistrationFormValidation,
    onSubmit: (value) => {
      const newUser = { ...value, value };
      axios.post("http://localhost:8088/api/users/register", newUser).then((res) => {
        toast({
          title: `User has been successfully registered!!`,
          status: `success`,
          isClosable: true,
          position: `top`,
        });
        handleClick(false);
        navigate("/team");
      });
    },
  });

  return (
    <>
      {!showForm &&  
       <Box p={5}>
      <VStack spacing={4}>
        <Text fontSize="lg">
          Sign up in case of new user
        </Text>
        <Button colorScheme="teal" onClick={() => handleClick(true)}>
          Sign up
        </Button>
      </VStack>
    </Box>
      }
      {showForm && (
        <Box p={6} rounded="md" >
          <form onSubmit={formik.handleSubmit}>
            <VStack spacing={4} align="flex-start">
                <FormControl>
                  <HStack>
                  <FormLabel htmlFor="project">Project</FormLabel>
                  <Input
                    id="project"
                    name="project"
                    type="text"
                    variant="filled"
                    onChange={formik.handleChange}
                    value={formik.values.project}
                  />
                  {formik.touched.project && formik.errors.project ? (
                    <div className="error">{formik.errors.project}</div>
                  ) : null}
                
                <FormLabel htmlFor="userId">Id</FormLabel>
                <Input
                  id="userId"
                  name="userId"
                  type="text"
                  variant="filled"
                  onChange={formik.handleChange}
                  value={formik.values.userId}
                  placeholder="lenze id"
                />
                {formik.touched.userId && formik.errors.userId ? (
                  <div className="error">{formik.errors.userId}</div>
                ) : null}
                </HStack>
                </FormControl>
              <FormControl>
                <FormLabel htmlFor="author">Name</FormLabel>
                <Input
                  id="userName"
                  name="userName"
                  type="text"
                  variant="filled"
                  onChange={formik.handleChange}
                  value={formik.values.userName}
                />
                {formik.touched.userName && formik.errors.userName ? (
                  <div className="error">{formik.errors.userName}</div>
                ) : null}
              </FormControl>
              <FormControl>
                <FormLabel htmlFor="designation">Designation</FormLabel>
                <Input
                  id="designation"
                  name="designation"
                  type="text"
                  variant="filled"
                  onChange={formik.handleChange}
                  value={formik.values.designation}
                />
                {formik.touched.designation && formik.errors.designation ? (
                  <div className="error">{formik.errors.designation}</div>
                ) : null}
              </FormControl>

              <FormControl>
                <FormLabel htmlFor="gender">Gender</FormLabel>
                <RadioGroup>
                  <Stack spacing={5} direction="row">
                    <Radio
                      id="gender"
                      name="gender"
                      size="md"
                      onChange={formik.handleChange}
                      value="male"
                    >
                      Male
                    </Radio>
                    <Radio
                      id="gender"
                      name="gender"
                      size="md"
                      onChange={formik.handleChange}
                      value="female"
                    >
                      Female
                    </Radio>
                  </Stack>
                  {formik.touched.gender && formik.errors.gender ? (
                    <div className="error">{formik.errors.gender}</div>
                  ) : null}
                </RadioGroup>
              </FormControl>
              <HStack>
              <Button type="submit" colorScheme="purple" >
                Register
              </Button>
              <Button colorScheme="red" onClick={()=> handleClick(false)}>Close</Button>
              </HStack>
            </VStack>
          </form>
        </Box>
      )}
    </>
  );
};
