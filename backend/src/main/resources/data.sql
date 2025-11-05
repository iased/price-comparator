-- --------------------------
-- Supermarkets
-- --------------------------
INSERT INTO supermarkets (id, name) VALUES (1, 'Lidl') ON CONFLICT (id) DO NOTHING;
INSERT INTO supermarkets (id, name) VALUES (2, 'Profi') ON CONFLICT (id) DO NOTHING;
INSERT INTO supermarkets (id, name) VALUES (3, 'Kaufland') ON CONFLICT (id) DO NOTHING;

-- --------------------------
-- Products
-- --------------------------
INSERT INTO products (id, name, brand, category, quantity, unit) VALUES
(1, 'lapte', 'Zuzu', 'lactate', 1, 'l'),
(2, 'iaurt grecesc', 'Pilos', 'lactate', 0.4, 'kg'),
(3, 'ouă mărimea M', 'Profi', 'ouă', 10, 'buc'),
(4, 'brânză telemea', 'Kaufland', 'lactate', 0.3, 'kg'),
(5, 'pâine albă', 'Lidl', 'panificație', 500, 'g'),
(6, 'roșii cherry', 'Profi', 'legume și fructe', 250, 'g'),
(7, 'piept de pui', 'Kaufland', 'carne', 1, 'kg'),
(8, 'spaghetti nr.5', 'Barilla', 'paste făinoase', 500, 'g'),
(9, 'zahăr tos', 'Mărgăritar', 'alimente de bază', 1, 'kg'),
(10, 'apă plată', 'Aqua Carpatica', 'băuturi', 2, 'l')
ON CONFLICT (id) DO NOTHING;

-- --------------------------
-- Product prices
-- --------------------------
INSERT INTO product_prices (id, product_id, supermarket_id, price_date, price, currency) VALUES
-- Lidl prices
(1, 1, 1, '2025-11-01', 9.90, 'RON'),
(2, 2, 1, '2025-11-01', 10.50, 'RON'),
(3, 5, 1, '2025-11-01', 4.20, 'RON'),
(4, 9, 1, '2025-11-01', 5.80, 'RON'),
(5, 10, 1, '2025-11-01', 3.30, 'RON'),

-- Profi prices
(6, 1, 2, '2025-11-01', 10.50, 'RON'),
(7, 3, 2, '2025-11-01', 13.20, 'RON'),
(8, 6, 2, '2025-11-01', 9.90, 'RON'),
(9, 9, 2, '2025-11-01', 6.10, 'RON'),
(10, 10, 2, '2025-11-01', 3.00, 'RON'),

-- Kaufland prices
(11, 1, 3, '2025-11-01', 9.60, 'RON'),
(12, 4, 3, '2025-11-01', 14.10, 'RON'),
(13, 7, 3, '2025-11-01', 27.90, 'RON'),
(14, 8, 3, '2025-11-01', 8.90, 'RON'),
(15, 10, 3, '2025-11-01', 3.10, 'RON')
ON CONFLICT (id) DO NOTHING;

-- --------------------------
-- Discounts
-- --------------------------
INSERT INTO discounts (id, product_id, supermarket_id, from_date, to_date, percentage_of_discount) VALUES
-- Lidl
(1, 2, 1, '2025-11-03', '2025-11-09', 10),
(2, 5, 1, '2025-11-10', '2025-11-16', 15),
(3, 9, 1, '2025-11-24', '2025-11-30', 5),

-- Profi
(4, 3, 2, '2025-11-17', '2025-11-23', 8),
(5, 6, 2, '2025-11-24', '2025-11-30', 12),

-- Kaufland
(6, 7, 3, '2025-11-05', '2025-11-11', 10),
(7, 8, 3, '2025-11-12', '2025-11-18', 7)
ON CONFLICT (id) DO NOTHING;

