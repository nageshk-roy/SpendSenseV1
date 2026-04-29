/*
package com.spendsens.entity;

import jakarta.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Id
    @Column(length = 36)
    private String id;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String name;

    */
/*@Column(name = "photo_url")
    private String photoUrl;*//*


    @Column(length = 10)
    private String currency = "INR";

    @Column(name = "monthly_budget")
    private Double monthlyBudget = 0.0;

    @Column(name = "is_notification_enabled")
    private Boolean isNotificationEnabled = true;

    @Column(name = "is_sms_enabled")
    private Boolean isSmsEnabled = true;

    @Column(name = "google_id")
    private String googleId;

    @Column(name = "refresh_token")
    private String refreshToken;

    @Column(name = "is_active")
    private Boolean isActive = true;

    public User() {
    }

    @PrePersist
    public void prePersist() {
        if (id == null) {
            id = UUID.randomUUID().toString();
        }
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public Double getMonthlyBudget() {
        return monthlyBudget;
    }

    public void setMonthlyBudget(Double monthlyBudget) {
        this.monthlyBudget = monthlyBudget;
    }

    public Boolean getNotificationEnabled() {
        return isNotificationEnabled;
    }

    public void setNotificationEnabled(Boolean notificationEnabled) {
        isNotificationEnabled = notificationEnabled;
    }

    public Boolean getSmsEnabled() {
        return isSmsEnabled;
    }

    public void setSmsEnabled(Boolean smsEnabled) {
        isSmsEnabled = smsEnabled;
    }

    public String getGoogleId() {
        return googleId;
    }

    public void setGoogleId(String googleId) {
        this.googleId = googleId;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }
}*/
