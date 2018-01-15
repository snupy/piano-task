import {BrowserModule} from '@angular/platform-browser';
import {NgModule} from '@angular/core';


import {AppComponent} from './app.component';
import {QuestionService} from "./service/question.service";
import {QuestionsListComponent} from "./components/questionsList.component";
import {QuestionRowComponent} from "./components/questionRow.component";
import {QuestionsViewComponent} from "./components/questionsView.component";
import {FormsModule} from "@angular/forms";
import {HttpClientModule} from "@angular/common/http";
import {CommonModule} from "@angular/common";


@NgModule({
  declarations: [
    AppComponent,
    QuestionsViewComponent,
    QuestionsListComponent,
    QuestionRowComponent
  ],
  imports: [
    BrowserModule,
    FormsModule,
    HttpClientModule,
    CommonModule
  ],
  providers: [QuestionService],
  bootstrap: [AppComponent]
})
export class AppModule {
}
