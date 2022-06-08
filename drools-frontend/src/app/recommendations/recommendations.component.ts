import { Component, OnInit } from '@angular/core';
import { FormControl } from '@angular/forms';
import { TopListDTO } from '../model/toplist';
import { RecommendationsService } from './service/recommendations.service';

@Component({
  selector: 'app-recommendations',
  templateUrl: './recommendations.component.html',
  styleUrls: ['./recommendations.component.css'],
})
export class RecommendationsComponent implements OnInit {
  genres = new FormControl();
  displayedColumns: string[] = ['Name', 'Points'];
  recommendations: TopListDTO[] = [];
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
  selectedStudios: string[] = [];
  studioList: string[] = ['Ubisoft', 'EA sports', 'Rockstar'];
  length: number = 0;
  price: number = 0;
  isMultiplayer: string = 'false';
  isOnline: string = 'false';

  constructor(private recommendationsService: RecommendationsService) {}

  getRecommendations(): void {
    this.recommendationsService
      .getRecommendations({
        genres: this.selectedGenres,
        studios: this.selectedStudios,
        lenght: this.length,
        price: this.price,
        isMultiplayer: this.isMultiplayer === 'false' ? false : true,
        isOnline: this.isOnline === 'false' ? false : true,
      })
      .subscribe((data) => {
        this.recommendations = data;
      });
  }

  ngOnInit(): void {}
}
