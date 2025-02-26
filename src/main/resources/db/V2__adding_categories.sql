CREATE TABLE place_categories
(
    id          SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

CREATE TABLE dish_categories
(
    id          SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL
);

ALTER TABLE restaurants
    ADD COLUMN place_category_id INT NOT NULL;
ALTER TABLE restaurants
    ADD CONSTRAINT fk_place_category FOREIGN KEY (place_category_id) REFERENCES place_categories (id);

ALTER TABLE dishes
    ADD COLUMN dish_category_id INT NOT NULL;
ALTER TABLE dishes
    ADD CONSTRAINT fk_dish_category FOREIGN KEY (dish_category_id) REFERENCES dish_categories (id);

-- Inserindo categorias de restaurantes
INSERT INTO place_categories (id, description)
VALUES (1, 'Lanchonete'),
       (2, 'Hamburgueria'),
       (3, 'Pizzaria'),
       (4, 'Fast Food'),
       (5, 'Restaurante Casual');
INSERT INTO dish_categories (id, description) VALUES
      (1, 'Bebidas'),
      (2, 'Sanduíches'),
      (3, 'Frango'),
      (4, 'Pizzas'),
      (5, 'Acompanhamentos');

-- Atualizando categorias dos restaurantes existentes
UPDATE restaurants SET place_category_id = 1 WHERE name = 'McDonalds';  -- Lanchonete
UPDATE restaurants SET place_category_id = 2 WHERE name = 'Burger King';  -- Hamburgueria
UPDATE restaurants SET place_category_id = 4 WHERE name = 'KFC';  -- Fast Food
UPDATE restaurants SET place_category_id = 5 WHERE name = 'Subway';  -- Restaurante Casual
UPDATE restaurants SET place_category_id = 3 WHERE name = 'Pizza Hut';  -- Pizzaria

-- Atualizando categorias dos pratos existentes
UPDATE dishes SET dish_category_id = 2 WHERE name = 'Big Mac';  -- Sanduíches
UPDATE dishes SET dish_category_id = 2 WHERE name = 'McChicken';  -- Sanduíches
UPDATE dishes SET dish_category_id = 3 WHERE name = 'McNuggets';  -- Frango
UPDATE dishes SET dish_category_id = 2 WHERE name = 'Whopper';  -- Sanduíches
UPDATE dishes SET dish_category_id = 3 WHERE name = 'Chicken Fries';  -- Frango
UPDATE dishes SET dish_category_id = 5 WHERE name = 'Onion Rings';  -- Acompanhamentos
UPDATE dishes SET dish_category_id = 3 WHERE name = 'Chicken Bucket';  -- Frango
UPDATE dishes SET dish_category_id = 2 WHERE name = 'Zinger Burger';  -- Sanduíches
UPDATE dishes SET dish_category_id = 3 WHERE name = 'Hot Wings';  -- Frango
UPDATE dishes SET dish_category_id = 2 WHERE name = 'Sub de Frango';  -- Sanduíches
UPDATE dishes SET dish_category_id = 2 WHERE name = 'Sub de Carne';  -- Sanduíches
UPDATE dishes SET dish_category_id = 2 WHERE name = 'Sub Vegetariano';  -- Sanduíches
UPDATE dishes SET dish_category_id = 4 WHERE name = 'Pizza Pepperoni';  -- Pizzas
UPDATE dishes SET dish_category_id = 4 WHERE name = 'Pizza de Queijo';  -- Pizzas
UPDATE dishes SET dish_category_id = 4 WHERE name = 'Pizza Supreme';  -- Pizzas