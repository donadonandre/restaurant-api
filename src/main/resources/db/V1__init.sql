CREATE TABLE cities
(
    id    BIGSERIAL PRIMARY KEY,
    name  VARCHAR(255) NOT NULL,
    state VARCHAR(2)   NOT NULL
);

CREATE TABLE restaurants
(
    id      UUID PRIMARY KEY,
    name    VARCHAR(255) NOT NULL,
    active  BOOLEAN      NOT NULL DEFAULT TRUE,
    city_id BIGINT       NOT NULL,
    FOREIGN KEY (city_id) REFERENCES cities (id) ON DELETE CASCADE
);

CREATE TABLE dishes
(
    id            UUID PRIMARY KEY,
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
    FOREIGN KEY (restaurant_id) REFERENCES restaurants (id) ON DELETE CASCADE,
    FOREIGN KEY (dish_id) REFERENCES dishes (id) ON DELETE CASCADE
);

INSERT INTO cities(id, name, state)
VALUES (1, 'São Paulo', 'SP');

INSERT INTO restaurants (id, name, city_id)
VALUES ('550e8400-e29b-41d4-a716-446655440000', 'McDonalds', 1),
       ('550e8400-e29b-41d4-a716-446655440001', 'Burger King', 1),
       ('550e8400-e29b-41d4-a716-446655440002', 'KFC', 1),
       ('550e8400-e29b-41d4-a716-446655440003', 'Subway', 1),
       ('550e8400-e29b-41d4-a716-446655440004', 'Pizza Hut', 1);

INSERT INTO dishes (id, name)
VALUES ('111e8400-e29b-41d4-a716-446655440000', 'Big Mac'),
       ('111e8400-e29b-41d4-a716-446655440001', 'McChicken'),
       ('111e8400-e29b-41d4-a716-446655440002', 'McNuggets'),
       ('111e8400-e29b-41d4-a716-446655440003', 'Whopper'),
       ('111e8400-e29b-41d4-a716-446655440004', 'Chicken Fries'),
       ('111e8400-e29b-41d4-a716-446655440005', 'Onion Rings'),
       ('111e8400-e29b-41d4-a716-446655440006', 'Chicken Bucket'),
       ('111e8400-e29b-41d4-a716-446655440007', 'Zinger Burger'),
       ('111e8400-e29b-41d4-a716-446655440008', 'Hot Wings'),
       ('111e8400-e29b-41d4-a716-446655440009', 'Sub de Frango'),
       ('111e8400-e29b-41d4-a716-446655440010', 'Sub de Carne'),
       ('111e8400-e29b-41d4-a716-446655440011', 'Sub Vegetariano'),
       ('111e8400-e29b-41d4-a716-446655440012', 'Pizza Pepperoni'),
       ('111e8400-e29b-41d4-a716-446655440013', 'Pizza de Queijo'),
       ('111e8400-e29b-41d4-a716-446655440014', 'Pizza Supreme');

INSERT INTO restaurant_dishes (restaurant_id, dish_id)
VALUES ('550e8400-e29b-41d4-a716-446655440000', '111e8400-e29b-41d4-a716-446655440000'),
       ('550e8400-e29b-41d4-a716-446655440000', '111e8400-e29b-41d4-a716-446655440001'),
       ('550e8400-e29b-41d4-a716-446655440000', '111e8400-e29b-41d4-a716-446655440002'),
       ('550e8400-e29b-41d4-a716-446655440001', '111e8400-e29b-41d4-a716-446655440003'),
       ('550e8400-e29b-41d4-a716-446655440001', '111e8400-e29b-41d4-a716-446655440004'),
       ('550e8400-e29b-41d4-a716-446655440001', '111e8400-e29b-41d4-a716-446655440005'),
       ('550e8400-e29b-41d4-a716-446655440002', '111e8400-e29b-41d4-a716-446655440006'),
       ('550e8400-e29b-41d4-a716-446655440002', '111e8400-e29b-41d4-a716-446655440007'),
       ('550e8400-e29b-41d4-a716-446655440002', '111e8400-e29b-41d4-a716-446655440008'),
       ('550e8400-e29b-41d4-a716-446655440003', '111e8400-e29b-41d4-a716-446655440009'),
       ('550e8400-e29b-41d4-a716-446655440003', '111e8400-e29b-41d4-a716-446655440010'),
       ('550e8400-e29b-41d4-a716-446655440003', '111e8400-e29b-41d4-a716-446655440011'),
       ('550e8400-e29b-41d4-a716-446655440004', '111e8400-e29b-41d4-a716-446655440012'),
       ('550e8400-e29b-41d4-a716-446655440004', '111e8400-e29b-41d4-a716-446655440013'),
       ('550e8400-e29b-41d4-a716-446655440004', '111e8400-e29b-41d4-a716-446655440014');