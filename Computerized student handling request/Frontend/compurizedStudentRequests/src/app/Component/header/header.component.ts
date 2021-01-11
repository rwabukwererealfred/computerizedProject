import { Component, OnInit } from '@angular/core';
import { RegistrationService } from 'src/app/Service/registration.service';
import { AuthService } from 'src/app/Service/auth.service';
import { StorageTokenService } from 'src/app/Service/storage-token.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-header',
  templateUrl: './header.component.html',
  styleUrls: ['./header.component.css']
})
export class HeaderComponent implements OnInit {

  constructor(private storage: StorageTokenService,private router: Router) { }

  ngOnInit() {
  }

  logout(){
    this.storage.signout();
    this.router.navigate(['/login']);
  }

}
