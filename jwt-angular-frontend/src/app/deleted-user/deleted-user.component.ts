import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-deleted-user',
  templateUrl: './deleted-user.component.html',
  styleUrls: ['./deleted-user.component.css']
})
export class DeletedUserComponent implements OnInit {
  users: any;

  constructor(private userService: UserService,
    private router: Router) { }

  ngOnInit(): void {
    this.getAllDeletedUsers();
  }

  getAllDeletedUsers(){
    this.userService.getAllDeletedUsers().subscribe((data) => {
      this.users = data;
      console.log(this.users);
    }, error => console.log(error));
  }

  deleteUser(id: string){
    this.userService.hardDeleteUser(id).subscribe((data) => {
      console.log(data);
      this.getAllDeletedUsers();
    }, error => console.log(error));
  }

}
