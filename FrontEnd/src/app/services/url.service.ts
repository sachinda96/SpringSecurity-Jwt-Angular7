import {HttpClient} from "@angular/common/http";
import { Injectable } from '@angular/core';

export const MAIN_URL ="http://localhost:8080";

@Injectable()
export class UrlService {

  constructor(private http:HttpClient) { }
}
