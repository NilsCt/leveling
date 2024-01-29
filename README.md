# leveling

Leveling est un bot discord qui attribue de l'expérience aux membres de serveurs discord en fonction de leur activité.

### Sommaire

1. [Présentation générale](#presentation)
2. [Comment utiliser le bot ?](#installation)
3. [Informations pour les administrateurs](#informations)

###  

### Attention : ce projet n'est pas le même que leveling-multiserver.

Il s'agit en réalité d'une version allégée d'un ancien bot dont le code est disponible dans le projet Github
leveling-multiserver.  
Pour cette nouvelle version, l'hébergement et le stockage des données n'est pas pris en compte.  
De plus, cette version est conçue pour être utilisée dans un seul serveur à la fois.  
[Plus d'informations](#histoire)

### Attention : le code du bot n'est plus mis à jour depuis septembre 2023

##  

## <a id="presentation"></a>Présentation du bot

![one](https://scoutant.org/leveling/images/one.png)
![two](https://scoutant.org/leveling/images/two.png)
![three](https://scoutant.org/leveling/images/three.png)
![four](https://scoutant.org/leveling/images/four.png)
![five](https://scoutant.org/leveling/images/five.png)
![six](https://scoutant.org/leveling/images/six.png)
![seven](https://scoutant.org/leveling/images/seven.png)

## <a id="installation"></a> Comment utiliser le bot ?

1. Créer le bot Discord.  
   Il faut d'abord créer le compte du bot sur le
   site [Discord Developper Portal](https://discord.com/developers/applications) et l'ajouter sur son serveur.  
   On peut suivre les 5 premières étapes
   du [tutoriel de JDA](https://github.com/discord-jda/JDA/wiki/3%29-Getting-Started)
2. Télécharger ce projet Github.
3. Compléter le fichier config.txt.  
   Il faut entrer le token du bot, l'identifiant du serveur, l'identifiant des administrateurs, l'URL d'un Discord
   Webhook et une clé de code en base64.  
   (On peut obtenir ces identifiants directement sur Discord en activant l'option de développeur et avec un clic droit
   sur les utilisateurs concernés.)  
   Le Webhook peut être créé dans les paramètres d'un channel Discord. Il permet ici d'envoyer les logs du bot
   directement sur Discord.
4. Héberger et lancer le bot.  
   Le bot créé automatiquement au démarrage les slash-commandes, mais celles-ci peuvent mettre plusieurs heures à
   apparaître (le délai vient de Discord).

## <a id="informations"></a> Informations pour les administrateurs

Les logs sont envoyées directement sur Discord grâce au Webhook.  
Les données sont sauvegardées automatiquement chaque jour à midi.  
Les administrateurs dont les identifiants ont été entrés dans le fichier config.txt (avant le démarrage) peuvent
utiliser des commandes supplémentaires telles que /load /save /backup et /activity.  
Les données sont stockées dans un fichier json et cryptées en base64 avec la clé fournie dans le fichier config.  
Attention à ne pas modifier la clé base64 après utilisation, les données seraient alors perdues.  
Le comportement du bot est configurable directement sur Discord notamment avec le bouton set-up accessible après la
commande /help.

## <a id="histoire"></a> Histoire

Leveling était à l'origine un bot publié sur le portail des applications de Discord et sur top.gg.  
Les utilisateurs n'avaient pas besoin d'héberger le bot ni de s'occuper du stockage des données.  
Le bot a pris de l'ampleur : il a été certifié par Discord et a été ajouté dans plus de 1200 serveurs Discord.  
Il recevait à son pic autour de 600 interactions par jour.  
Mais le bot n'était pas fait pour stocker et traquer les données de plusieurs milliers d'utilisateurs Discord (pas
forcément utilisateurs du bot).  
La gestion des données était très basique et se basait seulement sur l'écriture et la lecture d'un fichier json.  
Je n'avais pas le temps de continuer le développement du bot et j'ai donc été contraint d'arrêter sa publication.

Ainsi, ce projet Github est une version simplifiée de cet ancien bot Discord.  
Les utilisateurs sont responsables de l'hébergement du bot et du stockage de leurs données.  
Cette version est adaptée aux modérateurs qui veulent ajouter le bot sur leur serveur Discord.  
Mais elle ne convient plus à l'ajout du bot dans un grand nombre de serveurs.  
De nombreuses fonctionnalités ont été enlevées notamment celles liées aux classements des serveurs.  
Pour voir le code original du bot Discord, il faut aller voir le projet Github leveling-multiserver.

Le bot est plus vieux que la date de publication de ce projet sur Github. Il date en réalité de 2020, mais a été mis à
jour jusqu'en septembre 2023.  