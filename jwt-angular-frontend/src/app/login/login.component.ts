import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { UserAuthService } from '../_services/user-auth.service';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private userService: UserService,
    private userAuthService: UserAuthService,
    private router: Router) { }

  ngOnInit(): void {
  }

  login(loginForm: NgForm){
    this.userService.login(loginForm.value).subscribe(
      (response: any) => {
        // console.log(response.user.id);
        // console.log(response.jwtToken);
        // console.log(response.user.roles);

        this.userAuthService.setRoles(response.user.roles);
        this.userAuthService.setToken(response.jwtToken);
        this.userAuthService.setId(response.user.id);

        const role = response.user.roles[0].roleName;
        if(role === 'admin'){
          this.router.navigate(["/admin"])
        } else {
          this.router.navigate(["/user"])
        }
      },
      (error) => {
        console.log(error);
      }
    );
  }

}
