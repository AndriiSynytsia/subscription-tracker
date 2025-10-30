import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Verification.css';

export default function Verification() {
  const [code, setCode] = useState(['', '', '', '']);
  const navigate = useNavigate();

  const handleChange = (index, value) => {
    if (!/^\d?$/.test(value)) return;
    const updated = [...code];
    updated[index] = value;
    setCode(updated);

    // Auto-focus to next input
    if (value && index < 3) {
      const nextInput = document.getElementById(`code-${index + 1}`);
      if (nextInput) nextInput.focus();
    }
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const fullCode = code.join('');
    if (fullCode.length !== 4) {
      alert('Please enter all 4 digits.');
      return;
    }

    // You can send fullCode to backend here

    navigate('/success');
  };

  return (
    <div className="verify-wrapper">
      <form className="verify-card" onSubmit={handleSubmit}>
        <h2>Enter your verification code</h2>
        <p className="verify-subtitle">
          We’ve sent a 4-digit code to <strong>hello@gmail.com</strong>.<br />
          Enter the code below to continue.
        </p>

        <div className="code-inputs">
          {code.map((val, index) => (
            <input
              key={index}
              id={`code-${index}`}
              type="text"
              maxLength="1"
              value={val}
              onChange={(e) => handleChange(index, e.target.value)}
            />
          ))}
        </div>

        <button type="submit">Submit</button>
        <p className="resend-link">Didn’t get it? <span>Resend code</span></p>
      </form>
    </div>
  );
}
