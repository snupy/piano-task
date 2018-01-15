import {Component, OnInit} from '@angular/core';
import {QuestionService} from "../service/question.service";
import {Page} from "../entity/page";
import {Question} from "../entity/question";
import 'rxjs/add/operator/debounceTime';
import 'rxjs/add/operator/distinctUntilChanged';
import {Subject} from "rxjs/Subject";

@Component({
  selector: 'questions-view',
  templateUrl: './questionsView.component.html',
  styleUrls: ['./questionsView.component.css']
})
export class QuestionsViewComponent{

  search: string;
  pageNumber: number;
  pageSize: number;
  page: Page<Question>;

  private searchTextChanged: Subject<string> = new Subject<string>();

  constructor(private qs: QuestionService) {
    this.searchTextChanged
      .debounceTime(600)
      .distinctUntilChanged()
      .subscribe((s) => {
        if (s) {
          this.refreshQuestions();
        } else {
          this.clear();
        }
      })
  }

  private refreshQuestions() {
    this.pageNumber = 0;
    this.pageSize = 10;
    this.loadQuestions();
  }

  private clear() {
    this.pageNumber = 0;
    this.pageSize = 10;
    this.page = null;
  }

  private loadQuestions() {
    this.qs.getPage(this.search, this.pageNumber, this.pageSize).subscribe(p => {
      this.page = p;
    });
  }

  public searchChange(s: string): void {
    this.search = s;
    this.searchTextChanged.next(s);
  }

  public nextPage() {
    this.pageNumber++;
    this.loadQuestions();
  }

  public previousPage() {
    this.pageNumber--;
    this.loadQuestions();
  }
}
