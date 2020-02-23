[x] un fichier README.txt (ou .md) à la racine du projet

[x] un fichier maven pour le build du projet

[x] les sources (fichiers Java)

[x] la documentation javadoc de nos classes

[x] les fichiers natifs de votre modélisation UML ( outil: StarUML )

[x] le rapport en PDF 

Modifiez la classe Player ou Field pour :

	[x] que les touches de contrôle correspondent à un clavier AZERTY plutôt que QWERTY, i.e. ZQSD au lieu de WASD.
	[x] que la vitesse (la taille du pas) du Joueur soit variable (que cela deviennent une propriété du joueur spécifié à sa création).
	[x] que chaque joueur utilise une touche différente pour tirer (au lieu d'ESPACE pour les deux joueurs).

Ajouter quelques méthodes à Player et Field :

	[x] Créer deux listes de joueurs correspondants à deux équipes (au lieu d'une liste).
	[x] Utiliser l'héritage pour faire une distinction entre joueurs contrôlés par un humain et par l'ordinateur.
	[x] Pour l'instant les joueurs controlés par l'ordinateur restent statiques.
	[x] Faire en sorte qu'une partie oppose 3 joueurs (1 humain, 2 ordinateurs) de chaque côté.

Tirs

	[x] Créer une classe projectile, créée lors d'un tir, avec une vitesse et une direction.
	[x] Associer une représentation graphique à cette classe (ajouter l'image ball.png dans le dossier assets).
	[x] S'assurer que le projectile se déplace bien lors de la boucle principale du jeu.

Collision

	[x] le projectile s'arrête dans le camps opposé, soit en tombant au sol, soit en touchant un joueur.
	[x] le projectile teste s'il rencontre un joueur adverse. Pour cela 1. créer une méthode testant si deux rectangles se chevauchent ; 2. rajouter une méthode permettant de facilement récupérer les coordonnées de la bounding box (du rectangle englobant) des projectiles et des joueurs ; 3. À chaque déplacement du projectile tester s'il rencontre un joueur adverse.
	[ ] le joueur adverse disparaisse du jeu s'il est touché. Pour cela rajouter un attribut à la classe Player.

Les collisions peuvent être entre :

	[x] une balle et un joueur
	[x] une balle et les murs
	[x] une balle et un obstacle

Chaque joueur avec une IA aura une tactique propre qui consiste à :

	[x] se déplacer aléatoirement
	[ ] suivre le joueur contrôlé par un humain

Toute autre stratégie est possible.

	[ ] Faites en sorte que la tactique puisse être changée en cours de partie (via une action utilisateur sur un bouton par exemple).

Pour suivre et contrôler le jeu, on disposera de plusieurs vues:

	[ ] une présentant le terrain
	[ ] une autre présentant le score
	[ ] une autre les contrôles du jeu

Qualité de la réalisation Patterns utilisés

	[x] MVC
	[x] 2/3 Design Pattern: Command - Abstract Factory

Test collision

	[x] character et balle
	[x] obstacle et balle
	[x] mur et balle

Comment jouer?
	
	Player Red : les touches de contrôle : ZQSD 		- tirer : SPACE
	Player Blue: les touches de contrôle : les flèches 	- tirer : F
	Les joueurs ne contrôleraient plus leurs characters si les Players sont frappés par leurs concurrents
