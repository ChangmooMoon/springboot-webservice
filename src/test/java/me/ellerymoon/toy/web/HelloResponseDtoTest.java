package me.ellerymoon.toy.web;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import me.ellerymoon.toy.web.dto.HelloResponseDto;
import org.junit.jupiter.api.Test;

class HelloResponseDtoTest {

    @Test
    public void 롬복_테스트() {
        // Given
        String name = "test";
        int amount = 1000;

        // When
        HelloResponseDto dto = new HelloResponseDto(name, amount);

        // Then
        assertThat(dto.getName()).isEqualTo(name);
        assertThat(dto.getAmount()).isEqualTo(amount);
    }
}
