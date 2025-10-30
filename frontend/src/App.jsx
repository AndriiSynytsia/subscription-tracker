import React from "react";
import { BrowserRouter as Router, Routes, Route } from "react-router-dom";
import Welcome from "./components/Welcome";
import Registration from "./components/Registration";
import PersonalDetails from "./components/PersonalDetails";
import Preferences from "./components/Preferences";
import NotificationSettings from "./components/NotificationSettings";
import Verification from "./components/Verification";
import Success from "./components/Success";

function App() {
  return (
    <Router>
      <Routes>
        <Route path="/" element={<Welcome />} />
        <Route path="/register" element={<Registration />} />
        <Route path="/personal" element={<PersonalDetails />} />
        <Route path="/preferences" element={<Preferences />} />
        <Route path="/notifications" element={<NotificationSettings />} />
        <Route path="/verify" element={<Verification />} />
        <Route path="/success" element={<Success />} />
      </Routes>
    </Router>
  );
}

export default App;
