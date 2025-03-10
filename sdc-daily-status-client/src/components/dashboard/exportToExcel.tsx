import {
  HStack,
  Button,
  useToast,
  Box,
  FormControl,
  FormLabel,
  Input,
  VStack,
} from "@chakra-ui/react";
import { useFormik } from "formik";
import { useNavigate } from "react-router-dom";
import * as Yup from "yup";

export const ExportToExcel = ({ handleClickForDownloadForm }: any) => {
  const API_URL = process.env.REACT_APP_API_URL;
  const navigate = useNavigate();
  const toast = useToast();
  const initialFormValue = {
    userName: "",
    startDate: undefined,
    endDate: undefined,
  };
  const downloadFormValidation = Yup.object().shape({
    userName: Yup.string()
      .min(2, "Too short")
      .required("User name is required"),
    startDate: Yup.date().required("Required"),
    endDate: Yup.date()
      .required("Required")
      .test("dates", "Start date must be before end date", function (value) {
        const { startDate } = this.parent;
        return startDate && value
          ? new Date(startDate) < new Date(value)
          : true;
      }),
  });

  const formik = useFormik({
    initialValues: initialFormValue,
    validationSchema: downloadFormValidation,
    onSubmit: async (value) => {
      const newRequest = { ...value, value };
      const response = await fetch(
        `${API_URL}/api/export/generate-excel`,
        {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify(newRequest),
        }
      );

      if (response.ok && response.status === 200) {
        const blob = await response.blob();
        const url = window.URL.createObjectURL(blob);
        const a = document.createElement("a");
        a.href = url;
        a.download = "data.xlsx";
        document.body.appendChild(a);
        a.click();
        a.remove();
        toast({
          title: `Data has been successfully downloaded!!`,
          status: `success`,
          isClosable: true,
          position: `top`,
        });
      } else {
        console.error("Failed to download file:", response.statusText);
      }
      handleClickForDownloadForm(false)
      navigate("/team");
    },
  });

  return (
    <Box p={6} rounded="md">
      <form onSubmit={formik.handleSubmit}>
        <VStack spacing={4} align="flex-start">
          <FormControl>
            <FormLabel htmlFor="userName">Name</FormLabel>
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

            <FormLabel htmlFor="startDate">Start date:</FormLabel>
            <Input
              id="startDate"
              name="startDate"
              type="date"
              variant="filled"
              onChange={formik.handleChange}
              value={formik.values.startDate}
            />
            {formik.touched.startDate && formik.errors.startDate ? (
              <div className="error">{formik.errors.startDate}</div>
            ) : null}

            <FormLabel htmlFor="endDate">End date:</FormLabel>
            <Input
              id="endDate"
              name="endDate"
              type="date"
              variant="filled"
              onChange={formik.handleChange}
              value={formik.values.endDate}
            />
            {formik.touched.endDate && formik.errors.endDate ? (
              <div className="error">{formik.errors.endDate}</div>
            ) : null}
          </FormControl>
          <HStack>
            <Button type="submit" colorScheme="purple">
              Download
            </Button>
            <Button
              colorScheme="red"
              onClick={() => handleClickForDownloadForm(false)}
            >
              Close
            </Button>
          </HStack>
        </VStack>
      </form>
    </Box>
  );
};
