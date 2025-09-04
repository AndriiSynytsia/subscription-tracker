CREATE TABLE IF NOT EXISTS auth_migrations_marker (
  id SERIAL PRIMARY KEY,
  created_at TIMESTAMPTZ DEFAULT now()
);
