Spotify Playlist Creator AI Project
This project encompasses a fundamental Spotify playlist creation AI. The central coordinating service is the playlist-conversion-service. Its primary function involves utilizing the fetch-playlist microservice to retrieve data and subsequently store it in the database. The Chat-Gpt-Service is then engaged to generate new playlists based on the previously fetched data. To ensure load distribution, the Netflix Naming Server is employed for load balancing purposes.

The microservices establish communication through a Feign client. API security is implemented using Spring Security, while the Spring Cloud Config Server is integrated for configuration management. Additionally, a basic API gateway example is included within the project architecture.
