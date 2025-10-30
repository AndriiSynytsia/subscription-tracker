import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/NotificationSettings.css';

export default function NotificationSettings() {
  const [enabled, setEnabled] = useState(true);
  const [selectedTime, setSelectedTime] = useState('3 days');
  const [customTime, setCustomTime] = useState('');
  const navigate = useNavigate();

  const timingOptions = ['1 day', '3 days', '7 days', 'Custom'];

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!enabled) {
      alert('Notifications are off. You may miss payment alerts.');
    }

    // Logic to store settings (optional)

    navigate('/verify');
  };

  return (
    <div className="notify-wrapper">
      <form className="notify-card" onSubmit={handleSubmit}>
        <h2>Don’t miss a payment</h2>
        <p className="notify-subtitle">
          Heads up, Alex! <br />
          Your Netflix payment of <strong>$9.99</strong> is coming in <strong>3 days</strong> (01 Oct). Don’t let it surprise you!
        </p>

        <div className="notify-toggle">
          <label>
            <input
              type="checkbox"
              checked={enabled}
              onChange={() => setEnabled(!enabled)}
            />
            Turn On Notification
          </label>
        </div>

        <div className="timing-options">
          {timingOptions.map((opt) => (
            <div
              key={opt}
              className={`timing-option ${selectedTime === opt ? 'selected' : ''}`}
              onClick={() => setSelectedTime(opt)}
            >
              {opt}
            </div>
          ))}
        </div>

        {selectedTime === 'Custom' && (
          <input
            type="number"
            min="1"
            placeholder="Enter custom days"
            className="custom-input"
            value={customTime}
            onChange={(e) => setCustomTime(e.target.value)}
            required
          />
        )}

        <button type="submit">Confirm</button>
      </form>
    </div>
  );
}
