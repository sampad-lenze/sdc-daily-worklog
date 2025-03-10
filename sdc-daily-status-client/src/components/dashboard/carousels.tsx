import React from "react";
import { Box, IconButton, useBreakpointValue } from "@chakra-ui/react";
import { BiLeftArrowAlt, BiRightArrowAlt } from "react-icons/bi";
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
};

export const Carousel = ()=> {
  const [slider, setSlider] = React.useState<Slider | null>(null);
  const top = useBreakpointValue({ base: "90%", md: "50%" });
  const side = useBreakpointValue({ base: "30%", md: "10px" });
  const slides = [
    "../../../../5.png",
    "../../../../1.png",
    "../../../../2.png",
    "../../../../3.png",
    "../../../../4.png",
    "../../../../5.png",
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
          border="2px solid cornflowerblue"
        />
      ))}
    </Slider>
  </Box>
    );
  }
