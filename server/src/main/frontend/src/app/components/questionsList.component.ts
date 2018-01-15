import {Component, Input} from '@angular/core';
import {Question} from "../entity/question";


@Component({
  selector: 'questions-list',
  templateUrl: './questionsList.component.html',
  styleUrls: ['./questionsList.component.css']
})
export class QuestionsListComponent {

  @Input()
  questions: Question[];
}
