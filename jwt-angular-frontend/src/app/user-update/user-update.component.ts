import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-user-update',
  templateUrl: './user-update.component.html',
  styleUrls: ['./user-update.component.css']
})
export class UserUpdateComponent implements OnInit {
  user : any;

  name: string = "";
  surname: string = "";
  username: string = "";
  password: string = "";
  pincode: string = "";
  dob: Date;
  joiningDate: Date;

  updateForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    surname : new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required]),
    password: new FormControl('', [Validators.required]),
    pincode: new FormControl('', [Validators.required]),
    dob: new FormControl('', [Validators.required]),
    joiningDate: new FormControl('', [Validators.required])
  })

  constructor(private userService: UserService, 
    private userAuthService: UserAuthService,
    private router: Router) { }

  ngOnInit(): void {
    this.userService.getUserById(this.userAuthService.getId()).subscribe((data) => {
      this.user = data;
      console.log(this.user);
    }, error => console.log(error))
  }

  update() {
    if(this.updateForm.valid){
      this.userService.updateUser(this.userAuthService.getId(), this.updateForm.value).subscribe((data) => {
        console.log(data);
        this.router.navigate(["/user-profile"])
      }, error => console.log(error));
    } else{
      this.updateForm.markAllAsTouched();
    }
    
  }

  get rname(){
    return this.updateForm.get("name");
  }

  get rsurname(){
    return this.updateForm.get("surname");
  }

  get rusername(){
    return this.updateForm.get("username");
  }

  get rpassword(){
    return this.updateForm.get("password");
  }

  get rpincode(){
    return this.updateForm.get("pincode");
  }

  get rdob(){
    return this.updateForm.get("dob");
  }

  get rjoiningDate(){
    return this.updateForm.get("joiningDate");
  }

}
