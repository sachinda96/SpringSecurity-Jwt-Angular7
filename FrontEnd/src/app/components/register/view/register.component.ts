import { Component, OnInit, ViewChild } from '@angular/core';
import { RegistorService } from 'src/app/services/registor.service';
import { Registor } from 'src/app/dtos/registor';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  registorAccount:Registor = new Registor();
  @ViewChild('frmRegistation')frmRegistation:NgForm;
  constructor(private saveRegistationService:RegistorService) { }

  ngOnInit() {
  }

  saveRegistation():void{
    this.saveRegistationService.saveRegistation(this.registorAccount).subscribe(
      (result)=>{
        if(result){
          alert('Registation Succsesfully');
        }else{
          alert('Registation Faild');
        }
      }
    )
  }
}
