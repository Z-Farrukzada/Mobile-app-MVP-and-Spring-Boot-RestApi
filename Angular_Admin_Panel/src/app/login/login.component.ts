import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { AlertifyService } from '../services/alertify.service';



@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss'],
})
export class LoginComponent implements OnInit {



  constructor(private alertify:AlertifyService) { }

  loginForm: FormGroup = new FormGroup({
    "userName": new FormControl("", Validators.required),
    "password": new FormControl("", Validators.required),
  });



  ngOnInit(): void {
    this.loginForm;
  }

  onSubmit() {
    if (this.loginForm.valid) {
      alert(this.loginForm.value);
    }

  }

  change(event: any) {
    if (event.target.value <= 0) {
      this.alertify.error("Zəhmət olmasa boş saxlamayın.")
    }
  }
  Submit() {


  }

}
