import { Component } from '@angular/core';
import {IColumnDefinition} from './IColumnDefinition';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.scss']
})
export class AppComponent {
  title = 'battleshipclient';
  boardSize: Number = 10;

  columnDefs = this.createColumnDefs()

  rowData = [ { row: 1, A: '', B: '', C: ''},
              { row: 2, A: '', B: '', C: ''},
              { row: 3, A: '', B: '', C: ''}
  ];

  public createColumnDefs() {

     let columnDefs: IColumnDefinition[];
     columnDefs = [
      {
        headerName: 'Row',
        field: 'row'
      }
      ];

     for (let i = 0; i < this.boardSize ; i++) {
        const newObject: IColumnDefinition = {
          headerName: String.fromCharCode(65 + i),
          field: String.fromCharCode(65 + i)
        };
        columnDefs.push(newObject);
     }
     return columnDefs;
  }

  public createRowData() {
    for (let i = 0; i < this.boardSize ; i++) {
      this.rowData.push(

  }

  public testFn() {
    const columnDefs = [
      {headerName: 'A', field: 'A'},
      {headerName: 'B', field: 'B'},
      {headerName: 'C', field: 'C'}
    ];

    const rowData = [
      { A: 1, B: 1, C: 1 },
      { A: 2, B: 2, C: 2 },
      { A: 3, B: 3, C: 3 }
    ];
  }





}
