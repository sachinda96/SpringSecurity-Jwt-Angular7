import { Injectable } from '@angular/core';
import { Sigin } from '../dtos/sigin';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { MAIN_URL } from './url.service';
import {map} from "rxjs/operators";
import {HttpClient} from "@angular/common/http";

const URL = "/login";
@Injectable()
export class SiginService {

  constructor(private http:HttpClient,private router:Router) { }

  login(sigin:Sigin):Observable<boolean>{
    return this.http.post<boolean>(MAIN_URL+URL,sigin)
    .pipe(
      map((result)=>{
        sessionStorage.setItem("token",result+"");
        if(result){
          this.router.navigate(['']);
        }
        return result;
      })
    )
  }

  isAuthenticated():boolean{
    if (sessionStorage.getItem("token")){
      return sessionStorage.getItem("token")== 'false' ? false : true;
    }
  }

  logout():void{
    sessionStorage.removeItem("token");
    this.router.navigate(['/sigin']);
  }

  }
