import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Registration.css';

export default function Registration() {
  const [form, setForm] = useState({
    email: '',
    password: '',
    confirmPassword: '',
    agree: false,
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setForm({ ...form, [name]: type === 'checkbox' ? checked : value });
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!form.agree) {
      alert("You must agree to the terms and privacy policy.");
      return;
    }
    if (form.password !== form.confirmPassword) {
      alert("Passwords do not match.");
      return;
    }

    // Proceed to next step
    navigate('/personal');
  };

  return (
    <div className="register-wrapper">
      <form className="register-card" onSubmit={handleSubmit}>
        <h2>Create your Subsy space.</h2>
        <p>Enter your email and set a secure password</p>

        <input
          type="email"
          name="email"
          placeholder="Email Address"
          value={form.email}
          onChange={handleChange}
          required
        />

        <input
          type="password"
          name="password"
          placeholder="Password"
          value={form.password}
          onChange={handleChange}
          required
        />

        <input
          type="password"
          name="confirmPassword"
          placeholder="Confirm Password"
          value={form.confirmPassword}
          onChange={handleChange}
          required
        />

        <label className="checkbox-container">
          <input
            type="checkbox"
            name="agree"
            checked={form.agree}
            onChange={handleChange}
          />
          I agree to Terms & Privacy Policy
        </label>

        <button type="submit">Confirm</button>

        <p className="redirect-link">
          Already have an account? <span onClick={() => navigate('/login')}>Log in.</span>
        </p>
      </form>
    </div>
  );
}
