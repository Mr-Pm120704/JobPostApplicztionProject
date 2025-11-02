package com.ZidioIntern.DTO;

import com.ZidioIntern.Entity.User;
import com.ZidioIntern.Enum.Role;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDTO {

    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    private String userName;

    @JsonProperty("email")
    private String userEmail;

    private String password;
    private Role role;

    private boolean active = true;

    // ✅ This computed getter defines "status" in JSON output
    @JsonProperty("status")
    public String getStatus() {
        return active ? "active" : "blocked";
    }

    public static UserDTO fromEntity(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setUserEmail(user.getUserEmail());
        dto.setRole(user.getRole());
        dto.setActive(user.isActive());
        return dto;
    }

    // --- Getters and setters ---
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
