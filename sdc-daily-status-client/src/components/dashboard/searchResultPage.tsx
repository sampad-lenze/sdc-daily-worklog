import {
  Box,
  Heading,
  useColorModeValue,
  Button,
  Center,
  Link,
  SimpleGrid,
} from "@chakra-ui/react";
import { Footer } from "./footer";
import { Header } from "./header";
import { TaskCard } from "./taskCard";
import { useLocation } from "react-router-dom";

export const SearchResult = () => {
  const location = useLocation();
  const tasks = location.state?.tasks || [];

  return (
    <>
      <Header />
      <Box minH="100vh" bg={useColorModeValue("gray.100", "gray.900")}>
          <SimpleGrid columns={5} spacingX="30px" spacingY="20px">
            Search result for
            {tasks && tasks.map((t: any) => (
              <TaskCard key={t.id} {...t} />
            ))}
          </SimpleGrid>
        <Center mt='30px'>
          <Button colorScheme="purple">
            <Link href="/projects">Go Back</Link>
          </Button>
        </Center>
      </Box>
      <Footer />
    </>
  );
};
