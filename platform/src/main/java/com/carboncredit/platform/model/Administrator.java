package com.carboncredit.platform.model;

import jakarta.persistence.*;
        import lombok.Getter;
        import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "Administrator")
public class Administrator {

    @Id
    @Column(name = "AdministratorID")
    private String administratorId;

    @Column(name = "AdministratorName")
    private String administratorName;

    @Column(name = "UserID")
    private String userId;
}
