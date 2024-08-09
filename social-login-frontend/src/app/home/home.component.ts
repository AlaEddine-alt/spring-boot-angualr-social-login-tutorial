import { Component, inject, OnInit } from '@angular/core';
import { UserService } from '../service/user.service';
import { User } from '../model/User';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './home.component.html',
  styleUrl: './home.component.css',
})
export class HomeComponent implements OnInit {
  user: any;
  private readonly userService = inject(UserService);

  ngOnInit(): void {
    this.userService.user.subscribe({
      next: (user: User) => {
        this.user = user;
      },
      error: (error) => {
        console.error(error);
      },
    });
  }
}
