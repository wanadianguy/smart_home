# back
## Team
- Geoffrey Auzou
- Amandine Carlier
- Maxime Fremeau
- William Denorme
- Bryan Moreau
## Lancement du Docker
- `docker build -t db_capteur -f .\dockerfile.db .`
- `docker run -d --name mongo_db -p 27017:27017 db_capteur`
## Lancement en local
Pour ce processus, il faut que la base de données (et potentiellement le broker) soient lancés.
- `mvn clean install package -DskipTests`
- `java -jar -Dspring.profiles.active=local target/smart-home-controller-0.0.1-SNAPSHOT.jar`
