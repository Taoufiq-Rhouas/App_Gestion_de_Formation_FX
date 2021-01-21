package application;

public class Employee {
	private int id;
	private String libelle;
	private String ville;
	private String nom;
	private String prenom;
	
	Employee(int id, String libelle, String ville, String nom, String prenom) {
		this.id = id;
		this.libelle = libelle;
		this.ville = ville;
		this.nom = nom;
		this.prenom = prenom;
	}

	public int getId() {
		return id;
	}

	public String getLibelle() {
		return libelle;
	}

	public String getVille() {
		return ville;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

	public void setVille(String ville) {
		this.ville = ville;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
