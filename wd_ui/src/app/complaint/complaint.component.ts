import { Component } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {Complaint} from "../core/models/complaint/Complaint";
import {ComplaintService} from "../core/services/complaint/ComplaintService";
import { AuthService } from '../service/user/auth.service';

@Component({
  selector: 'app-complaint',
  templateUrl: './complaint.component.html',
  styleUrls: ['./complaint.component.css']
})
export class ComplaintComponent {
  complaintForm: FormGroup;
  isLoading = false;
  loading = false;
  errorMessage !:string;
  isloading: boolean = false;
  complaints: Complaint[] = [];
  showPopup:boolean=false;
  popupTitle: string = '';
  popupMessage: string = '';

  complaintTypes = [
    { value: 'SERVICE_QUALITY', label: 'Service Quality' },
    { value: 'DELIVERY', label: 'Delivery' },
    { value: 'CONTRACT_ISSUES', label: 'Contract Issues' },
    { value: 'REFUND_ISSUES', label: 'Refund Issues' },
  ];

  constructor(private fb: FormBuilder,
              private complaintService: ComplaintService,private auth:AuthService) {
    this.complaintForm = this.fb.group({
      title: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(100)]],
      complaintType: ['', Validators.required],
      complaintDescription: ['', [Validators.required, Validators.minLength(5), Validators.maxLength(500)]],
    });
  }


  generateTitle() {
    const descriptionControl = this.complaintForm.get('complaintDescription');
    descriptionControl?.markAsTouched();
    descriptionControl?.markAsDirty();
    if (!descriptionControl || !descriptionControl.value) {
      return;
    }
    this.loading = true;
    const desc: string = descriptionControl.value;
    this.complaintService.getSuggestedTitle(desc).subscribe({
      next: (response:any) => {
        setTimeout(() => {
          console.log(response);
          this.complaintForm.get('title')?.setValue(response);
          this.loading = false;
        }, 700);
      },
      error: (err:any) => {
        console.error('Error fetching title:', err);
        this.errorMessage = 'Failed to generate a title. Try again.';
      }
    });
  }

  saveComplaint() {
    if (this.complaintForm.invalid) {
      this.complaintForm.markAllAsTouched();
      return;
    }

    this.isloading = true;

    setTimeout(() => {
      const complaint: Complaint = this.complaintForm.value;
      // this.auth.getUserId()||'null'
      this.complaintService.addComplaint("67f3cb886874c45776d2e0a6", complaint).subscribe({
        next: () => {
          this.complaintForm.reset();
          this.showPopup = true;
          this.popupTitle = "Complaint Received";
          this.popupMessage = "Thank you! We have received your complaint and will get back to you soon.";
        },
        error: (err:any) => {
          console.error("Error submitting complaint:", err);

          if (err.error && err.error.error) {
            this.errorMessage = err.error.error;
            const parts = this.errorMessage.split("No. ");
            this.popupMessage = parts.length > 1 ? parts[1].split(' A more appropriate')[0] : this.errorMessage;
            this.popupTitle = "Invalid Complaint Submission";
            this.isloading = false;
            this.showPopup = true;
          } else {
            this.errorMessage = "An unexpected error occurred. Please try again later.";
          }
        },
        complete: () => {
          this.isloading = false;
        }
      });
    }, 5000);
  }
  onPopupClose() {
    this.showPopup = false;
  }
}
