import * as React from "react";
import { ChakraProvider, theme } from "@chakra-ui/react";
import { BrowserRouter, Routes, Route } from "react-router-dom";
import { Dashboard } from "./components/dashboard/dashboard";
import { Teams } from "./components/dashboard/teams";
import { Projects } from "./components/dashboard/projects";
import { NotFound } from "./components/dashboard/notFound";
import { SearchResult } from "./components/dashboard/searchResultPage";

export const App = () => (
  <ChakraProvider theme={theme}>
    <BrowserRouter>
      <Routes>
        {/* <Route index path="/" element={<Login />} /> */}
        <Route path="/" element={<Dashboard />} />
        <Route path="/projects" element={<Projects />} />
        <Route path="/team" element={<Teams />} />
        <Route path="/not-found" element={<NotFound />} />
        <Route path="/search-result" element={<SearchResult />} />
      </Routes>
    </BrowserRouter>
  </ChakraProvider>
);
