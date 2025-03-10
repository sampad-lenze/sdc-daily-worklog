import {
  Avatar,
  Badge,
  Box,
  Tab,
  Tabs,
  TabList,
  TabPanel,
  TabPanels,
  Divider,
  Grid,
  GridItem,
  Heading,
  Card,
  CardHeader,
  CardBody,
  Text,
  Stack,
  StackDivider,
  List,
  ListIcon,
  ListItem,
} from "@chakra-ui/react";
import { Footer } from "./footer";
import { useEffect, useState } from "react";
import { User } from "./tasksLoader";
import axios from "axios";
import { FaGrav } from "react-icons/fa";
import { Header } from "./header";

export const Dashboard = () => {
  const API_URL = process.env.REACT_APP_API_URL;
  const [users, setUsers] = useState<User[]>([]);
  useEffect(() => {
    const fetchUsers = async () => {
      try {
        const response = axios.get(`${API_URL}/api/users`);
        setUsers((await response).data);
      } catch (error) {
        console.error("Error fetching tasks:", error);
      }
    };
    fetchUsers();
  }, []);


  interface UserAdditionalData {
    leaveDates: string[];
    trainingModule: string[];
  }

  interface UserLeaveTrainingData {
    [username: string]: UserAdditionalData;
  }
  const [data, setData] = useState<UserLeaveTrainingData>({});

  const fetchUserData = async (username: string) => {
    try {
      const leaveResponse = await fetch(
        `${API_URL}/api/worklog/daily/${username}/leave`
      );
      const leaveData = await leaveResponse.json();

      const trainingResponse = await fetch(
        `${API_URL}/api/worklog/daily/${username}/training`
      );
      const trainingData = await trainingResponse.json();

      setData((prevData) => ({
        ...prevData,
        [username]: {
          leaveDates: leaveData,
          trainingModule: trainingData,
        },
      }));
    } catch (error) {
      console.error("Error fetching leave dates:", error);
    }
  };

  return (
    <>
      <Header />
      <Grid
        left="0"
        right="0"
        minH="100vh"
        width="100%"
        templateRows="repeat(1, 50%)"
        templateColumns="repeat(7, 1fr)"
        gap={2}
      >
        {users.map((m, i) => (
          <GridItem colSpan={1} bg={i % 2 === 0 ? "teal.300" : "yellow.300"}>
            <Heading as="h3" size="lg" noOfLines={1}>
              <Avatar name={m.userName} src={""} />
              {m.userName}
              <Badge ml="1" colorScheme="green">
                {m.designation}
              </Badge>
            </Heading>
            <Divider />
            <Tabs>
              <TabList>
                <Tab
                  _selected={{ color: "white", bg: "blue.500" }}
                  borderX="1px"
                  borderColor="whilte"
                  onClick={() => fetchUserData(m.userName)}
                >
                  Report
                </Tab>
                <Tab
                  _selected={{ color: "white", bg: "blue.500" }}
                  borderX="1px"
                  borderColor="whilte"
                  onClick={() => fetchUserData(m.userName)}
                >
                  Leaves
                </Tab>
                <Tab
                  _selected={{ color: "white", bg: "blue.500" }}
                  borderX="1px"
                  borderColor="whilte"
                  onClick={() => fetchUserData(m.userName)}
                >
                  Training
                </Tab>
              </TabList>

              <TabPanels bg="lightcoral">
                <TabPanel>
                  <Card>
                    <CardBody>
                      <Stack divider={<StackDivider />} spacing="4">
                        <Box>
                          <Heading size="xs" textTransform="uppercase">
                            Total number of tickets:
                          </Heading>
                          <Text pt="2" fontSize="sm">
                            {m.doneTicketCount + m.inProgressTicketCount}
                          </Text>
                        </Box>
                        <Box>
                          <Heading size="xs" textTransform="uppercase">
                            Analysis
                          </Heading>
                          <Text pt="2" fontSize="sm">
                            Done: {m.doneTicketCount}
                          </Text>
                          <Text pt="2" fontSize="sm">
                            In Progress: {m.inProgressTicketCount}
                          </Text>
                          <Text pt="2" fontSize="sm">
                            Current year's total working hour: {m.totalWorkingHours}
                          </Text>
                        </Box>
                      </Stack>
                    </CardBody>
                  </Card>
                </TabPanel>
                <TabPanel>
                  <Card>
                    <CardHeader>
                      <Heading size="md">Availed leave/s</Heading>
                    </CardHeader>
                    <CardBody>
                      <Stack>
                        <Box>
                          {data[m.userName] && (
                            <ul>
                              {data[m.userName].leaveDates.map(
                                (date, index) => (
                                  <List key={index}>
                                    <ListItem key={index}>
                                      <ListIcon as={FaGrav} color="green.500" />
                                      <Text fontWeight="bold">{date}</Text>
                                    </ListItem>
                                  </List>
                                )
                              )}
                            </ul>
                          )}
                        </Box>
                      </Stack>
                    </CardBody>
                  </Card>
                </TabPanel>
                <TabPanel>
                  <Card>
                    <CardBody>
                      <Stack>
                        <Box>
                          <Heading size="md">Training Modules</Heading>
                          <Text pt="2" fontSize="sm">
                            {data[m.userName] && (
                              <ul>
                                {data[m.userName].trainingModule.map(
                                  (module, index) => (
                                    <li key={index}>{module}</li>
                                  )
                                )}
                              </ul>
                            )}
                          </Text>
                        </Box>
                      </Stack>
                    </CardBody>
                  </Card>
                </TabPanel>
              </TabPanels>
            </Tabs>
          </GridItem>
        ))}
      </Grid>
      <Footer />
    </>
  );
};
