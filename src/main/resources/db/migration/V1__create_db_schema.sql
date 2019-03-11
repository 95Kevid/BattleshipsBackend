create table board_position(
  id int(11) auto_increment,
  col char(1),
  is_hit bit(1),
  row int(11),
  primary key (id)
);

create table game(
  id int(11) auto_increment,
  game_arena_size int(11),
  no_of_players int(11),
  turn_index int(11),
  primary key (id)
);

create table game_arena(
  id int(11) auto_increment,
  all_ships_placed bit(1),
  game_arena_size int(11),
  primary key (id)
);

create table game_arena_ships_on_board(
  game_arena_id int(11),
  ships_on_board_id int(11),
  primary key (game_arena_id, ships_on_board_id)
);

create table game_arena_shot_board_positions(
  game_arena_id int(11),
  shot_board_positions_id int(11)
);

create table game_arena_sunk_ships(
  game_arena_id int(11),
  sunk_ships_id int(11),
  primary key (game_arena_id, sunk_ships_id)
);

create table game_players(
  game_id int(11) NOT NULL,
  players_id int(11) NOT NULL
);

create table player(
  id int(11) auto_increment,
  player_name varchar(254),
  ready_to_start_game bit(1),
  game_arena_id int(11),
  primary key (id)
);

create table ship(
  dtype varchar(31),
  id int(11) auto_increment,
  length int(11),
  is_sunk bit(1),
  orient int(11),
  primary key (id)
);

create table ship_occupied_board_positions(
  ship_id int(11),
  occupied_board_positions_id int(11)
);
