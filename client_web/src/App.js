import { Routes, Route, Navigate } from "react-router-dom";
import { lazy, Suspense } from "react";
import './App.css';


const Loading = <div>Loading...</div>;

// page
const Index = lazy(() => import("src/pages/index"));

// routes
const HRMRoutes = lazy(() => import("src/router/HRMRoutes"));

function App() {
  return (
    <div className="App">
      <Suspense fallback={Loading}>
        <Routes>
          <Route path="/" element={<Index />} />
          <Route path="*" element={<Navigate to="/" />} />

          <Route path="/hrm/*" element={<HRMRoutes />} />

          

        </Routes>
      </Suspense>
    </div>
  );
}

export default App;
