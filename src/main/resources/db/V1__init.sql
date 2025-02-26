CREATE TABLE restaurants
(
    id     UUID PRIMARY KEY,
    name   VARCHAR(255) NOT NULL,
    review VARCHAR(30)           DEFAULT NULL,
    active BOOLEAN      NOT NULL DEFAULT TRUE
);

CREATE TABLE dishes
(
    id            UUID PRIMARY KEY, -- Agora um UUID único para cada prato
    restaurant_id UUID         NOT NULL,
    name          VARCHAR(255) NOT NULL,
    active        BOOLEAN      NOT NULL,
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
);

CREATE TABLE restaurant_dishes
(
    restaurant_id UUID NOT NULL,
    dish_id       UUID NOT NULL,
    PRIMARY KEY (restaurant_id, dish_id),
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id),
    FOREIGN KEY (dish_id) REFERENCES dishes (id)
);

-- Inserindo restaurantes
INSERT INTO restaurants (id, name)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'McDonalds'),
       ('550e8400-e29b-41d4-a716-446655440001', 'Burger King'),
       ('550e8400-e29b-41d4-a716-446655440002', 'KFC'),
       ('550e8400-e29b-41d4-a716-446655440003', 'Subway'),
       ('550e8400-e29b-41d4-a716-446655440004', 'Pizza Hut');

-- Inserindo pratos
INSERT INTO dishes (id, name, active)
VALUES ('111e8400-e29b-41d4-a716-446655440000', 'Big Mac', true),
       ('111e8400-e29b-41d4-a716-446655440001', 'McChicken', true),
       ('111e8400-e29b-41d4-a716-446655440002', 'McNuggets', true),
       ('111e8400-e29b-41d4-a716-446655440003', 'Whopper', true),
       ('111e8400-e29b-41d4-a716-446655440004', 'Chicken Bucket', true),
       ('111e8400-e29b-41d4-a716-446655440005', 'Pizza Pepperoni', true);

-- Associando pratos a restaurantes
INSERT INTO restaurant_dishes (restaurant_id, dish_id)
VALUES ('550e8400-e29b-41d4-a716-446655440000', '111e8400-e29b-41d4-a716-446655440000'), -- McDonalds -> Big Mac
       ('550e8400-e29b-41d4-a716-446655440000', '111e8400-e29b-41d4-a716-446655440001'), -- McDonalds -> McChicken
       ('550e8400-e29b-41d4-a716-446655440000', '111e8400-e29b-41d4-a716-446655440002'), -- McDonalds -> McNuggets

       ('550e8400-e29b-41d4-a716-446655440001', '111e8400-e29b-41d4-a716-446655440003'), -- Burger King -> Whopper
       ('550e8400-e29b-41d4-a716-446655440001', '111e8400-e29b-41d4-a716-446655440000'), -- Burger King -> Big Mac
       ('550e8400-e29b-41d4-a716-446655440001', '111e8400-e29b-41d4-a716-446655440002'), -- Burger King -> McNuggets

       ('550e8400-e29b-41d4-a716-446655440002', '111e8400-e29b-41d4-a716-446655440004'), -- KFC -> Chicken Bucket
       ('550e8400-e29b-41d4-a716-446655440002', '111e8400-e29b-41d4-a716-446655440002'), -- KFC -> McNuggets
       ('550e8400-e29b-41d4-a716-446655440002', '111e8400-e29b-41d4-a716-446655440003'), -- KFC -> Whopper

       ('550e8400-e29b-41d4-a716-446655440004', '111e8400-e29b-41d4-a716-446655440005'), -- Pizza Hut -> Pizza Pepperoni
       ('550e8400-e29b-41d4-a716-446655440004', '111e8400-e29b-41d4-a716-446655440002'), -- Pizza Hut -> McNuggets
       ('550e8400-e29b-41d4-a716-446655440004', '111e8400-e29b-41d4-a716-446655440004'); -- Pizza Hut -> Chicken Bucket