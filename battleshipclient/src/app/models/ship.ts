import {Cell} from './cell';

export class Ship {
  private occupiedBoardPositions: Cell[];
  private orient: string;
  private length: number;
  private sunk: boolean;
}
