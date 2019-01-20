import {Cell} from './cell';

export class Ship {
  occupiedBoardPositions: Cell[];
  orient: string;
  length: number;
  sunk: boolean;
}
