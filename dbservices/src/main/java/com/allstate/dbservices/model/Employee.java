package com.allstate.dbservices.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "Employee")
public class Employee extends AuditModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "first_name")
    @NotBlank
    @Size(min = 4, max = 40)
    private String firstName;

    @Column(name = "last_name")
    @NotBlank
    @Size(min = 4, max = 40)
    private String lastName;

    @Column(name = "date_of_birth")
    @NotBlank
    private String dateOfBirth;

    @Column(name = "hiring_date")
    @NotBlank
    private String hiringDate;

    @Column(name = "province")
    @NotBlank
    private String province;

    @Column(name = "postal_code")
    @NotBlank
    private String postalCode;

    @Column(name = "username")
    @JsonIgnore
    private String username;

    @Column(name = "email")
    private String email;

    @Column(name = "password")
    @JsonIgnore
    private String password;

    @Column (name = "salary")
    private Long salary;

    @Column (name = "bonus")
    private Long bonus;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Employee_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "Employee_jobPostings",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "jobposting_id"))
    private List<JobPostings> Jobpostings = new ArrayList<>();

   
}
