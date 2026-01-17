-- Supermarkets
INSERT INTO supermarkets (name) VALUES ('Lidl')     ON CONFLICT (name) DO NOTHING;
INSERT INTO supermarkets (name) VALUES ('Profi')    ON CONFLICT (name) DO NOTHING;
INSERT INTO supermarkets (name) VALUES ('Kaufland') ON CONFLICT (name) DO NOTHING;

-- Products
INSERT INTO products (name, brand, category, quantity, unit, image_url) VALUES
('lapte', 'Zuzu', 'lactate', 1, 'l', 'https://auchan.vtexassets.com/arquivos/ids/162906-1200-1200?v=637981790362670000&width=1200&height=1200&aspect=true'),
('iaurt grecesc', 'Pilos', 'lactate', 0.4, 'kg', 'https://imgproxy-retcat.assets.schwarz/5V_9w5pWt1YGSAvAldRPQhwy6nA0Ua2wYgfcpGlRlxA/sm:1/w:1278/h:959/cz/M6Ly9wcm9kLWNhd/GFsb2ctbWVkaWEvcm8vMS8zOEYzNTVGQzdBQ0M3ODBGMTQwODFENzZ/DOEI5OTNCMEU1RDVCODk5QzJEQUE3QTlGQjZFRTE1QkJFNUM2N0M1LmpwZw.jpg'),
('ouă mărimea M', 'Profi', 'ouă', 10, 'buc', 'https://nutriwell.ro/wp-content/uploads/2019/07/ce-inseamn%C4%83-codul-de-pe-ou%C4%83-1024x683.jpg'),
('brânză telemea', 'Kaufland', 'lactate', 0.3, 'kg', 'https://www.delabunici.ro/data/articles/7/751/telemea-de-capra-1b.jpg'),
('pâine albă', 'Lidl', 'panificație', 500, 'g', 'https://sp-ao.shortpixel.ai/client/q_glossy+w_1056+to_auto+ret_img/pastry-workshop.com/wp-content/uploads/2017/08/paine-alba-de-casa-min.jpg'),
('roșii cherry', 'Profi', 'legume și fructe', 250, 'g', 'https://froopt.ro//images/7261/conversions/ROSII-CHERRY-PE-CRENGUTA-(3)-large.jpg'),
('piept de pui', 'Kaufland', 'carne', 1, 'kg', 'https://d2j6dbq0eux0bg.cloudfront.net/images/44675013/1930104647.jpg'),
('paste penne', 'Barilla', 'paste făinoase', 500, 'g', 'https://www.the-pasta-project.com/wp-content/uploads/Penne-Pasta-1.jpg'),
('zahăr tos', 'Mărgăritar', 'alimente de bază', 1, 'kg', 'https://s13emagst.akamaized.net/products/41616/41615609/images/res_404bdea4a3c24e6d15e1491910ee5812.jpg'),
('apă plată', 'Aqua Carpatica', 'băuturi', 2, 'l', 'https://auchan.vtexassets.com/arquivos/ids/251147-1200-1200?v=638404767366930000&width=1200&height=1200&aspect=true')
ON CONFLICT (name, brand, category, quantity, unit) DO NOTHING;

WITH
s AS (
  SELECT id, name FROM supermarkets
),
p AS (
  SELECT id, name, brand, category, quantity, unit FROM products
),
price_rows AS (
  -- Lidl
  SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l') AS product_id,
    (SELECT id FROM s WHERE name='Lidl') AS supermarket_id,
    DATE '2025-11-01' AS price_date,
    9.90::numeric AS price,
    'RON'::text AS currency
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-01', 10.50::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-01', 4.20::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-10', 5.10::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-14', 4.90::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-01', 5.80::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-01', 3.30::numeric, 'RON'

  -- Profi
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-11-01', 10.50::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-11-01', 13.20::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-11-01', 9.90::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-11-01', 6.10::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-11-01', 3.00::numeric, 'RON'

  -- Kaufland
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 9.60::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 14.10::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 27.90::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='paste făinoase' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 8.90::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 3.10::numeric, 'RON'
)
INSERT INTO product_prices (product_id, supermarket_id, price_date, price, currency)
SELECT product_id, supermarket_id, price_date, price, currency
FROM price_rows
WHERE product_id IS NOT NULL AND supermarket_id IS NOT NULL
ON CONFLICT (product_id, supermarket_id, price_date) DO NOTHING;

-- Discounts
WITH
s AS (
  SELECT id, name FROM supermarkets
),
p AS (
  SELECT id, name, brand, category, quantity, unit FROM products
),
d AS (
  -- Lidl
  -- November 2025
  SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l') AS product_id,
    (SELECT id FROM s WHERE name='Lidl') AS supermarket_id,
    DATE '2025-11-03' AS from_date,
    DATE '2025-11-09' AS to_date,
    10 AS percentage_of_discount
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-03', DATE '2025-11-09', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-10', DATE '2025-11-16', 15
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-24', DATE '2025-11-30', 5
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-11-24', DATE '2025-11-30', 7

  -- December 2025
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-01', DATE '2025-12-07', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-08', DATE '2025-12-14', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-08', DATE '2025-12-14', 15
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-15', DATE '2025-12-21', 5
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-15', DATE '2025-12-21', 7
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-22', DATE '2025-12-28', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-22', DATE '2025-12-28', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-22', DATE '2025-12-28', 15

  -- January 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-29', DATE '2026-01-04', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-29', DATE '2026-01-04', 7
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2025-12-29', DATE '2026-01-04', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-05', DATE '2026-01-11', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-05', DATE '2026-01-11', 18
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-12', DATE '2026-01-18', 18
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-12', DATE '2026-01-18', 18
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-12', DATE '2026-01-18', 7
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-12', DATE '2026-01-18', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-19', DATE '2026-01-25', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-19', DATE '2026-01-25', 18

  -- February 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', DATE '2026-02-01', 7
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', DATE '2026-02-01', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-02', DATE '2026-02-08', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-02', DATE '2026-02-08', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-09', DATE '2026-02-15', 16
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-09', DATE '2026-02-15', 6
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-16', DATE '2026-02-22', 8

  -- March 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-23', DATE '2026-03-01', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-23', DATE '2026-03-01', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-23', DATE '2026-03-01', 16
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-03-02', DATE '2026-03-08', 13
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-03-02', DATE '2026-03-08', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='pâine albă' AND brand='Lidl' AND category='panificație' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-03-09', DATE '2026-03-15', 20
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-03-09', DATE '2026-03-15', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-03-16', DATE '2026-03-22', 10

  -- Profi
  -- November 2025
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-11-17', DATE '2025-11-23', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-11-24', DATE '2025-11-30', 12

  -- December 2025
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-01', DATE '2025-12-07', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-01', DATE '2025-12-07', 7
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-01', DATE '2025-12-07', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-01', DATE '2025-12-07', 5
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-01', DATE '2025-12-07', 6

  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-08', DATE '2025-12-14', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-08', DATE '2025-12-14', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-08', DATE '2025-12-14', 8

  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-15', DATE '2025-12-21', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-15', DATE '2025-12-21', 6

  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-22', DATE '2025-12-28', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-22', DATE '2025-12-28', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-22', DATE '2025-12-28', 14
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-22', DATE '2025-12-28', 7

  -- January 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-29', DATE '2026-01-04', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-29', DATE '2026-01-04', 13
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-12-29', DATE '2026-01-04', 7
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-05', DATE '2026-01-11', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-05', DATE '2026-01-11', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-12', DATE '2026-01-18', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-12', DATE '2026-01-18', 15
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-12', DATE '2026-01-18', 6
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-19', DATE '2026-01-25', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-19', DATE '2026-01-25', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-19', DATE '2026-01-25', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', DATE '2026-02-01', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', DATE '2026-02-01', 5

  -- February 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-02', DATE '2026-02-08', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-02', DATE '2026-02-08', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-02', DATE '2026-02-08', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-09', DATE '2026-02-15', 13
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-09', DATE '2026-02-15', 6
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-16', DATE '2026-02-22', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-16', DATE '2026-02-22', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-16', DATE '2026-02-22', 14
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-16', DATE '2026-02-22', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-23', DATE '2026-03-01', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-23', DATE '2026-03-01', 7

  -- March 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-03-02', DATE '2026-03-08', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-03-02', DATE '2026-03-08', 15
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-03-02', DATE '2026-03-08', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-03-09', DATE '2026-03-15', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-03-09', DATE '2026-03-15', 6
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='ouă' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-03-16', DATE '2026-03-22', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-03-16', DATE '2026-03-22', 13
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-03-16', DATE '2026-03-22', 8

  -- Kaufland
  -- November 2025
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-05', DATE '2025-11-11', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='paste făinoase' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-12', DATE '2025-11-18', 7
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-19', DATE '2025-11-25', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-19', DATE '2025-11-25', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-19', DATE '2025-11-25', 7
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-26', DATE '2025-12-02', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='paste făinoase' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-26', DATE '2025-12-02', 8

  -- December 2025
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-03', DATE '2025-12-09', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-03', DATE '2025-12-09', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-03', DATE '2025-12-09', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-03', DATE '2025-12-09', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='paste făinoase' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-10', DATE '2025-12-16', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-17', DATE '2025-12-23', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-17', DATE '2025-12-23', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-24', DATE '2025-12-30', 13
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-24', DATE '2025-12-30', 9

  -- January 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-31', DATE '2026-01-06', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='paste făinoase' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-31', DATE '2026-01-06', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-07', DATE '2026-01-13', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-07', DATE '2026-01-13', 14
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-14', DATE '2026-01-20', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-21', DATE '2026-01-27', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-21', DATE '2026-01-27', 13
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='paste făinoase' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-28', DATE '2026-02-03', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-28', DATE '2026-02-03', 8

  -- February 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-04', DATE '2026-02-10', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-04', DATE '2026-02-10', 15
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-11', DATE '2026-02-17', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='paste făinoase' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-11', DATE '2026-02-17', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-18', DATE '2026-02-24', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-25', DATE '2026-03-03', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-25', DATE '2026-03-03', 13

  -- March 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='paste făinoase' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-03-04', DATE '2026-03-10', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-03-04', DATE '2026-03-10', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-03-11', DATE '2026-03-17', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-03-11', DATE '2026-03-17', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='carne' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-03-18', DATE '2026-03-24', 14
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-03-18', DATE '2026-03-24', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='paste făinoase' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-03-25', DATE '2026-03-31', 9
)
INSERT INTO discounts (product_id, supermarket_id, from_date, to_date, percentage_of_discount)
SELECT product_id, supermarket_id, from_date, to_date, percentage_of_discount
FROM d
WHERE product_id IS NOT NULL AND supermarket_id IS NOT NULL
ON CONFLICT (product_id, supermarket_id, from_date, to_date) DO NOTHING;
