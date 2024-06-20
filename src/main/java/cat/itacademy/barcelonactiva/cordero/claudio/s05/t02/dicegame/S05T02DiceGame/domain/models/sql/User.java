package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.models.sql;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
 
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.enums.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
 
@Entity
@Table(schema = "dice", name = "users")
public class User implements UserDetails {
	
	private static final long serialVersionUID = 1L;
 
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")	
	private Integer userId;
	
	@NotBlank
	@NotNull
	@Email
	@Column(name = "email", unique = true)
	private String email;
	
	@NotNull
	@Column(name = "nickname")
	private String nickname;
	
	@NotBlank
	@NotNull
	@Column(name = "user_password")
	private String password;
	
	@Column(name = "created_at")
	private Timestamp createdAt;
	
	@Column(name = "updated_at")
	private Timestamp updatedAt;
 
	@Enumerated(EnumType.STRING)
	@Column(name = "role")
	private Role role;
	
	public User() {
		super();
	}
	
	public User(@NotBlank @NotNull @Email String email, @NotNull String nickname,
			@NotBlank @NotNull String password, Role role) {
		super();
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.role = role;
	}
 
	public User(Integer userId, @NotBlank @NotNull @Email String email, @NotNull String nickname,
			@NotBlank @NotNull String password, Timestamp createdAt, Timestamp updatedAt, Role role) {
		super();
		this.userId = userId;
		this.email = email;
		this.nickname = nickname;
		this.password = password;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.role = role;
	}
 
	public Integer getUserId() {
		return userId;
	}
 
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
 
	public String getEmail() {
		return email;
	}
 
	public void setEmail(String email) {
		this.email = email;
	}
 
	public String getNickname() {
		return nickname;
	}
 
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
 
	public Timestamp getCreatedAt() {
		return createdAt;
	}
 
	public void setCreatedAt(Timestamp createdAt) {
		this.createdAt = createdAt;
	}
 
	public Timestamp getUpdatedAt() {
		return updatedAt;
	}
 
	public void setUpdatedAt(Timestamp updatedAt) {
		this.updatedAt = updatedAt;
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
 
	@Override
	public String toString() {
		return "User [userId=" + userId + ", email=" + email + ", nickname=" + nickname + ", password=" + password
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", role=" + role + "]";
	}
 
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(role.name()));
	}
 
	@Override
	public String getPassword() {
		return password;
	}
 
	@Override
	public String getUsername() {
		return email;
	}
 
}
 
