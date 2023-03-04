Bienvenue dans ce jeu de bataille navale !

Ce jeu possède quelques limites : les fichiers de configurations doivent
être précisés afin que le jeu fonctionne. De plus même si l'intelligence
artificielle trouve un gagnant rapidement, elle ne joue qu'au hasard.
Lui fournir un peu plus d'intelligence pourrait être un plus, comme par
exemple chauqe fois qu'elle touche un navire, elle se souvient des
coordonnées et regarde autour de ces coordonnées lors du prochain coup.

BUG : La dernière ligne de la grille n'apparait pas quand la fenêtre
s'ouvre. Si des bateaux sont placés sur cette ligne ils peuvent ne pas
être visible, il faut donc penser à agrandir la fenêtre.

-------------------------------------------------------------------------

Vous trouverez ci-dessous toutes les informations nécessaires au bon
fonctionnement du jeu.

Vous pouvez placer vos fichiers de configuration dans le dossier data
situé à la racine du projet. Il existe 2 types de fichier de
configuration :

- Le fichier de configuration : c'est dans celui là que vous pouvez
  modifier la taille de la grille, le mode de jeu (Artificial vs
  Artificial, Humain vs Artificial ou Humain vs Humain), ainsi que les
  types des navires qui seront utilisés dans la partie.

- Les fichiers de placement des navires : c'est dans celui-ci que vous
  allez pouvoir placer vos bateaux en précisant leurs coordonnées ainsi
  que leur direction. Il est recommandé d'en faire deux, un pour chaque
  joueur humain. Les joueurs automatiques placent leurs bateaux
  automatiquement.

Le répertoire contenant ces fichiers doit être placé au même niveau que
le répertoire contenant le fichier JAR.

La commande pour lancer le jeu est la suivante :

java -jar battle_ark.jar ../data/config.txt nom1 nom2
