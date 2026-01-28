import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { AuthService } from '../../auth/auth.service';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [CommonModule, FormsModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.scss',
})
export class RegisterComponent {
  name = '';
  email = '';
  password = '';
  confirmPassword = '';

  loading = false;
  error: string | null = null;

  constructor(
    private auth: AuthService,
    private router: Router,
    private route: ActivatedRoute
  ) {}

  submit() {
    this.error = null;

    const name = this.name.trim();
    const email = this.email.trim().toLowerCase();

    if (!name || !email || !this.password) {
      this.error = 'Completează numele, email-ul și parola.';
      return;
    }
    if (this.password !== this.confirmPassword) {
      this.error = 'Parolele nu coincid.';
      return;
    }
    if (this.password.length < 6) {
      this.error = 'Parola trebuie să aibă cel puțin 6 caractere.';
      return;
    }

    this.loading = true;

    this.auth.register(name, email, this.password).subscribe({
      next: () => {
        const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '/';
        this.router.navigateByUrl(returnUrl);
      },
      error: (e) => {
        this.error =
          e?.error?.message ||
          e?.error?.detail ||
          (typeof e?.error === 'string' ? e.error : null) ||
          'Nu s-a putut crea contul. Încearcă din nou.';
        this.loading = false;
      },
      complete: () => (this.loading = false),
    });
  }

  goLogin() {
    const returnUrl = this.route.snapshot.queryParamMap.get('returnUrl') || '/';
    this.router.navigate(['/auth/login'], { queryParams: { returnUrl } });
  }
}
