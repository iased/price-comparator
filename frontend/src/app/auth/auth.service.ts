import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, catchError, map, of, tap } from 'rxjs';

export type AuthResponse = {
  token: string;
  email?: string;
  name?: string;
};

const TOKEN_KEY = 'pc_token';
const USER_KEY = 'pc_user';

@Injectable({ providedIn: 'root' })
export class AuthService {
  private baseUrl = 'http://localhost:8080/api/auth';

  private loggedIn$ = new BehaviorSubject<boolean>(this.hasToken());

  constructor(private http: HttpClient) {}

  isLoggedIn(): boolean {
    return this.loggedIn$.value;
  }

  loggedInChanges() {
    return this.loggedIn$.asObservable();
  }

  login(email: string, password: string) {
    return this.http
      .post<AuthResponse>(`${this.baseUrl}/login`, { email, password })
      .pipe(
        tap(res => this.persist(res)),
        tap(() => this.loggedIn$.next(true))
      );
  }

  register(name: string, email: string, password: string) {
    return this.http
      .post<AuthResponse>(`${this.baseUrl}/register`, { name, email, password })
      .pipe(
        tap(res => this.persist(res)),
        tap(() => this.loggedIn$.next(true))
      );
  }

  logout() {
    localStorage.removeItem(TOKEN_KEY);
    localStorage.removeItem(USER_KEY);
    this.loggedIn$.next(false);
  }

  getToken(): string | null {
    return localStorage.getItem(TOKEN_KEY);
  }

  getUserNameOrEmail(): string {
    const raw = localStorage.getItem(USER_KEY);
    if (!raw) return '';
    try {
      const u = JSON.parse(raw);
      return u.name || u.email || '';
    } catch {
      return '';
    }
  }

  private persist(res: AuthResponse) {
    if (res?.token) localStorage.setItem(TOKEN_KEY, res.token);

    const user = { email: res.email ?? '', name: res.name ?? '' };
    localStorage.setItem(USER_KEY, JSON.stringify(user));
  }

  private hasToken(): boolean {
    const t = localStorage.getItem(TOKEN_KEY);
    return !!t && t.length > 10;
  }

  updateStoredUser(patch: { name?: string; email?: string }) {
    const raw = localStorage.getItem('pc_user');
    let u = { email: '', name: '' };

    try {
      if (raw) u = { ...u, ...JSON.parse(raw) };
    } catch {}

    const next = { ...u, ...patch };
    localStorage.setItem('pc_user', JSON.stringify(next));
  }
}
