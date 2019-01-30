export class Cell {
  col: string;
  row: number;
  colour: string;
  hit: boolean;

  public equals(cell: Cell) {
    return cell.col === this.col
    && cell.row === this.row;
  }
}
