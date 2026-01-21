import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss',
})
export class LoginComponent {
  email = '';
  password = '';
  private returnUrl = '/products';
  loading = false;
  error: string | null = null;

  constructor(
    public auth: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '/products';
  }

  submit() {
    this.error = null;

    const email = this.email.trim().toLowerCase();
    if (!email || !this.password) {
      this.error = 'Completează email-ul și parola.';
      return;
    }

    this.loading = true;

    this.auth.login(email, this.password).subscribe({
      next: () => {
        const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '/';
        this.router.navigateByUrl(returnUrl);
      },
      error: (e) => {
        this.error =
          e?.error?.message ||
          'Nu s-a putut face autentificarea. Verifică datele și încearcă din nou.';
        this.loading = false;
      },
      complete: () => (this.loading = false),
    });
  }

  goRegister() {
    const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '/';
    this.router.navigate(['/auth/register'], { queryParams: { returnUrl } });
  }
}
