import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/PersonalDetails.css';

export default function PersonalDetails() {
  const [form, setForm] = useState({
    firstName: '',
    lastName: '',
    location: '',
  });

  const navigate = useNavigate();

  const handleChange = (e) => {
    const { name, value } = e.target;
    setForm(prev => ({ ...prev, [name]: value }));
  };

  const handleSubmit = (e) => {
    e.preventDefault();

    // Basic validation
    if (!form.firstName || !form.lastName || !form.location) {
      alert('Please fill in all fields.');
      return;
    }

    // Navigate to next screen
    navigate('/preferences');
  };

  return (
    <div className="personal-wrapper">
      <form className="personal-card" onSubmit={handleSubmit}>
        <h2>Hi there, let’s personalize your app</h2>
        <p>These details help us tailor your experience.</p>

        <input
          type="text"
          name="firstName"
          placeholder="First Name"
          value={form.firstName}
          onChange={handleChange}
          required
        />

        <input
          type="text"
          name="lastName"
          placeholder="Last Name"
          value={form.lastName}
          onChange={handleChange}
          required
        />

        <input
          type="text"
          name="location"
          placeholder="Place of living"
          value={form.location}
          onChange={handleChange}
          required
        />

        <button type="submit">Confirm</button>
      </form>
    </div>
  );
}
