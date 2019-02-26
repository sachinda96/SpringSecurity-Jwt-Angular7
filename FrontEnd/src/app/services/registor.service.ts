import { MAIN_URL } from './url.service';
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Registor } from '../dtos/registor';
import { Observable } from 'rxjs';

const URL="/signup";

@Injectable()
export class RegistorService {

  constructor(private http:HttpClient) { }

  saveRegistation(userRegistation:Registor):Observable<boolean>{
    return this.http.post<boolean>(MAIN_URL+URL,userRegistation);
  }

}
