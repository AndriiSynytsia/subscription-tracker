import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import '../styles/Preferences.css';

const categories = [
  'News',
  'Travel',
  'Entertainment',
  'Work',
  'Finance',
  'Health',
  'Learning',
  'Gaming',
];

export default function Preferences() {
  const [budget, setBudget] = useState('');
  const [selectedCategories, setSelectedCategories] = useState([]);
  const navigate = useNavigate();

  const toggleCategory = (cat) => {
    setSelectedCategories((prev) =>
      prev.includes(cat) ? prev.filter((c) => c !== cat) : [...prev, cat]
    );
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    if (!budget || selectedCategories.length === 0) {
      alert('Please enter a budget and select at least one category.');
      return;
    }

    // Go to next page
    navigate('/notifications');
  };

  return (
    <div className="prefs-wrapper">
      <form className="prefs-card" onSubmit={handleSubmit}>
        <h2>Help us understand your habits</h2>

        <label className="prefs-label">What’s your usual monthly budget for subscriptions?</label>
        <input
          type="number"
          min="0"
          placeholder="200.00"
          value={budget}
          onChange={(e) => setBudget(e.target.value)}
        />

        <label className="prefs-label">What do you usually subscribe to?</label>
        <div className="category-grid">
          {categories.map((cat) => (
            <div
              key={cat}
              className={`category-item ${selectedCategories.includes(cat) ? 'selected' : ''}`}
              onClick={() => toggleCategory(cat)}
            >
              {cat}
            </div>
          ))}
        </div>

        <button type="submit">Confirm</button>
      </form>
    </div>
  );
}
