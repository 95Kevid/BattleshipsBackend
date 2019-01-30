import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule} from '@angular/forms';
import { AppComponent } from './app.component';
import { StoreModule } from '@ngrx/store';
import { EffectsModule } from '@ngrx/effects';

import { GridComponent } from './grid/grid.component';
import { ShipPlacerUIComponent } from './containers/ship-placing/ship-placer-u-i.component';
import {HttpClientModule} from '@angular/common/http';
import { ShipPositionBoxComponent } from './components/ship-position-box/ship-position-box.component';
import { CreateGameBoxComponent } from './components/create-game-box/create-game-box.component';
import { GameControlComponent } from './containers/game-control/game-control.component';
import { CreatePlayerBoxComponent } from './components/create-player-box/create-player-box.component';
import {GameControlBoxComponent} from './components/game-control-box/game-control-box.component';
import { shipReducers } from './store/ship/ship.reducers';
import {reducers} from './store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {environment} from '../environments/environment';
import {ShipEffects} from './store/ship/ship.effects';



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
    StoreModule.forRoot(reducers),
    !environment.production ? StoreDevtoolsModule.instrument() : [],
    EffectsModule.forRoot([ShipEffects])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
