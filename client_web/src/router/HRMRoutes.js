import { Routes, Route, Navigate } from "react-router-dom";
import { lazy } from "react";

const Index = lazy(() => import("src/pages/index"));
const Add = lazy(() => import("src/pages/hrm/empl/add"));
const OrganizationChart = lazy(() => import("src/pages/hrm/organizationChart"));

const HomeRoutes = () => (
  <Routes>
    <Route path="/" element={<Index />} />
    <Route path="/empl/add" element={<Add />} />
    <Route path="/organizationChart" element={<OrganizationChart />} />




    <Route path="*" element={<Navigate to="/" />} />
  </Routes>
);

export default HomeRoutes;
