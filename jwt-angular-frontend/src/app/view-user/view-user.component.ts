import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-view-user',
  templateUrl: './view-user.component.html',
  styleUrls: ['./view-user.component.css']
})
export class ViewUserComponent implements OnInit {

  users: any;
  selectedValue: any;
  searchOption: any;
  searchText: any;
  path: any;
  searchTerm: string;

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

  updateUser(id: string){
    this.router.navigate(['update',id]);
  }

  deleteUser(id: string) {
    this.userService.softDeleteUser(id).subscribe((data) => {
      console.log(data);
      this.getAllUsers();
    }, error => console.log(error));
  }

  lockUser(id: string) {
    this.userService.lockUser(id).subscribe((data) => {
      console.log("user locked with id: "+id);
      this.getAllUsers();
    }, error => console.log(error));
  }

  onSelectSearch(){
    this.searchText = "";
    this.getAllUsers();
  }

  onSearchText() {
    this.path = this.searchOption+"/"+this.searchText;
    this.userService.userSearchBy(this.path).subscribe((data) => {
      this.users = data;
    }, error => console.log(error));
  }

  sort(){
    if(this.selectedValue === "dob"){
      this.userService.getUsersSortedByDob().subscribe((data) => {
        this.users = data;
        console.log(this.users);
      }, error => console.log(error));
    } else if(this.selectedValue === "joiningdate") {
      this.userService.getUsersSortedByJoiningDate().subscribe((data) => {
        this.users = data;
        console.log(this.users);
      }, error => console.log(error));
    }
  }

}
