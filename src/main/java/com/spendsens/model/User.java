package com.spendsens.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;
    private String photoUrl;

    @Column(nullable = false)
    private String provider = "LOCAL";

    /*@CreationTimestamp
    private LocalDateTime createdAt;*/

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Budget> budgets;

    public User() {}

    public User(Long id, String name, String email, String password, String photoUrl, String provider) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.photoUrl = photoUrl;
        this.provider = provider != null ? provider : "LOCAL";
    }

    // Getters
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
  public String getPhotoUrl() { return photoUrl; }
    public String getProvider() { return provider; }
   /* public LocalDateTime getCreatedAt() { return createdAt; }*/
    public List<Transaction> getTransactions() { return transactions; }
    public List<Budget> getBudgets() { return budgets; }

    // Setters
    public void setId(Long id) { this.id = id; }
    public void setName(String name) { this.name = name; }
    public void setEmail(String email) { this.email = email; }
    public void setPassword(String password) { this.password = password; }
    public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }
    public void setProvider(String provider) { this.provider = provider; }

    // Builder
    public static Builder builder() { return new Builder(); }

    public static class Builder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private String photoUrl;
        private String provider = "LOCAL";

        public Builder id(Long id) { this.id = id; return this; }
        public Builder name(String name) { this.name = name; return this; }
        public Builder email(String email) { this.email = email; return this; }
        public Builder password(String password) { this.password = password; return this; }
        public Builder photoUrl(String photoUrl) { this.photoUrl = photoUrl; return this; }
        public Builder provider(String provider) { this.provider = provider; return this; }

        public User build() {
            return new User(id, name, email, password, photoUrl, provider);
        }
    }
}
