# Dukes of the Realm

_Développé par Théo Battrel et Nicolas Moignot, étudiants en L3 Informatique à l’Université de Bordeaux._

## Fonctionnalités

- *Différents niveaux pour les châteaux.*
- *Revenu automatique à chaque tour.*
- *3 types de troupes :* piquier, chevalier et onagre.
- *Utilisation des portes :* les troupes partent de la porte (un certain nombre dépendant à la fois de la taille de la porte, les troupes s’attendent), puis rentrent dans le château adverse par sa porte.
- *Troupes qui évitent les autres châteaux.*
- *"Intelligence artificielle" :* les autres ducs jouent également.
- *Intégration de la pause (touche Espace).*
- *Barre de status :* contenant les informations du château sélectionné, les actions disponibles et le tour courant.
- *Différenciation du château du joueur des autres châteaux.*
- *Documentation JavaDoc.*
- *Implémentation basée sur une architecture Modèle-Vue-Contrôleur.*
- *Personnalisation :* beaucoup d’éléments du jeu peuvent être modifiés dans utils.Settings, comme la couleur des éléments, la situation initiale des châteaux, le nombre de joueurs, la taille des cases, le texte…

## Règle du jeu

- Dans ce jeu, différents ducs attaquent les châteaux des autres ducs afin de devenir le nouveau roi.
- Au début de la partie, chaque duc a un château. Les châteaux ont un niveau, un trésor (en florins), une réserve de troupes et une unité de production (qui permet de créer une troupe pour l’armée ou d’améliorer le niveau du château).
- À chaque tour, les châteaux produisent un revenu proportionnel à leur niveau.
- Les châteaux ne peuvent envoyer qu’une armée (une sélection de troupes de sa réserve) à la fois pour attaquer un autre château ou le rejoindre. Si l’armée attaque, des points de vies sont retirés à des troupes du château adverse pour chaque point d’attaque de l’armée. Les troupes disparaissent dès qu’elles n’ont plus de points de vie ou d’attaque. Si le château adverse ne possède plus de troupe, alors il est conquis par le duc qui a envoyé son armée.
- Il y a trois types de troupes : les piquiers, les chevaliers et les onagres. Chaque type a une vitesse de déplacement, des points d’attaque, des points de vies différents, un coût et un temps de production différent.
- La partie se termine dès que le joueur n’a plus de château (il perd) ou dès que tous les châteaux lui appartiennent (il gagne).

## Fonctionnement du jeu

- Pour sélectionner un château, cliquer dessus. Pour le déselectionner (avant d’en sélectionner un autre), cliquer une seconde fois dessus.
- Pour lancer une attaque, sélectionner le château attaquant (appartenant au joueur), puis cliquer sur le château adverse.
- Pour lancer une production, sélectionner le château, puis appuyer sur "Production +". Une fenêtre s’ouvre alors pour permettre au joueur de sélectionner le type de production qu’il souhaite.
- Pour annuler la dernière production de la liste, sélectionner le château, puis appuyer sur le bouton "Production -".
- Pour annuler toutes les productions, il fait sélectionner le château, puis appuyer sur le bouton "Production C".
- Pour mettre le jeu en pause, appuyer sur la touche Espace.

## Bugs connus

- Le jeu se bloque dès qu’un château est conquis.
- Impossible de lancer une attaque après la première lancée.
- Impossible de sélectionner un château entre le point (0, 0) de la fenêtre et une troupe affichée.

## Améliorations possibles

- Correction des bugs connus.
- Ajout de la sauvegarde de la partie (début d’implémentation).
- Rendu 3D avec déplacement sur la carte, plutôt qu’une carte prenant toute la fenêtre.