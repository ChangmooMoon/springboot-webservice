package me.ellerymoon.toy.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import me.ellerymoon.toy.domain.posts.Posts;
import me.ellerymoon.toy.domain.posts.PostsRepository;
import me.ellerymoon.toy.web.dto.PostsSaveRequestDto;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PostsApiControllerTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    PostsRepository postsRepository;

    @AfterEach
    void tearDown() {
        postsRepository.deleteAll();
    }

    @Test
    public void posts_등록() {
        // Given
        String title = "테스트 포스트";
        String content = "테스트 콘텐트";
        PostsSaveRequestDto requestDto = PostsSaveRequestDto.builder()
            .title(title).content(content).author("test author").build();

        String url = "http://localhost:" + port + "/api/v1/posts";

        // When
        ResponseEntity<Long> responseEntity = restTemplate.postForEntity(url, requestDto,
            Long.class);

        // Then
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody()).isGreaterThan(0L);

        List<Posts> allPosts = postsRepository.findAll();
        assertThat(allPosts.get(0).getTitle()).isEqualTo(title);
        assertThat(allPosts.get(0).getContent()).isEqualTo(content);
    }
}
