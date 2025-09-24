CREATE TABLE IF NOT EXISTS api_gateway_migrations_marker (
  id SERIAL PRIMARY KEY,
  created_at TIMESTAMPTZ DEFAULT now()
);
