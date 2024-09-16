package entity;

import java.io.Serializable;

public class Team implements Serializable {
	private int teamId;
	private String name;
	private String representative;
	private String email;
	
	public Team() {}
	
	public Team(int teamId, String name, String representative, String email) {
		this.teamId = teamId;
		this.name = name;
		this.representative = representative;
		this.email = email;
	}
	
	public int getTeamId() { return teamId; }
	public void setTeamId(int teamId) { this.teamId = teamId; }


	public String getName() { return name; }
	public void setName(String name) { this.name = name; }

	public String getRepresentative() { return representative; }
	public void setRepresentative(String representative) { this.representative = representative; }
	
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }

}
