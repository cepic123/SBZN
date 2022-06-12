import { Component, OnInit } from '@angular/core';
import { TopListDTO } from '../model/toplist';
import { CreateGameService } from './service/create-game.service';

@Component({
  selector: 'app-create-game',
  templateUrl: './create-game.component.html',
  styleUrls: ['./create-game.component.css']
})
export class CreateGameComponent implements OnInit {
  topList: TopListDTO[] = [];
  selectedGenres: string[] = [];
  genreList: string[] = [
    'ADVENTURE',
    'MMO',
    'SPORTS',
    'RPG',
    'FPS',
    'STRATEGY',
    'MOBA',
  ];
  selectedStudio: string = "";
  studioList: string[] = ['Ubisoft', 'EA sports', 'Rockstar'];
  length: number = 0;
  price: number = 0;
  isMultiplayer: string = 'false';
  isOnline: string = 'false';
  name: string = '';

  constructor(private createGameService: CreateGameService) { }

  ngOnInit(): void {}

  save(): void {
    this.createGameService
      .save({
        name: this.name,
        genres: this.selectedGenres,
        studioName: this.selectedStudio,
        lenght: this.length,
        price: this.price,
        isMultiplayer: this.isMultiplayer === 'false' ? false : true,
        isOnline: this.isOnline === 'false' ? false : true,
      })
      .subscribe((data) => {
        alert("Game succesfully added!");
      });
  }
}