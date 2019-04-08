create table ship_ship_type(
  ship_id int,
  ship_type_id int
);

alter table ship
drop column dtype;

alter table ship
add column board_position_id int first;