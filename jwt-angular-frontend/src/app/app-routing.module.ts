import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminComponent } from './admin/admin.component';
import { ForbiddenComponent } from './forbidden/forbidden.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserUpdateComponent } from './user-update/user-update.component';
import { UserComponent } from './user/user.component';
import { AuthGuard } from './_auth/auth.guard';

const routes: Routes = [
  { path: "home", component: HomeComponent },
  {path: '', redirectTo: "home", pathMatch: "full"},
  {path: "register", component: RegisterComponent},
  { path: "admin", component: AdminComponent , canActivate: [AuthGuard], data: {roles: ['admin']}},
  { path: "user", component: UserComponent, canActivate: [AuthGuard], data: {roles: ['user']}},
  { path: "user-profile", component: UserProfileComponent, canActivate: [AuthGuard], data: {roles: ['user']}},
  { path: "user-update", component: UserUpdateComponent, canActivate: [AuthGuard], data: {roles: ['user']}},
  { path: "login", component: LoginComponent },
  { path: "forbidden", component: ForbiddenComponent }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
