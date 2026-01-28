-- Supermarkets
INSERT INTO supermarkets (name) VALUES ('Lidl')     ON CONFLICT (name) DO NOTHING;
INSERT INTO supermarkets (name) VALUES ('Profi')    ON CONFLICT (name) DO NOTHING;
INSERT INTO supermarkets (name) VALUES ('Kaufland') ON CONFLICT (name) DO NOTHING;

-- Products
INSERT INTO products (name, brand, category, quantity, unit, image_url) VALUES
('lapte 3,5% grăsime', 'Zuzu', 'lactate', 1, 'l', 'https://auchan.vtexassets.com/arquivos/ids/162906-1200-1200?v=637981790362670000&width=1200&height=1200&aspect=true'),
('iaurt grecesc', 'Pilos', 'lactate', 0.4, 'kg', 'https://imgproxy-retcat.assets.schwarz/5V_9w5pWt1YGSAvAldRPQhwy6nA0Ua2wYgfcpGlRlxA/sm:1/w:1278/h:959/cz/M6Ly9wcm9kLWNhd/GFsb2ctbWVkaWEvcm8vMS8zOEYzNTVGQzdBQ0M3ODBGMTQwODFENzZ/DOEI5OTNCMEU1RDVCODk5QzJEQUE3QTlGQjZFRTE1QkJFNUM2N0M1LmpwZw.jpg'),
('ouă mărimea M', 'Profi', 'lactate', 10, 'buc', 'https://nutriwell.ro/wp-content/uploads/2019/07/ce-inseamn%C4%83-codul-de-pe-ou%C4%83-1024x683.jpg'),
('brânză telemea', 'Kaufland', 'lactate', 0.3, 'kg', 'https://www.delabunici.ro/data/articles/7/751/telemea-de-capra-1b.jpg'),
('pâine albă', 'Lidl', 'panificație', 500, 'g', 'https://sp-ao.shortpixel.ai/client/q_glossy+w_1056+to_auto+ret_img/pastry-workshop.com/wp-content/uploads/2017/08/paine-alba-de-casa-min.jpg'),
('roșii cherry', 'Profi', 'legume și fructe', 250, 'g', 'https://froopt.ro//images/7261/conversions/ROSII-CHERRY-PE-CRENGUTA-(3)-large.jpg'),
('piept de pui', 'Kaufland', 'alimente de bază', 1, 'kg', 'https://d2j6dbq0eux0bg.cloudfront.net/images/44675013/1930104647.jpg'),
('paste penne', 'Barilla', 'alimente de bază', 500, 'g', 'https://www.the-pasta-project.com/wp-content/uploads/Penne-Pasta-1.jpg'),
('zahăr tos', 'Mărgăritar', 'alimente de bază', 1, 'kg', 'https://s13emagst.akamaized.net/products/41616/41615609/images/res_404bdea4a3c24e6d15e1491910ee5812.jpg'),
('apă plată', 'Aqua Carpatica', 'băuturi', 2, 'l', 'https://auchan.vtexassets.com/arquivos/ids/251147-1200-1200?v=638404767366930000&width=1200&height=1200&aspect=true'),
('lapte UHT 3,5% grăsime', 'Pilos', 'lactate', 1, 'l', 'https://imgproxy-retcat.assets.schwarz/RATTLZUxW7wQemPiLmwggAPjpxaNJodAcs-68NSqD2o/sm:1/w:1278/h:959/cz/M6Ly9wcm9kLWNhd/GFsb2ctbWVkaWEvcm8vMS8yMDNCNzZDQTA0RkMyNDI2OURBNjZBNEM/zMDI0RkQ4QkM5OUYwREU5Nzk0QkFBNkZDRjFDMEI3QkJDRTVFQTFBLmpwZw.jpg'),
('kefir', 'Zuzu', 'lactate', 330, 'ml', 'https://static.mega-image.ro/medias/sys_master/h4f/h36/9418036576286.jpg?buildNumber=08d11195bb29f2655d397cda289b6e82a315cec9d7431a8e7f7a9e6f44ec9da0&imwidth=320&imdensity=2'),
('smântână 20% grăsime', 'Olympus', 'lactate', 200, 'g', 'https://auchan.vtexassets.com/arquivos/ids/275267-1200-1200?v=638603494741200000&width=1200&height=1200&aspect=true'),
('unt 65% grăsime', 'Pilos', 'lactate', 200, 'g', 'https://imgproxy-retcat.assets.schwarz/JRNGx34JucytuXSeTDpwtWTwL5kVj35Tg-XbtGe4Ay0/sm:1/w:1278/h:959/cz/M6Ly9wcm9kLWNhd/GFsb2ctbWVkaWEvcm8vMS81QUU1NzVFMjc1OTJERUM4MURGRjk0Qjk/wMkJFQjE4MTk4MDUxRjMzQ0I3OTkzQUFDNzlGQUVDNjk0MDcwMDBGLmpwZw.jpg'),
('ton bucăți în suc propriu', 'Rio Mare', 'alimente de bază', 160, 'g', 'https://frentzy.ro/wp-content/uploads/2023/02/res_620bf7a1a63ac2bd5bf258ad00f569e8.jpg'),
('banane', 'Generic', 'legume și fructe', 1, 'kg', 'https://mediacdn.libertatea.ro/unsafe/975x547/smart/filters:format(webp):contrast(8):quality(75)/https://static4.libertatea.ro/wp-content/uploads/2021/03/banane.jpg'),
('portocale', 'Generic', 'legume și fructe', 1, 'kg', 'https://upload.wikimedia.org/wikipedia/commons/4/43/Ambersweet_oranges.jpg'),
('cartofi albi', 'Generic', 'legume și fructe', 1, 'kg', 'https://fresh-fruits.ro/wp-content/uploads/2019/02/cartofi-albi-1.jpg'),
('morcovi', 'Generic', 'legume și fructe', 1, 'kg', 'https://static.mega-image.ro/site/binaries/_ht_1615453654728/lg/content/gallery/image-components/mega-image-romania-ro/articole-inspiratie/morcovii/morcovii_image001-1.jpg?imwidth=690'),
('castraveți', 'Generic', 'legume și fructe', 1, 'kg', 'https://www.catena.ro/assets/uploads/files/images/Castraveti.jpg'),
('orez bob lung', 'Scotti', 'alimente de bază', 1, 'kg', 'https://static.mega-image.ro/medias/sys_master/h3b/h3f/9416939110430.jpg?buildNumber=08d11195bb29f2655d397cda289b6e82a315cec9d7431a8e7f7a9e6f44ec9da0&imwidth=320&imdensity=2'),
('orez basmati', 'K-Classic', 'alimente de bază', 500, 'g', 'https://imageproxy.wolt.com/assets/68c042c35723c40c40d05321?w=960'),
('ulei de floarea soarelui', 'Unisol', 'alimente de bază', 1, 'l', 'https://s13emagst.akamaized.net/products/29822/29821662/images/res_1a715e97714c295608632147ae498fc4.jpg'),
('ketchup', 'Heinz', 'sosuri', 500, 'g', 'https://www.kurt.ro/storage/photos/000/053/53219/heinz-ketchup-500-ml-7006.jpg'),
('muștar', 'Olympia', 'sosuri', 300, 'g', 'https://auchan.vtexassets.com/arquivos/ids/199489-1200-1200?v=638011770220330000&width=1200&height=1200&aspect=true'),
('maioneză', 'Hellmann''s', 'sosuri', 400, 'g', 'https://mcgrocer.com/cdn/shop/files/hellmann-s-real-mayonnaise-400g-41783090774254.jpg?v=1741687970'),
('ciocolată cu lapte', 'Fin Carré', 'dulciuri', 100, 'g', 'https://imgproxy-retcat.assets.schwarz/FSgBDNxkxsIl_NkwuXw9rCLjqrb95q5MSuWvSG9lNlQ/sm:1/w:1500/h:1125/cz/M6Ly9wcm9kLWNhd/GFsb2ctbWVkaWEvcm8vMS81Qjk4RUYzNEUxMThCQTBEMkMyRTcwNjA/0RTdBMDEwMzA1REI4NjQ1QzVGQzk1MDVEODI5NTA4REEzNUJDMzRDLmpwZw.jpg'),
('napolitane', 'Joe', 'dulciuri', 180, 'g', 'https://static.mega-image.ro/medias/sys_master/h72/h3d/9416500117534.jpg?buildNumber=08d11195bb29f2655d397cda289b6e82a315cec9d7431a8e7f7a9e6f44ec9da0&imwidth=320&imdensity=2'),
('chips cu sare', 'Chio', 'snacks', 140, 'g', 'https://auchan.vtexassets.com/arquivos/ids/165554-1200-1200?v=637985901091030000&width=1200&height=1200&aspect=true'),
('suc portocale', 'Granini', 'băuturi', 1, 'l', 'https://auchan.vtexassets.com/arquivos/ids/251786-1200-1200?v=638404769635230000&width=1200&height=1200&aspect=true'),
('cola', 'Coca-Cola', 'băuturi', 2, 'l', 'https://megakfood.com/cdn/shop/products/049000050103_4da239f3-b1cb-48f0-97ee-4ed2a67eea44.jpg?v=1659677044'),
('fulgi de ovăz', 'Crownfield', 'alimente de bază', 500, 'g', 'https://www.mustakshif.com/public/uploads/products/crownfield-fine-oat-flakes_4056489195917_Mustakshif.jpg'),
('cereale cu ciocolată și vanilie', 'Nesquik', 'alimente de bază', 375, 'g', 'https://static.mega-image.ro/medias/sys_master/products/h92/h13/9349523275806.jpg?buildNumber=08d11195bb29f2655d397cda289b6e82a315cec9d7431a8e7f7a9e6f44ec9da0&imwidth=320&imdensity=2'),
('ceai verde cu mentă', 'Lipton', 'băuturi', 20, 'buc', 'https://p1.akcdn.net/full/1224370048.lipton-ceai-verde-cu-menta-piramide-20-plicuri.jpg'),
('cafea măcinată', 'Jacobs', 'băuturi', 500, 'g', 'https://auchan.vtexassets.com/arquivos/ids/285671-1200-1200?v=638723769925400000&width=1200&height=1200&aspect=true'),
('hrană umedă pisici plic somon', 'Brit Care', 'petshop', 85, 'g', 'https://cdn.dedeman.ro/media/catalog/product/7/0/7042256.jpg?optimize=low&fit=bounds&height=700&width=700&canvas=700:700'),
('vin roșu demisec', 'Jidvei', 'băuturi alcoolice', 0.75, 'l', 'https://auchan.vtexassets.com/arquivos/ids/289814-1200-1200?v=638792782658400000&width=1200&height=1200&aspect=true'),
('săpun lichid antibacterian', 'Cien', 'igienă', 0.5, 'l', 'https://archivana.com/pics/fa/5f/fa5fc152a31592159d20ba1bf577c4a3372ee3a5.jpg')
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
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l') AS product_id,
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

  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte UHT 3,5% grăsime' AND brand='Pilos' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 8.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='unt 65% grăsime' AND brand='Pilos' AND category='lactate' AND quantity=200 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 9.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='fulgi de ovăz' AND brand='Crownfield' AND category='alimente de bază' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 7.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ciocolată cu lapte' AND brand='Fin Carré' AND category='dulciuri' AND quantity=100 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 4.29::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='banane' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 7.29::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cartofi albi' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 3.59::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='portocale' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 5.79::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cafea măcinată' AND brand='Jacobs' AND category='băuturi' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 31.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='orez bob lung' AND brand='Scotti' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 9.29::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ulei de floarea soarelui' AND brand='Unisol' AND category='alimente de bază' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 9.69::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ketchup' AND brand='Heinz' AND category='sosuri' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 13.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='muștar' AND brand='Olympia' AND category='sosuri' AND quantity=300 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 6.79::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='maioneză' AND brand='Hellmann''s' AND category='sosuri' AND quantity=400 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 12.89::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='napolitane' AND brand='Joe' AND category='dulciuri' AND quantity=180 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 5.69::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='chips cu sare' AND brand='Chio' AND category='snacks' AND quantity=140 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 7.89::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='suc portocale' AND brand='Granini' AND category='băuturi' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 10.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cola' AND brand='Coca-Cola' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 8.09::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cereale cu ciocolată și vanilie' AND brand='Nesquik' AND category='alimente de bază' AND quantity=375 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 16.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ceai verde cu mentă' AND brand='Lipton' AND category='băuturi' AND quantity=20 AND unit='buc'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 10.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='hrană umedă pisici plic somon' AND brand='Brit Care' AND category='petshop' AND quantity=85 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 6.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='vin roșu demisec' AND brand='Jidvei' AND category='băuturi alcoolice' AND quantity=0.75 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 25.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='săpun lichid antibacterian' AND brand='Cien' AND category='igienă' AND quantity=0.5 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', 7.49::numeric, 'RON'

  -- Profi
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2025-11-01', 10.50::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='lactate' AND quantity=10 AND unit='buc'),
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

  UNION ALL SELECT
    (SELECT id FROM p WHERE name='kefir' AND brand='Zuzu' AND category='lactate' AND quantity=330 AND unit='ml'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 4.19::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='smântână 20% grăsime' AND brand='Olympus' AND category='lactate' AND quantity=200 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 4.89::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='banane' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 6.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cartofi albi' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 3.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='morcovi' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 3.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='castraveți' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 5.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='portocale' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 5.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ton bucăți în suc propriu' AND brand='Rio Mare' AND category='alimente de bază' AND quantity=160 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 9.79::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ulei de floarea soarelui' AND brand='Unisol' AND category='alimente de bază' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 9.89::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ketchup' AND brand='Heinz' AND category='sosuri' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 13.59::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='muștar' AND brand='Olympia' AND category='sosuri' AND quantity=300 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 6.89::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='maioneză' AND brand='Hellmann''s' AND category='sosuri' AND quantity=400 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 12.69::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='napolitane' AND brand='Joe' AND category='dulciuri' AND quantity=180 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 6.19::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='chips cu sare' AND brand='Chio' AND category='snacks' AND quantity=140 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 7.79::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='suc portocale' AND brand='Granini' AND category='băuturi' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 10.29::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cola' AND brand='Coca-Cola' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 8.29::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cereale cu ciocolată și vanilie' AND brand='Nesquik' AND category='alimente de bază' AND quantity=375 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 17.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ceai verde cu mentă' AND brand='Lipton' AND category='băuturi' AND quantity=20 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 11.19::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='hrană umedă pisici plic somon' AND brand='Brit Care' AND category='petshop' AND quantity=85 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 6.79::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='vin roșu demisec' AND brand='Jidvei' AND category='băuturi alcoolice' AND quantity=0.75 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', 26.49::numeric, 'RON'

  -- Kaufland
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 9.60::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 14.10::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 27.90::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='alimente de bază' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 8.90::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-11-01', 3.10::numeric, 'RON'

  UNION ALL SELECT
    (SELECT id FROM p WHERE name='orez basmati' AND brand='K-Classic' AND category='alimente de bază' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 6.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='orez bob lung' AND brand='Scotti' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 8.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ulei de floarea soarelui' AND brand='Unisol' AND category='alimente de bază' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 9.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ketchup' AND brand='Heinz' AND category='sosuri' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 14.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='muștar' AND brand='Olympia' AND category='sosuri' AND quantity=300 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 6.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='maioneză' AND brand='Hellmann''s' AND category='sosuri' AND quantity=400 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 13.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='napolitane' AND brand='Joe' AND category='dulciuri' AND quantity=180 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 5.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='chips cu sare' AND brand='Chio' AND category='snacks' AND quantity=140 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 7.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='suc portocale' AND brand='Granini' AND category='băuturi' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 9.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cola' AND brand='Coca-Cola' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 8.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cereale cu ciocolată și vanilie' AND brand='Nesquik' AND category='alimente de bază' AND quantity=375 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 16.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ceai verde cu mentă' AND brand='Lipton' AND category='băuturi' AND quantity=20 AND unit='buc'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 11.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cafea măcinată' AND brand='Jacobs' AND category='băuturi' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 29.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ton bucăți în suc propriu' AND brand='Rio Mare' AND category='alimente de bază' AND quantity=160 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 9.49::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='hrană umedă pisici plic somon' AND brand='Brit Care' AND category='petshop' AND quantity=85 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 6.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='vin roșu demisec' AND brand='Jidvei' AND category='băuturi alcoolice' AND quantity=0.75 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 24.99::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='banane' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 6.79::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cartofi albi' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 3.39::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='portocale' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 6.19::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='kefir' AND brand='Zuzu' AND category='lactate' AND quantity=330 AND unit='ml'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 4.39::numeric, 'RON'
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='smântână 20% grăsime' AND brand='Olympus' AND category='lactate' AND quantity=200 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', 4.69::numeric, 'RON'
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
  -- January 2026
  SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l') AS product_id,
    (SELECT id FROM s WHERE name='Lidl') AS supermarket_id,
    DATE '2025-12-29' AS from_date,
    DATE '2026-01-04' AS to_date,
    12 AS percentage_of_discount

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
    (SELECT id FROM p WHERE name='lapte UHT 3,5% grăsime' AND brand='Pilos' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-05', DATE '2026-01-11', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='unt 65% grăsime' AND brand='Pilos' AND category='lactate' AND quantity=200 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-05', DATE '2026-01-11', 12
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
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='fulgi de ovăz' AND brand='Crownfield' AND category='alimente de bază' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-19', DATE '2026-01-25', 18
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ciocolată cu lapte' AND brand='Fin Carré' AND category='dulciuri' AND quantity=100 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-19', DATE '2026-01-25', 20

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
    (SELECT id FROM p WHERE name='napolitane' AND brand='Joe' AND category='dulciuri' AND quantity=180 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-01-26', DATE '2026-02-02', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-02', DATE '2026-02-08', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='iaurt grecesc' AND brand='Pilos' AND category='lactate' AND quantity=0.4 AND unit='kg'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-02', DATE '2026-02-08', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte UHT 3,5% grăsime' AND brand='Pilos' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-02', DATE '2026-02-08', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='unt 65% grăsime' AND brand='Pilos' AND category='lactate' AND quantity=200 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-02', DATE '2026-02-08', 13
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
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='fulgi de ovăz' AND brand='Crownfield' AND category='alimente de bază' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Lidl'),
    DATE '2026-02-16', DATE '2026-02-22', 17

  -- Profi
  -- January 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
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
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='lactate' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-05', DATE '2026-01-11', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-05', DATE '2026-01-11', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='kefir' AND brand='Zuzu' AND category='lactate' AND quantity=330 AND unit='ml'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-05', DATE '2026-01-11', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='smântână 20% grăsime' AND brand='Olympus' AND category='lactate' AND quantity=200 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-05', DATE '2026-01-11', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
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
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-19', DATE '2026-01-25', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='lactate' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-19', DATE '2026-01-25', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-19', DATE '2026-01-25', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='banane' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-19', DATE '2026-01-25', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cartofi albi' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-19', DATE '2026-01-25', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='morcovi' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-19', DATE '2026-01-25', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', DATE '2026-02-01', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', DATE '2026-02-01', 5
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cereale cu ciocolată și vanilie' AND brand='Nesquik' AND category='alimente de bază' AND quantity=375 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-01-26', DATE '2026-02-02', 11

  -- February 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-02', DATE '2026-02-08', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='lactate' AND quantity=10 AND unit='buc'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-02', DATE '2026-02-08', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-02', DATE '2026-02-08', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='portocale' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-02', DATE '2026-02-08', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='castraveți' AND brand='Generic' AND category='legume și fructe' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-02', DATE '2026-02-08', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='roșii cherry' AND brand='Profi' AND category='legume și fructe' AND quantity=250 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-09', DATE '2026-02-15', 13
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-09', DATE '2026-02-15', 6
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-16', DATE '2026-02-22', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ouă mărimea M' AND brand='Profi' AND category='lactate' AND quantity=10 AND unit='buc'),
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
    (SELECT id FROM p WHERE name='kefir' AND brand='Zuzu' AND category='lactate' AND quantity=330 AND unit='ml'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-16', DATE '2026-02-22', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='smântână 20% grăsime' AND brand='Olympus' AND category='lactate' AND quantity=200 AND unit='g'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-16', DATE '2026-02-22', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-23', DATE '2026-03-01', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='zahăr tos' AND brand='Mărgăritar' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Profi'),
    DATE '2026-02-23', DATE '2026-03-01', 7

  -- Kaufland
  -- January 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-31', DATE '2026-01-06', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='alimente de bază' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2025-12-31', DATE '2026-01-06', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-07', DATE '2026-01-13', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-07', DATE '2026-01-13', 14
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='orez basmati' AND brand='K-Classic' AND category='alimente de bază' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-07', DATE '2026-01-13', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ulei de floarea soarelui' AND brand='Unisol' AND category='alimente de bază' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-07', DATE '2026-01-13', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ton bucăți în suc propriu' AND brand='Rio Mare' AND category='alimente de bază' AND quantity=160 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-07', DATE '2026-01-13', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-14', DATE '2026-01-20', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-21', DATE '2026-01-27', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-21', DATE '2026-01-27', 13
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ketchup' AND brand='Heinz' AND category='sosuri' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-21', DATE '2026-01-27', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='maioneză' AND brand='Hellmann''s' AND category='sosuri' AND quantity=400 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-21', DATE '2026-01-27', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cola' AND brand='Coca-Cola' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-21', DATE '2026-01-27', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ketchup' AND brand='Heinz' AND category='sosuri' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', DATE '2026-02-02', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cola' AND brand='Coca-Cola' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', DATE '2026-02-02', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='hrană umedă pisici plic somon' AND brand='Brit Care' AND category='petshop' AND quantity=85 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-26', DATE '2026-02-02', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='alimente de bază' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-28', DATE '2026-02-03', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-01-28', DATE '2026-02-03', 8

  -- February 2026
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-04', DATE '2026-02-10', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-04', DATE '2026-02-10', 15
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cafea măcinată' AND brand='Jacobs' AND category='băuturi' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-04', DATE '2026-02-10', 11
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='cereale cu ciocolată și vanilie' AND brand='Nesquik' AND category='alimente de bază' AND quantity=375 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-04', DATE '2026-02-10', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='suc portocale' AND brand='Granini' AND category='băuturi' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-04', DATE '2026-02-10', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='chips cu sare' AND brand='Chio' AND category='snacks' AND quantity=140 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-04', DATE '2026-02-10', 13
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='apă plată' AND brand='Aqua Carpatica' AND category='băuturi' AND quantity=2 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-11', DATE '2026-02-17', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='paste penne' AND brand='Barilla' AND category='alimente de bază' AND quantity=500 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-11', DATE '2026-02-17', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='brânză telemea' AND brand='Kaufland' AND category='lactate' AND quantity=0.3 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-18', DATE '2026-02-24', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='orez bob lung' AND brand='Scotti' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-18', DATE '2026-02-24', 8
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='muștar' AND brand='Olympia' AND category='sosuri' AND quantity=300 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-18', DATE '2026-02-24', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='ceai verde cu mentă' AND brand='Lipton' AND category='băuturi' AND quantity=20 AND unit='buc'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-18', DATE '2026-02-24', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='hrană umedă pisici plic somon' AND brand='Brit Care' AND category='petshop' AND quantity=85 AND unit='g'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-18', DATE '2026-02-24', 12
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='vin roșu demisec' AND brand='Jidvei' AND category='băuturi alcoolice' AND quantity=0.75 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-18', DATE '2026-02-24', 9
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='lapte 3,5% grăsime' AND brand='Zuzu' AND category='lactate' AND quantity=1 AND unit='l'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-25', DATE '2026-03-03', 10
  UNION ALL SELECT
    (SELECT id FROM p WHERE name='piept de pui' AND brand='Kaufland' AND category='alimente de bază' AND quantity=1 AND unit='kg'),
    (SELECT id FROM s WHERE name='Kaufland'),
    DATE '2026-02-25', DATE '2026-03-03', 13
)
INSERT INTO discounts (product_id, supermarket_id, from_date, to_date, percentage_of_discount)
SELECT product_id, supermarket_id, from_date, to_date, percentage_of_discount
FROM d
WHERE product_id IS NOT NULL AND supermarket_id IS NOT NULL
ON CONFLICT (product_id, supermarket_id, from_date, to_date) DO NOTHING;
