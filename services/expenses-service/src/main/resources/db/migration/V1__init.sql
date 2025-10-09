CREATE TABLE IF NOT EXISTS expense_migrations_marker (
  id SERIAL PRIMARY KEY,
  created_at TIMESTAMPTZ DEFAULT now()
);
