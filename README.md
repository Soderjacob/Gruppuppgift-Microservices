# Gruppuppgift-Microservices
Kör hela scriptet som ligger i mappen sql_script för att skapa databaserna och exempeldatan för att kunna testa endpoints.
För att kunna testa måste både applikationen i Filmrecensioner-main/src köras samt applikationen i MoviedatabaseUsers/src.
Efter detta kan man anropa med postman på det olika enpointsen.
Mitt projekt är det som heter MoviedatabaseUsers och den enpointen som anropar en annan API är getUserReviews().
MoviedatabaseUsers är uppkopplad på port 8081.
API som anropas är uppkopplad på 8080.

 
Beskrivning av endpoints:
- GET /users:- Vad den gör: Hämtar en lista med alla användare.
- Postman: Skicka en GET-förfrågan till endpointen /users.

- POST /users:- Vad den gör: Skapar en ny användare.
- Postman: Skicka en POST-förfrågan till /users och inkludera användarens data i JSON-format i request body.
- {
  "username": "exampleUser",
  "reviews": "This is a review.",
  "genre": "Favorite genre"
}

- PUT /users/{id}:- Vad den gör: Uppdaterar en befintlig användare baserat på ID.
- Postman: Skicka en PUT-förfrågan till /users/{id} och inkludera den uppdaterade användardata i JSON-format i request body.
- {
  "id": 1,
  "username": "updatedUser",
  "reviews": "This is an updated review.",
  "genre": "Favorite genre"
}

- GET /users/{id}:- Vad den gör: Hämtar en specifik användare baserat på ID.
- Postman: Skicka en GET-förfrågan till /users/{id} och ersätt {id} med det aktuella ID:t för användaren.

- GET /users/reviews:- Vad den gör: Hämtar användare som skrivit recensioner via en extern tjänst.
- Postman: Skicka en GET-förfrågan till /users/reviews. Observera att denna endpoint använder WebClient för att interagera med en annan tjänst på URL:en http://localhost:8080/reviews.
- Skicka en get till http://localhost:8081/users/reviews
