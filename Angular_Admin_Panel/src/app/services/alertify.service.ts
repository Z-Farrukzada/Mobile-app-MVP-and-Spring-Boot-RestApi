import { Injectable } from '@angular/core';



declare let alertify: any;

export class AlertifyService {

  constructor() { }


  success(message:string){
    alertify.success(message);
  }

  error(message:string){
    alertify.error(message);
  }
}
