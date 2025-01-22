import { useColorModeValue } from "@chakra-ui/react";
import { Box } from "@chakra-ui/react";

interface NavLinkProps {
  children: React.ReactNode;
  path?: string; 
}
export const NavigationLink = (props: NavLinkProps) => {
  const { children } = props;

  return (
    <Box
      as="a"
      px={2}
      py={1}
      rounded={"md"}
      _hover={{
        textDecoration: "none",
        bg: useColorModeValue("purple.200", "purple.700"),
      }}
      href={props?.path}
    >
      {children}
    </Box>
  );
};
