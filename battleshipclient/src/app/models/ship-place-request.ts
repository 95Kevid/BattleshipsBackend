export class ShipPlaceRequest {
  playerId ? = 0;
  gameId ? = 0;
  boardPosition: {
    col: string;
    row: number;
  } = {col: '', row: 0};
  orientation = '';
}
