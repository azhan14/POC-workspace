import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-locked-user',
  templateUrl: './locked-user.component.html',
  styleUrls: ['./locked-user.component.css']
})
export class LockedUserComponent implements OnInit {
  users: any;

  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
    this.getAllUsers();
  }

  getAllUsers(){
    this.userService.getAllUsers().subscribe((data) => {
      this.users = data;
      console.log(this.users);
    }, error => console.log(error));
  }

  unlockUser(id: string){
    this.userService.unlockUser(id).subscribe((data) => {
      console.log(data);
      this.getAllUsers();
    }, error => console.log(error));
  }

}
