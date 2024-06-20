package cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.domain.dtos.sql;

import cat.itacademy.barcelonactiva.cordero.claudio.s05.t02.dicegame.S05T02DiceGame.enums.Role;

public class UserDTO {

	private Integer userId;
	private String email;
	private String nickname;
	private String password;
	private String createdAt;
	private String updatedAt;
	private Role role;

	public UserDTO() {
		super();
	}

	public UserDTO(Integer userId, String email, String nickname, String password, String createdAt, String updatedAt,
			Role role) {
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(String updatedAt) {
		this.updatedAt = updatedAt;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "UserDTO [userId=" + userId + ", email=" + email + ", nickname=" + nickname + ", password=" + password
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", role=" + role + "]";
	}

}
