import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Welcome.css';

export default function Welcome() {
  const navigate = useNavigate();

  return (
    <div className="welcome-wrapper">
      <div className="welcome-card">
        <h1 className="welcome-title">Take control of your subscriptions.</h1>
        <p className="welcome-subtitle">Track all your subscriptions in one place</p>
        <button className="welcome-button" onClick={() => navigate('/register')}>
          Get Started
        </button>
      </div>
    </div>
  );
}
