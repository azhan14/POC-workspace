import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-user-profile',
  templateUrl: './user-profile.component.html',
  styleUrls: ['./user-profile.component.css']
})
export class UserProfileComponent implements OnInit {
  user : any;
  constructor(private userService: UserService, 
    private userAuthService: UserAuthService,
    private router: Router) { }
  
  ngOnInit(): void {
    this.userService.getUserById(this.userAuthService.getId()).subscribe((data) => {
      this.user = data;
      console.log(this.user);
    }, error => console.log(error))
  }

  updateProfile(){
    this.router.navigate(["/user-update"]);
  }

}
