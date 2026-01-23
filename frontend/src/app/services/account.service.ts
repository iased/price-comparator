import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

export interface AccountDto {
  email: string;
  name: string;
}

@Injectable({ providedIn: 'root' })
export class AccountService {
  private base = 'http://localhost:8080/api';

  constructor(private http: HttpClient) {}

  getAccount(): Observable<AccountDto> {
    return this.http.get<AccountDto>(`${this.base}/account`);
  }

  updateAccountName(name: string): Observable<AccountDto> {
    return this.http.patch<AccountDto>(`${this.base}/account`, { name });
  }

  deleteAccount(password: string) {
    return this.http.delete<void>(`${this.base}/account`, { body: { password } });
  }
}
