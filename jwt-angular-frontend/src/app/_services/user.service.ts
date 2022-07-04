import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { retry } from 'rxjs/operators';
import { UserAuthService } from './user-auth.service';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  apiPath = "http://localhost:9090";

  requestHeader = new HttpHeaders(
    { "No-Auth": "True" }
  );

  constructor(private httpClient: HttpClient,
    private userAuthService: UserAuthService) { }

  public login(loginData: any) {
    return this.httpClient.post(this.apiPath + "/authenticate", loginData, { headers: this.requestHeader });
  }

  public forUser(){
    return this.httpClient.get(this.apiPath + "/forUser", {responseType: 'text'});
  }

  public forAdmin(){
    return this.httpClient.get(this.apiPath + "/forAdmin", {responseType: 'text'});
  }

  public register(registerData: any) {
    return this.httpClient.post(this.apiPath + "/registeruser", registerData,  { headers: this.requestHeader });
  }

  public getUserById(id: string){
    return this.httpClient.get(this.apiPath + "/user/" + id, {responseType : 'json'})
  }

  public updateUser(id: string, updateData: any) {
    return this.httpClient.put(this.apiPath + "/user/" + id, updateData)
  }

  public getAllUsers() {
    return this.httpClient.get(this.apiPath + "/users");
  }

  public getAllDeletedUsers() {
    return this.httpClient.get(this.apiPath + "/users/delete");
  }

  public softDeleteUser(id: string) {
    return this.httpClient.put(this.apiPath + "/user/soft/delete/"+id, "");
  }

  public hardDeleteUser(id: string) {
    return this.httpClient.delete(this.apiPath + "/user/hard/delete/"+id);
  }

  public lockUser(id: string) {
    return this.httpClient.put(this.apiPath + "/user/lock/" + id, "");
  }

  public unlockUser(id: string) {
    return this.httpClient.put(this.apiPath + "/user/unlock/" + id, "");
  }

  public getUsersSortedByDob() {
    return this.httpClient.get(this.apiPath + "/users/sort/dob");
  }

  public getUsersSortedByJoiningDate() {
    return this.httpClient.get(this.apiPath + "/users/sort/joiningdate");
  }

  public userSearchBy(path: string) {
    return this.httpClient.get(this.apiPath + "/user/" + path);
  }

  public roleMatch(allowedRoles:any): boolean {
    let isMatch = false;
    const userRoles: any = this.userAuthService.getRoles();
    if (userRoles != null && userRoles) {
      for (let i = 0; i < userRoles.length; i++) {
        for (let j = 0; j < allowedRoles.length; j++) {
          if (userRoles[i].roleName === allowedRoles[j]) {
            isMatch = true;
            return isMatch;
          } else {
            return isMatch;
          }
        }
      }
    } else{
      return isMatch;
    }
    return isMatch;
  }
}
