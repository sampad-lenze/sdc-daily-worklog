import {
  Box,
  Heading,
  useColorModeValue,
  Button,
  Center,
  Link,
} from "@chakra-ui/react";
import { Footer } from "./footer";
import { Header } from "./header";

export const NotFound = () => {
  return (
    <>
      <Header />
      <Box minH="100vh" bg={useColorModeValue("gray.100", "gray.900")}>
        <Center>
          <Heading>No results found !!!! </Heading>
        </Center>
        <Center>
          <Button colorScheme="purple">
            <Link href="/projects">Go Back</Link>
          </Button>
        </Center>
      </Box>
      <Footer />
    </>
  );
};
