package com.example.Filmrecensioner;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;
    private final WebClient webClient;


    private String movieServiceUrl = "http://localhost:8082/";

    public ReviewService(ReviewRepository reviewRepository, WebClient.Builder webClientBuilder) {
        this.reviewRepository = reviewRepository; // Lägg till detta för att initialisera reviewRepository
        this.webClient = webClientBuilder.build();
    }

    public List<Review> getAllReviews() {
        return reviewRepository.findAll();
    }

    public Review getReviewById(Long id) {
        return reviewRepository.findById(id).orElseThrow(() -> new RuntimeException("Review not found"));
    }

    public Review createReview(Review review) {
        String userServiceUrl = "http://localhost:8081/users" + review.getUserId();
        User user = webClient.get()
                .uri(userServiceUrl)
                .retrieve()
                .bodyToMono(User.class)
                .block();
        if (user == null) {
            throw new RuntimeException("User not found");
        }

        Mono<Movie> movieMono = webClient.get()
                .uri("/movies/" + review.getMovieId())
                .retrieve()
                .bodyToMono(Movie.class);

        Movie movie = movieMono.block();
        if (movie == null) {
            throw new RuntimeException("Movie not found");
        }

        return reviewRepository.save(review);
    }

    public Review updateReview(Long id, Review updatedReview) {
        Review existingReview = getReviewById(id);
        existingReview.setRating(updatedReview.getRating());
        existingReview.setComment(updatedReview.getComment());
        return reviewRepository.save(existingReview);
    }

    public List<Review> getReviewsForUser(@PathVariable Long id) {
        return reviewRepository.findByUserId(id);
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }
}
