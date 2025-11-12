-- Supermarkets
INSERT INTO supermarkets (name) VALUES ('Lidl') ON CONFLICT (name) DO NOTHING;
INSERT INTO supermarkets (name) VALUES ('Profi') ON CONFLICT (name) DO NOTHING;
INSERT INTO supermarkets (name) VALUES ('Kaufland') ON CONFLICT (name) DO NOTHING;

-- Products
INSERT INTO products (name, brand, category, quantity, unit) VALUES
('lapte', 'Zuzu', 'lactate', 1, 'l'),
('iaurt grecesc', 'Pilos', 'lactate', 0.4, 'kg'),
('ouă mărimea M', 'Profi', 'ouă', 10, 'buc'),
('brânză telemea', 'Kaufland', 'lactate', 0.3, 'kg'),
('pâine albă', 'Lidl', 'panificație', 500, 'g'),
('roșii cherry', 'Profi', 'legume și fructe', 250, 'g'),
('piept de pui', 'Kaufland', 'carne', 1, 'kg'),
('spaghetti nr.5', 'Barilla', 'paste făinoase', 500, 'g'),
('zahăr tos', 'Mărgăritar', 'alimente de bază', 1, 'kg'),
('apă plată', 'Aqua Carpatica', 'băuturi', 2, 'l')
ON CONFLICT (name, brand, category, quantity, unit) DO NOTHING;

-- Product prices
INSERT INTO product_prices (product_id, supermarket_id, price_date, price, currency) VALUES
-- Lidl
(1, 1, '2025-11-01', 9.90, 'RON'),
(2, 1, '2025-11-01', 10.50, 'RON'),
(5, 1, '2025-11-01', 4.20, 'RON'),
(5, 1, '2025-11-10', 5.10, 'RON'),
(5, 1, '2025-11-14', 4.90, 'RON'),
(9, 1, '2025-11-01', 5.80, 'RON'),
(10, 1, '2025-11-01', 3.30, 'RON'),

-- Profi
(1, 2, '2025-11-01', 10.50, 'RON'),
(3, 2, '2025-11-01', 13.20, 'RON'),
(6, 2, '2025-11-01', 9.90, 'RON'),
(9, 2, '2025-11-01', 6.10, 'RON'),
(10, 2, '2025-11-01', 3.00, 'RON'),

-- Kaufland
(1, 3, '2025-11-01', 9.60, 'RON'),
(4, 3, '2025-11-01', 14.10, 'RON'),
(7, 3, '2025-11-01', 27.90, 'RON'),
(8, 3, '2025-11-01', 8.90, 'RON'),
(10, 3, '2025-11-01', 3.10, 'RON')
ON CONFLICT (product_id, supermarket_id, price_date) DO NOTHING;

-- Discounts
INSERT INTO discounts (product_id, supermarket_id, from_date, to_date, percentage_of_discount) VALUES
-- Lidl
(2, 1, '2025-11-03', '2025-11-09', 10),
(5, 1, '2025-11-10', '2025-11-16', 15),
(9, 1, '2025-11-24', '2025-11-30', 5),

-- Profi
(3, 2, '2025-11-17', '2025-11-23', 8),
(6, 2, '2025-11-24', '2025-11-30', 12),

-- Kaufland
(7, 3, '2025-11-05', '2025-11-11', 10),
(8, 3, '2025-11-12', '2025-11-18', 7)
ON CONFLICT (product_id, supermarket_id, from_date, to_date) DO NOTHING;

