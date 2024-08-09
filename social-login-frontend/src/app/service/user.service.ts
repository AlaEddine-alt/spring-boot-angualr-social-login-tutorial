import { inject, Injectable } from '@angular/core';
import { environment } from '../../environments/environment.development';
import { HttpClient } from '@angular/common/http';
import { Observable, ReplaySubject, tap } from 'rxjs';
import { User } from '../model/User';

/**
 * UserService is responsible for managing user-related operations such as fetching user data
 * and checking if a user is present. It uses Angular's HttpClient to make HTTP requests and
 * RxJS's ReplaySubject to manage user state.
 */
@Injectable({
  providedIn: 'root',
})
export class UserService {
  // Base URL for user-related API endpoints
  private apiUrl = `${environment.apiUrl}/user`;

  // Injecting HttpClient for making HTTP requests
  private readonly httpClient = inject(HttpClient);

  // ReplaySubject to hold the user data and allow subscribers to receive the latest user data
  private user_: ReplaySubject<User> = new ReplaySubject<User>(1);

  /**
   * Getter for the user observable.
   * @returns {Observable<User>} An observable that emits the current user data.
   */
  get user(): Observable<User> {
    return this.user_.asObservable();
  }

  /**
   * Setter for the user.
   * @param {User} value - The user data to set.
   */
  set user(value: User) {
    this.user_.next(value);
  }

  /**
   * Fetches the current user data from the API.
   * @returns {Observable<User>} An observable that emits the fetched user data.
   */
  getUser(): Observable<User> {
    return this.httpClient.get<User>(`${this.apiUrl}/me`).pipe(
      tap((user: User) => {
        this.user = user; // Update the user data in the ReplaySubject
      })
    );
  }

  /**
   * Checks if the user data is present in the ReplaySubject.
   * @returns {boolean} True if user data is present, false otherwise.
   */
  hasUser(): boolean {
    let hasValue = false;
    this.user_
      .subscribe({
        next: () => {
          hasValue = true;
        },
        error: () => {
          hasValue = false;
        },
        complete: () => {
          hasValue = false;
        },
      })
      .unsubscribe();
    return hasValue;
  }
}
