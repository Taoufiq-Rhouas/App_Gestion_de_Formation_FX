package application;

public class Formation {
	private int id;
	private String libelle;
	private String description;
	private String ville;
	
	Formation(int id, String libelle, String description, String ville) {
		this.id = id;
		this.libelle = libelle;
		this.description = description;
		this.ville = ville;
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getDescription() {
		return description;
	}
	
	public String getVille() {
		return ville;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public void setVille(String ville) {
		this.ville = ville;
	}
}
