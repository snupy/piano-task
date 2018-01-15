export class Page<T> {
  content: T[];
  totalPages: number;
  totalElements: number;
  last: boolean;
  first: boolean;


  constructor(content: T[], totalPages: number, totalElements: number, last: boolean, first: boolean) {
    this.content = content;
    this.totalPages = totalPages;
    this.totalElements = totalElements;
    this.last = last;
    this.first = first;
  }
}
