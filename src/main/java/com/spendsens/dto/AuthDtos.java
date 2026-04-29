package com.spendsens.dto;

public class AuthDtos {

    public static class LoginRequest {
        private String email;
        private String password;
        public LoginRequest() {}
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public void setEmail(String email) { this.email = email; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RegisterRequest {
        private String name;
        private String email;
        private String password;
        public RegisterRequest() {}
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPassword() { return password; }
        public void setName(String name) { this.name = name; }
        public void setEmail(String email) { this.email = email; }
        public void setPassword(String password) { this.password = password; }
    }

    public static class RefreshTokenRequest {
        private String refreshToken;
        public RefreshTokenRequest() {}
        public String getRefreshToken() { return refreshToken; }
        public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
    }

    public static class AuthResponse {
        private String accessToken;
        private String refreshToken;
        private Long expiresIn;
        private UserDto user;

        public AuthResponse() {}
        public AuthResponse(String accessToken, String refreshToken, Long expiresIn, UserDto user) {
            this.accessToken = accessToken;
            this.refreshToken = refreshToken;
            this.expiresIn = expiresIn;
            this.user = user;
        }

        public String getAccessToken() { return accessToken; }
        public String getRefreshToken() { return refreshToken; }
        public Long getExpiresIn() { return expiresIn; }
        public UserDto getUser() { return user; }
        public void setAccessToken(String accessToken) { this.accessToken = accessToken; }
        public void setRefreshToken(String refreshToken) { this.refreshToken = refreshToken; }
        public void setExpiresIn(Long expiresIn) { this.expiresIn = expiresIn; }
        public void setUser(UserDto user) { this.user = user; }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private String accessToken, refreshToken;
            private Long expiresIn;
            private UserDto user;
            public Builder accessToken(String v) { this.accessToken = v; return this; }
            public Builder refreshToken(String v) { this.refreshToken = v; return this; }
            public Builder expiresIn(Long v) { this.expiresIn = v; return this; }
            public Builder user(UserDto v) { this.user = v; return this; }
            public AuthResponse build() { return new AuthResponse(accessToken, refreshToken, expiresIn, user); }
        }
    }

    public static class UserDto {
        private Long id;
        private String name;
        private String email;
        private String photoUrl;

        public UserDto() {}
        public UserDto(Long id, String name, String email, String photoUrl) {
            this.id = id; this.name = name; this.email = email; this.photoUrl = photoUrl;
        }

        public Long getId() { return id; }
        public String getName() { return name; }
        public String getEmail() { return email; }
        public String getPhotoUrl() { return photoUrl; }
        public void setId(Long id) { this.id = id; }
        public void setName(String name) { this.name = name; }
        public void setEmail(String email) { this.email = email; }
        public void setPhotoUrl(String photoUrl) { this.photoUrl = photoUrl; }

        public static Builder builder() { return new Builder(); }
        public static class Builder {
            private Long id;
            private String name, email, photoUrl;
            public Builder id(Long v) { this.id = v; return this; }
            public Builder name(String v) { this.name = v; return this; }
            public Builder email(String v) { this.email = v; return this; }
            public Builder photoUrl(String v) { this.photoUrl = v; return this; }
            public UserDto build() { return new UserDto(id, name, email, photoUrl); }
        }
    }
}
