import { Component } from '@angular/core';
import { HttpHeaders, HttpParams, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Router } from '@angular/router';

@Component({
  selector: 'app-usersearch',
  templateUrl: './usersearch.component.html',
  styleUrls: ['/../app.component.css']
})

export class UserSearchComponent {

  headers = new HttpHeaders({
    'Content-Type': 'application/json',
    'Authorization': 'Bearer ' + localStorage.getItem("jwtToken"),
  });

  searchUserUrl = 'http://localhost:8924/api/user/namesearch/';
  addFriendUrl = 'http://localhost:8924/api/friends/';

  users: any[];

  constructor(private http: HttpClient, private router: Router) { }

  searchUser(nameValue) {
    this.users = [];

    let params = new HttpParams();
    params = params.append("name", nameValue);

    this.http.get<any>(this.searchUserUrl, { headers: this.headers, params: params }).toPromise().then(data => {

      var index = 0;
      while (data[index] != null) {
        this.users.push(data[index]);
        index++;
      }
    },
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side error occured.');
        } else {
          console.log('Server-side error occured.');
        }
      }
    )
  }

  viewProfile(userId) {
    this.router.navigate(['viewuserprofile'], {
      queryParams:
        { userId: userId }
    });
  }

  addFriend(friendId) {
    this.http.post<any>(this.addFriendUrl, { userId: localStorage.getItem('userId'), friendId: friendId }, { headers: this.headers }).toPromise().then(data => {
      //Todo Dialog box to say friend added
    }
      ,
      (err: HttpErrorResponse) => {
        if (err.error instanceof Error) {
          console.log('Client-side error occured.');
        } else {
          console.log('Server-side error occured.');
        }
      }
    )
  }
}
