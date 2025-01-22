import React from "react";
import { Box, IconButton, useBreakpointValue } from "@chakra-ui/react";
// Here we have used react-icons package for the icons
import { BiLeftArrowAlt, BiRightArrowAlt } from "react-icons/bi";
// And react-slick as our Carousel Lib
import Slider from "react-slick";

// Settings for the slider
const settings = {
  centerMode: true,
  centerPadding: '10px',
  slidesToShow: 5,
  speed: 500,
  slidesToScroll: 1,
  arrows: true,
  dots: true,
  // responsive: [
  //   {
  //     breakpoint: 1024,
  //     settings: {
  //       slidesToShow: 3,
  //       slidesToScroll: 3,
  //       infinite: true,
  //       dots: true
  //     }
  //   },
  //   {
  //     breakpoint: 600,
  //     settings: {
  //       slidesToShow: 2,
  //       slidesToScroll: 2,
  //       initialSlide: 2
  //     }
  //   },
  //   {
  //     breakpoint: 480,
  //     settings: {
  //       slidesToShow: 1,
  //       slidesToScroll: 1
  //     }
  //   }]
};

export const Carousel = ()=> {
  // As we have used custom buttons, we need a reference variable to
  // change the state
  const [slider, setSlider] = React.useState<Slider | null>(null);

  // These are the breakpoints which changes the position of the
  // buttons as the screen size changes
  const top = useBreakpointValue({ base: "90%", md: "50%" });
  const side = useBreakpointValue({ base: "30%", md: "10px" });

  // These are the images used in the slide
  const slides = [
    "https://images.unsplash.com/photo-1612852098516-55d01c75769a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDR8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60",
    "https://images.unsplash.com/photo-1627875764093-315831ac12f7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDJ8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60",
    "https://images.unsplash.com/photo-1571432248690-7fd6980a1ae2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDl8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60",
    "https://images.unsplash.com/photo-1612852098516-55d01c75769a?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDR8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60",
    "https://images.unsplash.com/photo-1627875764093-315831ac12f7?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDJ8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60",
    "https://images.unsplash.com/photo-1571432248690-7fd6980a1ae2?ixlib=rb-1.2.1&ixid=MnwxMjA3fDB8MHxwaG90by1yZWxhdGVkfDl8fHxlbnwwfHx8fA%3D%3D&auto=format&fit=crop&w=900&q=60",
    // "https://images.app.goo.gl/UpMPQiz72arR4Bts5",
    // "https://images.app.goo.gl/N75WvU6q5HgpCWaC9",
    // "https://images.app.goo.gl/ZLaYtPQjRpcRaTkB8",
    // "https://images.app.goo.gl/oWDteK3KpV2NAaQ9A",
    // "https://images.app.goo.gl/rF975fTfrUBUAn8e6",
  ];

  return (
<Box
    position={"relative"}
    height={"400px"}
    width={"full"}
    overflow={"hidden"}
  >
    {/* CSS files for react-slick */}
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick.min.css"
    />
    <link
      rel="stylesheet"
      type="text/css"
      href="https://cdnjs.cloudflare.com/ajax/libs/slick-carousel/1.6.0/slick-theme.min.css"
    />
    {/* Left Icon */}
    <IconButton
      aria-label="left-arrow"
      colorScheme="messenger"
      borderRadius="full"
      position="absolute"
      left={side}
      top={top}
      transform={"translate(0%, -50%)"}
      zIndex={2}
      onClick={() => slider?.slickPrev()}
    >
      <BiLeftArrowAlt />
    </IconButton>
    {/* Right Icon */}
    <IconButton
      aria-label="right-arrow"
      colorScheme="messenger"
      borderRadius="full"
      position="absolute"
      right={side}
      top={top}
      transform={"translate(0%, -50%)"}
      zIndex={2}
      onClick={() => slider?.slickNext()}
    >
      <BiRightArrowAlt />
    </IconButton>
    {/* Slider */}
    <Slider {...settings} ref={(slider) => setSlider(slider)}>
      {slides.map((slide, index) => (
        <Box
          key={index}
          height={"6xl"}
          position="relative"
          backgroundPosition="center"
          backgroundRepeat="no-repeat"
          backgroundSize="cover"
          backgroundImage={`url(${slide})`}
        />
      ))}
    </Slider>
  </Box>
    );
  }
