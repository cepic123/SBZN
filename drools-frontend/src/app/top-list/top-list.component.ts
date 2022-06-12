import { Component, OnInit } from '@angular/core';
import { TopListDTO } from '../model/toplist';
import { TopListService } from './service/top-list.service';

@Component({
  selector: 'app-top-list',
  templateUrl: './top-list.component.html',
  styleUrls: ['./top-list.component.css']
})
export class TopListComponent implements OnInit {
  topList: TopListDTO[] = [];
  displayedColumns: string[] = ['Name', 'Points'];
  gameList: String[] = [];
  selectedGame: String = '';
  selectedScore: number = 0;
  gradeList: number[] = [1, 2, 3, 4, 5]

  constructor(private topListService: TopListService) { }

  ngOnInit(): void {
    this.topListService.getTopList().subscribe((data) => {
      this.topList = data.sort((a, b) => b.points - a.points);
      this.gameList = data.map(game => game.gameName);
    });
  }

  setReview(): void {
    if(this.selectedGame !== '') {
      this.topListService.setReview(this.selectedGame, this.selectedScore).subscribe((data) => {
        if(data !== null) {
          this.topList = data.sort((a, b) => b.points - a.points);
          this.gameList = data.map(game => game.gameName);  
        }
      });
    }
  }
}
