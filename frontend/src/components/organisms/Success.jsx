import React from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Success.css';

export default function Success() {
  const navigate = useNavigate();

  const handleConfirm = () => {
    // Navigate to dashboard or home
    navigate('/');
  };

  return (
    <div className="success-wrapper">
      <div className="success-card">
        <h2>Subscription added successfully!</h2>
        <p className="success-subtitle">
          Your Netflix subscription is now part of your dashboard.
        </p>

        <div className="details-box">
          <div className="detail-row">
            <span className="label">Name:</span>
            <span className="value">Netflix</span>
          </div>
          <div className="detail-row">
            <span className="label">Next payment date:</span>
            <span className="value">01 Oct 2025</span>
          </div>
          <div className="detail-row">
            <span className="label">Category:</span>
            <span className="value">Entertainment</span>
          </div>
          <div className="detail-row">
            <span className="label">Notifications:</span>
            <span className="value">3 days before</span>
          </div>
        </div>

        <button onClick={handleConfirm}>Confirm</button>
      </div>
    </div>
  );
}
