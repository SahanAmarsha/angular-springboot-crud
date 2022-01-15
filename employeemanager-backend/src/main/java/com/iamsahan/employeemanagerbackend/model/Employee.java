package com.iamsahan.employeemanagerbackend.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity // Creates a database table
public class Employee implements Serializable
{
    @Id // Defining the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false) // Cannot be updated, or null
    private Long id;
    private String name;
    private String email;
    private String jobTitle;
    private String contactNumber;
    private String imageUrl;
    @Column(nullable = false, updatable = false) // Cannot be updated, or null
    private String employeeCode;


    public Employee( String name, String email, String jobTitle, String contactNumber, String imageUrl, String employeeCode )
    {
        this.name = name;
        this.email = email;
        this.jobTitle = jobTitle;
        this.contactNumber = contactNumber;
        this.imageUrl = imageUrl;
        this.employeeCode = employeeCode;
    }

    public Employee()
    {

    }

    public Long getId()
    {
        return id;
    }

    public void setId( Long id )
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName( String name )
    {
        this.name = name;
    }

    public String getEmail()
    {
        return email;
    }

    public void setEmail( String email )
    {
        this.email = email;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }

    public void setJobTitle( String jobTitle )
    {
        this.jobTitle = jobTitle;
    }

    public String getContactNumber()
    {
        return contactNumber;
    }

    public void setContactNumber( String contactNumber )
    {
        this.contactNumber = contactNumber;
    }

    public String getImageUrl()
    {
        return imageUrl;
    }

    public void setImageUrl( String imageUrl )
    {
        this.imageUrl = imageUrl;
    }

    public String getEmployeeCode()
    {
        return employeeCode;
    }

    public void setEmployeeCode( String employeeCode )
    {
        this.employeeCode = employeeCode;
    }
}
