package com.msproject.complaintresponse.entity;

import lombok.*;

import java.time.LocalDateTime;


@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class ComplaintDto {
    private String complaintId;
    private String userId;
    private String complaintDescription;
    private String title;
    private String complaintStatus; // Utilisation du type String pour les communications
    private String complaintType;   // Utilisation du type String pour les communications
    private LocalDateTime createdAt;

}
