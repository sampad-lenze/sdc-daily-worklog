import { BoxProps, FlexProps, Box, Flex, Icon, useColorModeValue, CloseButton, IconButton } from "@chakra-ui/react";
import axios from "axios";
import { useState, useEffect } from "react";
import { IconType } from "react-icons";
import { FaFemale, FaMale, FaList } from "react-icons/fa";
import { User } from "./tasksLoader";
import { useUser } from "../context/userContext";

interface SidebarProps extends BoxProps {
    onClose: () => void;
  }
  
  export const SidebarContent = ({ onClose, ...rest }: SidebarProps) => {
    const [users, setUsers] = useState<User[]>([]);
    // const [selectedUser, setSelectedUser] = useState<string | null>('');

    const { setSelectedUser } = useUser();
    useEffect(() => {
      const fetchUsers = async () => {
        try {
          const response = axios.get(
            `http://localhost:8088/api/users`
          );
          setUsers((await response).data);
        } catch (error) {
          console.error("Error fetching tasks:", error);
        }
      };
      fetchUsers();
    }, []);
    

    interface NavItemProps extends FlexProps {
      icon: IconType;
      userName: string;
      children: string;
    }
    
    const NavItem = ({ icon, userName, children, ...rest }: NavItemProps) => {
      return (
        <Box
          as="a"
        //   href={`http://localhost:3000/projects`}
          onClick={() => setSelectedUser(userName)}
          style={{ textDecoration: "none" }}
          _focus={{ boxShadow: "none" }}
        >
          <Flex
            align="center"
            p="4"
            mx="4"
            borderRadius="lg"
            role="group"
            cursor="pointer"
            _hover={{
              bg: "cyan.400",
              color: "red",
            }}
            {...rest}
          >
            {icon && (
              <Icon
                mr="4"
                fontSize="16"
                _groupHover={{
                  color: "red",
                }}
                height="24px"
                as={icon}
              />
            )}
            {children}
          </Flex>
        </Box>
      );
    };
    
    return (
      <>
        <Box
          bg={useColorModeValue("white", "gray.900")}
          borderRight="1px"
          borderRightColor={useColorModeValue("gray.200", "gray.700")}
          w={{ base: "full", md: 60 }}
          pos="fixed"
          h="full"
          {...rest}
        >
          <Flex h="20" alignItems="center" mx="8" justifyContent="space-between">
            <CloseButton
              display={{ base: "flex", md: "none" }}
              onClick={onClose}
            />
          </Flex>
          {users && users.length > 0 ? users.map((m) => (
            <NavItem
              key={m.userId}
              userName={m.userName}
              icon={m.gender === "female" ? FaFemale : FaMale}
            //   onClick={()=> setSelectedUser(m.userName)}
            >
              {m.userName}
            </NavItem>
          )) : "No User registered yet!!"}
        </Box>
      </>
    );
  };
  
  
  
  interface MobileProps extends FlexProps {
    onOpen: () => void;
  }
  export const MobileNav = ({ onOpen, ...rest }: MobileProps) => {
    return (
      <Flex
        ml={{ base: 0, md: 60 }}
        px={{ base: 4, md: 24 }}
        height="20"
        alignItems="center"
        bg={useColorModeValue("white", "gray.900")}
        borderBottomWidth="1px"
        borderBottomColor={useColorModeValue("gray.200", "gray.700")}
        justifyContent="flex-start"
        {...rest}
      >
        <IconButton
          variant="outline"
          onClick={onOpen}
          aria-label="open menu"
          icon={<FaList />}
        />
        {/*
        <Text fontSize="2xl" ml="8" fontFamily="monospace" fontWeight="bold">
          Logo
        </Text> */}
      </Flex>
    );
  };