import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';

import { GridComponent } from './grid/grid.component';
import { ShipPlacerUIComponent } from './containers/ship-placing/ship-placer-u-i.component';
import {HttpClientModule} from '@angular/common/http';
import { ShipPositionBoxComponent } from './components/ship-position-box/ship-position-box.component';
import { CreateGameBoxComponent } from './components/create-game-box/create-game-box.component';
import { GameControlComponent } from './containers/game-control/game-control.component';
import { CreatePlayerBoxComponent } from './components/create-player-box/create-player-box.component';
import {GameControlBoxComponent} from './components/game-control-box/game-control-box.component';
import { reducers } from './store/addShipReducer';



@NgModule({
  declarations: [
    AppComponent,
    GridComponent,
    ShipPlacerUIComponent,
    ShipPositionBoxComponent,
    CreateGameBoxComponent,
    GameControlComponent,
    GameControlBoxComponent,
    CreatePlayerBoxComponent,
    GameControlBoxComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    StoreModule.forRoot({reducers})
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
