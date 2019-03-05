create table board_position(
  id int(11) NOT NULL,
  col char(1),
  is_hit bit(1),
  row int(11),
  PRIMARY KEY(id)
)

create table game(
  id int(11) NOT NULL,
  game_arena_size int(11),
  no_of_players int(11),
  turn_index int(11),
  PRIMARY KEY(id)
)

create table game_arena(
  id int(11) NOT NULL,
  all_ships_placed bit(1),
  game_arena_size(11),
  PRIMARY KEY(id)
)

create table game_arena_ships_on_board(
  game_arena_id int(11) NOT NULL,
  ships_on_board_id int(11),
  PRIMARY KEY(game_arena_id, ships_on_board_id)
)

create table game_arena_shot_board_positions(
  game_arena_id int(11) NOT NULL,
  shot_board_positions_id int(11)
)

create table game_arena_sunk_ships(
  game_arena_id int(11) NOT NULL,
  sunk_ships_id int(11) NOT NULL,
  PRIMARY KEY(game_arena_id, sunk_ships_id)
)

create table game_players(
  game_id int(11) NOT NULL,
  players_id int(11) NOT NULL,
  )

create table player(
  id int(11) NOT NULL,
  player_name int(11) NOT NULL,
  ready_to_start_game bit(1),
  game_arena_id int(11) NOT NULL
  PRIMARY KEY(id)
)

create table ship(
  dtype varchar(31),
  id int(11) NOT NULL,
  length int(11) NOT NULL,
  is_sunk bit(1),
  orient int(11),
  PRIMARY KEY(id)
  )

create ship_occupied_board_positions(
  ship_id int(11),
  occupied_board_positions_id,
  PRIMARY KEY(id)
  )