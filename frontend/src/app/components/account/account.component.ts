import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AccountService, AccountDto } from '../../services/account.service';
import { AuthService } from '../../auth/auth.service';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-account',
  imports: [CommonModule, FormsModule],
  templateUrl: './account.component.html',
  styleUrls: ['./account.component.scss']
})
export class AccountComponent implements OnInit {
  loading = true;
  saving = false;
  deleting = false;

  email = '';
  name = '';

  error = '';
  success = '';

  showDeleteModal = false;
  deletePassword = '';

  constructor(
    private accountService: AccountService,
    private auth: AuthService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.load();
  }

  load(): void {
    this.loading = true;
    this.error = '';
    this.success = '';

    this.accountService.getAccount().subscribe({
      next: (me: AccountDto) => {
        this.email = me.email ?? '';
        this.name = me.name ?? '';
      },
      error: () => {
        this.error = 'Nu s-au putut încărca datele contului.';
      },
      complete: () => {
        this.loading = false;
      }
    });
  }

  save(): void {
    const trimmed = (this.name ?? '').trim();
    if (trimmed.length < 2) {
      this.error = 'Numele trebuie să aibă minim 2 caractere.';
      this.success = '';
      return;
    }

    this.saving = true;
    this.error = '';
    this.success = '';

    this.accountService.updateAccountName(trimmed).subscribe({
      next: (me: AccountDto) => {
        const newName = me.name ?? trimmed;
        this.name = newName;

        this.auth.updateStoredUser({ name: newName });
        this.success = 'Numele a fost actualizat.';
      },
      error: (e) => {
        this.error = e?.error?.message ?? 'Eroare la salvare.';
      },
      complete: () => {
        this.saving = false;
      }
    });
  }

  openDeleteModal(): void {
    this.showDeleteModal = true;
    this.deletePassword = '';
    this.error = '';
    this.success = '';
  }

  cancelDelete(): void {
    this.showDeleteModal = false;
    this.deletePassword = '';
    this.deleting = false;
  }

  confirmDelete(): void {
    const pw = (this.deletePassword ?? '').trim();
    if (!pw) {
      this.error = 'Introdu parola pentru confirmare.';
      return;
    }

    this.deleting = true;
    this.error = '';
    this.success = '';

    this.accountService.deleteAccount(pw).subscribe({
      next: () => {
        this.auth.logout();
        this.router.navigateByUrl('/products', { replaceUrl: true });
      },
      error: (e) => {
        this.error = e?.error?.message ?? 'Nu s-a putut șterge contul.';
        this.deleting = false;
      }
    });
  }
}
