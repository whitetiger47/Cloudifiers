import { ROUTES } from "../common/constants";
import React from "react";
import { Navigate, Route, Routes } from "react-router-dom";

function PrivateRoutes() {
  const privatePages = [];

  //Private routes will be defined here
  const renderRoutes = (
    <Routes>
      {privatePages.map((page, index) => {
        return (
          <Route
            exact
            path={page.pageLink}
            element={<page.view />}
            key={index}
          />
        );
      })}
      <Route path={ROUTES.NOT_FOUND} element={<Navigate to={ROUTES.ERROR} />} />
    </Routes>
  );

  return renderRoutes;
}

export default PrivateRoutes;
