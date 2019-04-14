# Laboratoire RES SMTP

## Isaïa Spinelli et Tommy Gerardi

### Description
Dans le cadre d'un cours à l'HEIG-VD, nous devons implémenter un client SMTP qui permet de faire des pranks par email.

Le client prend une liste d'adresses mail ainsi que des messages (les pranks) et génère des groupes de minimum 3 personnes qui auront un pranker (qui envoie les pranks) et 2+ victimes qui les recevront. Il y a aussi un fichier de configuration permettant de choisir l'IP et le port du serveur SMTP, ainsi que le nombre de groupes à former et éventuellement des personnes à mettre en CC.

Pour plus d'information, vous pouvez allez voir la donnée du laboratoire : https://github.com/SoftEng-HEIGVD/Teaching-HEIGVD-RES-2019-Labo-SMTP

### Mise en place d'un serveur mock SMTP (avec Docker)

Un "fake" serveur SMTP a été mis en place sur Docker afin de pouvoir tester le client. Nous avons utilisé MockMock, c'est un serveur SMTP qui permet de tester l'envoi de mails sans pour autant réellement les envoyer aux victimes. Il dispose d'une interface graphique web qui permet de voir les mails envoyés ainsi que leur contenu.

Lien pour MockMock si intéressé : https://github.com/tweakers/MockMock

Afin d'utiliser notre fake serveur, veuillez vous en référer au mode d'emploi.

### Mode d'emploi de l'application

1. Clonez notre repo https://github.com/isaiaSpinelli/Teaching-HEIGVD-RES-2019-Labo-SMTP
2. Allez dans le dossier Docker_MockMock -> docker -> lancez le script build-image_server.sh
3. Installez Docker pour votre plateforme
4. Lancez Docker et exécutez la commande suivante : docker run -p 25:25 -h 8282:8282 server_mockmock
5. Vous pouvez changer les messages et destinataires ainsi que tout ce qui est adresse du serveur / port du serveur dans les fichiers du dossier config
6. Lancez le code (main de Lab2_smtp dans src) et il va envoyer vos pranks !



### Description de l'implémentation

Afin de parvenir à notre résultat, nous nous sommes fortement inspirés du code dans les podcasts de Liechti Sensei.



Nous avons 3 packages principaux :

- config
- model
- smtp

config contient tous les fichiers de configuration, c'est-à-dire liste de mails, messages et finalement les choix d'IP, du nombre de groupe etc...

model contient tout ce qui gère la création et distribution des emails. C'est ici qu'on traite les fichiers de configuration afin de les utiliser.

smtp contient le client SMTP qui s'occupe de gérer la communication en utilisant les syntaxes appropriées pour envoyer les messages aux différentes adresses.