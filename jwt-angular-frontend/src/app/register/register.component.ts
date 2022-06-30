import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, NgForm, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit {

  name: string = "";
  surname: string = "";
  username: string = "";
  password: string = "";
  pincode: string = "";
  dob: Date;
  joiningDate: Date;

  registerForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    surname : new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    pincode: new FormControl('', [Validators.required]),
    dob: new FormControl('', [Validators.required]),
    joiningDate: new FormControl('', [Validators.required])
  })

  constructor(private userService: UserService, private router: Router) { }

  ngOnInit(): void {
  }

  register(){
    if(this.registerForm.valid){
      console.log(this.registerForm.value)
      this.userService.register(this.registerForm.value).subscribe((data) => {
        console.log(data);
        this.router.navigate(["/login"])
      }, error => console.log(error))
    } else{
      this.registerForm.markAllAsTouched();
    }
    
  }

  get rname(){
    return this.registerForm.get("name");
  }

  get rsurname(){
    return this.registerForm.get("surname");
  }

  get rusername(){
    return this.registerForm.get("username");
  }

  get rpassword(){
    return this.registerForm.get("password");
  }

  get rpincode(){
    return this.registerForm.get("pincode");
  }

  get rdob(){
    return this.registerForm.get("dob");
  }

  get rjoiningDate(){
    return this.registerForm.get("joiningDate");
  }

}
