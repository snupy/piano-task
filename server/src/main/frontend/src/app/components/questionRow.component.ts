import {Component, HostBinding, Input, OnInit} from '@angular/core';
import {Question} from "../entity/question";
import moment = require("moment/moment");
import Moment = moment.Moment;

@Component({
  selector: 'question-row',
  templateUrl: './questionRow.component.html',
  styleUrls: ['./questionRow.component.css']
})
export class QuestionRowComponent implements OnInit{
  @Input()
  question:Question;

  @HostBinding('style.background-color')
  color: string;


  ngOnInit(): void {
    this.color = this.question.answered?'green':'red';
  }

  formatDate(d: number):string{
    const m: Moment = moment(d);
    return m.toISOString();
  }
}
