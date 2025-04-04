package com.moviedatabase.Moviedatabaseusers;

import com.moviedatabase.Moviedatabaseusers.UserRepository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    // Se alla användare
    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    // Skapa användare
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userRepository.save(user);
    }

    @Autowired
    private WebClient.Builder webClientBuilder;

    // Uppdatera användare
    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User was not found under ID: " + id));

        existingUser.setUsername(updateUser.getUsername());
        existingUser.setGenre(updateUser.getGenre());

        return userRepository.save(existingUser);
    }


    // Söka användare
    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User was not found under ID: " + id));
    }

    // Söka reviews på användarid
    @GetMapping("reviews")
    public List<Review> getReviewsForUsers() {
        String reviewServiceUrl = "http://localhost:8080/reviews";
        WebClient webClient = webClientBuilder.build();
        List<Review> userReviews = webClient.get()
                .uri(reviewServiceUrl)
                .retrieve()
                .bodyToFlux(Review.class)
                .collectList()
                .block();

        if (userReviews == null) {
            throw new RuntimeException("No users with reviews found");
        }

        List <Review> reviews = userReviews;
        // Efter detta kan listan som returneras påverkas
        return reviews;

    }

    @GetMapping("/{id}/reviews")
    public List<Review> getUserReviews(@PathVariable Long id) {
        String reviewServiceUrl = "http://localhost:8080/reviews" + id;

        return webClientBuilder.build()
                .get()
                .uri(reviewServiceUrl)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Review>>() {})
                .block();
    }
}
