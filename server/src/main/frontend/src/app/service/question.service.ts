import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {Page} from "../entity/page";
import {Question} from "../entity/question";
import {HttpClient, HttpHeaders, HttpParameterCodec, HttpParams, HttpUrlEncodingCodec} from "@angular/common/http";

import {map} from 'rxjs/operators'
import {QueryEncoder} from "@angular/http";

@Injectable()
export class QuestionService {

  constructor(private httpClient: HttpClient) {
  }


  public getPage(intitle: string, page: number, pageSize: number): Observable<any> {

    const url: string = '/api/1/questions/search';

    let params: HttpParams = new HttpParams({encoder:new CustomQueryEncoderHelper()})
      .set('page', page.toString())
      .set('size', pageSize.toString())
      .set('intitle', intitle);

    let headers: HttpHeaders = new HttpHeaders()
      .append('Content-Type', 'application/x-www-form-urlencoded');


    let options = {
      params: params,
      headers: headers
    };

    return this.httpClient.get(url, options).pipe();

  }
}

class CustomQueryEncoderHelper extends HttpUrlEncodingCodec {
  encodeKey(k: string): string {
    k = super.encodeKey(k);
    return k.replace(/\+/gi, '%2B');
  }
  encodeValue(v: string): string {
    v = super.encodeValue(v);
    return v.replace(/\+/gi, '%2B');
  }
}
