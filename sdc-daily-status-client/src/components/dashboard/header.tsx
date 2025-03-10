import {
  Avatar,
  Box,
  Button,
  Flex,
  HStack,
  Menu,
  MenuButton,
  MenuDivider,
  MenuItem,
  MenuList,
  useColorModeValue,
  Image,
} from "@chakra-ui/react";
import { NavigationLink } from "./navigationLink";

export const Header = (props: any) => {
  return (
    <>
      <Box bg={useColorModeValue("purple.100", "purple.900")} px={4}>
        <Flex h={16} alignItems={"center"} justifyContent={"space-between"}>
          <HStack spacing={8} alignItems={"center"}>
            <Box>
              <Image
                borderRadius="full"
                boxSize="70px"
                src="/app-logo.png"
                alt="logo"
              />
            </Box>
            <HStack
              as={"nav"}
              spacing={4}
              display={{ base: "none", md: "flex" }}
            >
              <NavigationLink path="/">Dashboard</NavigationLink>
              <NavigationLink path="/projects">Projects</NavigationLink>
              <NavigationLink path="/team">Team</NavigationLink>
            </HStack>
          </HStack>
          <Flex alignItems={"center"}>
            <Menu>
              <MenuButton
                as={Button}
                rounded={"full"}
                variant={"link"}
                cursor={"pointer"}
                minW={0}
              >
                <Avatar size={"sm"} src={"/avatar.jpg"} />
              </MenuButton>
              <MenuList>
                <MenuItem>Profile</MenuItem>
                <MenuDivider />
                <MenuItem>Sign out</MenuItem>
              </MenuList>
            </Menu>
          </Flex>
        </Flex>
      </Box>
    </>
  );
};
