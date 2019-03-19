create table board_position(
  id int auto_increment,
  col char(1),
  is_hit boolean,
  row int,
  primary key (id)
);

create table game(
  id int auto_increment,
  game_arena_size int,
  no_of_players int,
  turn_index int,
  primary key (id)
);

create table game_arena(
  id int auto_increment,
  all_ships_placed boolean,
  game_arena_size int,
  primary key (id)
);

create table game_arena_ships_on_board(
  game_arena_id int,
  ships_on_board_id int,
  primary key (game_arena_id, ships_on_board_id)
);

create table game_arena_shot_board_positions(
  game_arena_id int,
  shot_board_positions_id int
);

create table game_arena_sunk_ships(
  game_arena_id int,
  sunk_ships_id int,
  primary key (game_arena_id, sunk_ships_id)
);

create table game_players(
  game_id int NOT NULL,
  players_id int NOT NULL
);

create table player(
  id int auto_increment,
  player_name varchar(254),
  ready_to_start_game boolean,
  game_arena_id int,
  primary key (id)
);

create table ship(
  dtype varchar(31),
  id int auto_increment,
  length int,
  is_sunk boolean,
  orient int,
  primary key (id)
);

create table ship_occupied_board_positions(
  ship_id int,
  occupied_board_positions_id int
);
