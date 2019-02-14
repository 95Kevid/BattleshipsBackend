import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {AppComponent} from './app.component';
import {StoreModule} from '@ngrx/store';
import {EffectsModule} from '@ngrx/effects';

import {GridBoxComponent} from './components/grid/grid-box.component';
import {ShipPositionComponent} from './containers/ship-placing/ship-position.component.';
import {HttpClientModule} from '@angular/common/http';
import {ShipPositionBoxComponent} from './components/ship-position-box/ship-position-box.component';
import {CreateGameBoxComponent} from './components/create-game-box/create-game-box.component';
import {GameControlComponent} from './containers/game-control/game-control.component';
import {CreatePlayerBoxComponent} from './components/create-player-box/create-player-box.component';
import {reducers} from './store';
import {StoreDevtoolsModule} from '@ngrx/store-devtools';
import {environment} from '../environments/environment';
import {ShipEffects} from './store/ship/ship.effects';
import {GameStatusBoxComponent} from './components/game-status-box/game-status-box/game-status-box.component';
import { GameGridComponent } from './containers/grid/game-grid/game-grid.component';
import {GameEffects} from './store/game/game.effects';
import { GameControlBoxComponent } from './components/game-status-box/game-control-box/game-control-box.component';


@NgModule({
  declarations: [
    AppComponent,
    GridBoxComponent,
    ShipPositionComponent,
    ShipPositionBoxComponent,
    CreateGameBoxComponent,
    GameControlComponent,
    CreatePlayerBoxComponent,
    GameStatusBoxComponent,
    GameGridComponent,
    GameControlBoxComponent,
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    FormsModule,
    ReactiveFormsModule,
    StoreModule.forRoot(reducers),
    !environment.production ? StoreDevtoolsModule.instrument() : [],
    EffectsModule.forRoot([ShipEffects, GameEffects])
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule {
}
