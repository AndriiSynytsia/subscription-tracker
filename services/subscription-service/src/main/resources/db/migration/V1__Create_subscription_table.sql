CREATE TABLE subscriptions (
    id BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    merchant_name VARCHAR2(255) NOT NULL,
    price DECIMAL(10, 2) NOT NULL CHECK (price > 0),
    currency VARCHAR2(3) NOT NULL DEFAULT 'USD',
    billing_cycle VARCHAR2(50) NOT NULL CHECK (billing_cycle IN ('MONTHLY', 'YEARLY', 'WEEKLY', 'DAILY')),
    next_renewal_date DATE NOT NULL,
    notification_interval INT NOT NULL CHECK (notification_interval >= 0 AND notification_interval <= 365),
    status VARCHAR2(50) NOT NULL DEFAULT 'ACTIVE' CHECK (status IN ('ACTIVE', 'INACTIVE', 'PENDING', 'CANCELLED', 'EXPIRED')),
    payment_method VARCHAR2(50) NOT NULL CHECK (payment_method IN ('CREDIT_CARD', 'DEBIT_CARD', 'PAYPAL', 'APPLE_PAY', 'BANK_TRANSFER')),
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT uq_subscriptions_user_merchant UNIQUE (user_id, merchant_name);
);

CREATE INDEX idx_subscriptions_user_merchant ON subscriptions(user_id, merchant_name);
CREATE INDEX idx_subscriptions_next_renewal_date ON subscriptions(next_renewal_date);
