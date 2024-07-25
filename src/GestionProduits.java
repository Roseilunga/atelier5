import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

// Classe de base Produit
class Produit {
    protected int id;
    protected String nom;
    protected int quantite;

    public Produit(int id, String nom, int quantite) {
        this.id = id;
        this.nom = nom;
        this.quantite = quantite;
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
}

// Classe dérivée Electronique
class Electronique extends Produit {
    private String marque;
    private String modele;

    public Electronique(int id, String nom, int quantite, String marque, String modele) {
        super(id, nom, quantite);
        this.marque = marque;
        this.modele = modele;
    }

    public String getMarque() {
        return marque;
    }

    public String getModele() {
        return modele;
    }
}

// Classe dérivée Alimentaire
class Alimentaire extends Produit {
    private String dateExpiration;

    public Alimentaire(int id, String nom, int quantite, String dateExpiration) {
        super(id, nom, quantite);
        this.dateExpiration = dateExpiration;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }
}

// Classe dérivée Vestimentaire
class Vestimentaire extends Produit {
    private String taille;
    private String couleur;

    public Vestimentaire(int id, String nom, int quantite, String taille, String couleur) {
        super(id, nom, quantite);
        this.taille = taille;
        this.couleur = couleur;
    }

    public String getTaille() {
        return taille;
    }

    public String getCouleur() {
        return couleur;
    }
}

// Classe Magasin
class Magasin {
    Map<Integer, Produit> produits;

    public Magasin() {
        produits = new HashMap<>();
    }

    public void ajouterProduit(Produit produit) {
        produits.put(produit.getId(), produit);
    }

    public void supprimerProduit(int id) {
        produits.remove(id);
    }

    public void modifierProduit(int id, Produit nouveauProduit) {
        produits.put(id, nouveauProduit);
    }

    public Produit rechercherProduit(String nom) {
        for (Produit produit : produits.values()) {
            if (produit.getNom().equalsIgnoreCase(nom)) {
                return produit;
            }
        }
        return null;
    }

    public void listerProduits(char lettre) {
        for (Produit produit : produits.values()) {
            if (produit.getNom().toUpperCase().startsWith(String.valueOf(lettre).toUpperCase())) {
                System.out.println(produit);
            }
        }
    }

    public int getNombreProduits() {
        return produits.size();
    }
}

public class GestionProduits {
    public static void main(String[] args) {
        Magasin magasin = new Magasin();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Choisissez une option :");
            System.out.println("1. Ajouter un produit");
            System.out.println("2. Supprimer un produit");
            System.out.println("3. Modifier un produit");
            System.out.println("4. Rechercher un produit");
            System.out.println("5. Lister les produits");
            System.out.println("6. Afficher le nombre de produits");
            System.out.println("7. Quitter");

            int choix = scanner.nextInt();
            scanner.nextLine(); // Consommer le retour à la ligne

            switch (choix) {
                case 1:
                    System.out.println("Entrez le type de produit (Electronique, Alimentaire, Vestimentaire) :");
                    String typeProduit = scanner.nextLine();

                    System.out.println("Entrez l'ID du produit :");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Entrez le nom du produit :");
                    String nom = scanner.nextLine();

                    System.out.println("Entrez la quantité :");
                    int quantite = scanner.nextInt();
                    scanner.nextLine();

                    Produit nouveauProduit;
                    switch (typeProduit.toLowerCase()) {
                        case "electronique":
                            System.out.println("Entrez la marque :");
                            String marque = scanner.nextLine();
                            System.out.println("Entrez le modèle :");
                            String modele = scanner.nextLine();
                            nouveauProduit = new Electronique(id, nom, quantite, marque, modele);
                            break;
                        case "alimentaire":
                            System.out.println("Entrez la date d'expiration :");
                            String dateExpiration = scanner.nextLine();
                            nouveauProduit = new Alimentaire(id, nom, quantite, dateExpiration);
                            break;
                        case "vestimentaire":
                            System.out.println("Entrez la taille :");
                            String taille = scanner.nextLine();
                            System.out.println("Entrez la couleur :");
                            String couleur = scanner.nextLine();
                            nouveauProduit = new Vestimentaire(id, nom, quantite, taille, couleur);
                            break;
                        default:
                            System.out.println("Type de produit invalide.");
                            continue;
                    }

                    magasin.ajouterProduit(nouveauProduit);
                    System.out.println("Produit ajouté avec succès.");
                    break;

                case 2:
                    System.out.println("Entrez l'ID du produit à supprimer :");
                    int idSuppression = scanner.nextInt();
                    scanner.nextLine();
                    magasin.supprimerProduit(idSuppression);
                    System.out.println("Produit supprimé avec succès.");
                    break;

                case 3:
                    System.out.println("Entrez l'ID du produit à modifier :");
                    int idModification = scanner.nextInt();
                    scanner.nextLine();

                    System.out.println("Entrez le nouveau nom du produit :");
                    String nouveauNom = scanner.nextLine();

                    System.out.println("Entrez la nouvelle quantité :");
                    int nouvelleQuantite = scanner.nextInt();
                    scanner.nextLine();

                    Produit produitModifie;
                    Produit produitInitial = magasin.produits.get(idModification);
                    if (produitInitial instanceof Electronique) {
                        Electronique produitElectronique = (Electronique) produitInitial;
                        produitModifie = new Electronique(idModification, nouveauNom, nouvelleQuantite, produitElectronique.getMarque(), produitElectronique.getModele());
                    } else if (produitInitial instanceof Alimentaire) {
                        Alimentaire produitAlimentaire = (Alimentaire) produitInitial;
                        produitModifie = new Alimentaire(idModification, nouveauNom, nouvelleQuantite, produitAlimentaire.getDateExpiration());
                    } else if (produitInitial instanceof Vestimentaire) {
                        Vestimentaire produitVestimentaire = (Vestimentaire) produitInitial;
                        produitModifie = new Vestimentaire(idModification, nouveauNom, nouvelleQuantite, produitVestimentaire.getTaille(), produitVestimentaire.getCouleur());
                    } else {
                        produitModifie = new Produit(idModification, nouveauNom, nouvelleQuantite);
                    }

                    magasin.modifierProduit(idModification, produitModifie);
                    System.out.println("Produit modifié avec succès.");
                    break;

                case 4:
                    System.out.println("Entrez le nom du produit à rechercher :");
                    String nomRecherche = scanner.nextLine();
                    Produit produitRecherche = magasin.rechercherProduit(nomRecherche);
                    if (produitRecherche != null) {
                        System.out.println("Produit trouvé : " + produitRecherche);
                    } else {
                        System.out.println("Aucun produit trouvé avec ce nom.");
                    }
                    break;

                case 5:
                    System.out.println("Entrez la première lettre du nom des produits à afficher :");
                    char lettreRecherche = scanner.nextLine().charAt(0);
                    magasin.listerProduits(lettreRecherche);
                    break;

                case 6:
                    System.out.println("Nombre de produits en stock : " + magasin.getNombreProduits());
                    break;

                case 7:
                    System.out.println("Au revoir !");
                    return;

                default:
                    System.out.println("Option invalide.");
                    break;
            }

            System.out.println();
        }
    }
}
