package dataFactory;

import org.junit.jupiter.params.provider.Arguments;

import java.util.stream.Stream;

public class BookDataFactory {
    public static Stream<Arguments> isbnParameter() {
        return Stream.of(
                Arguments.of("9781449325862"),
                Arguments.of("9781449331818"),
                Arguments.of("9781449337711"),
                Arguments.of("9781449365035"),
                Arguments.of("9781491904244"),
                Arguments.of("9781491950296"),
                Arguments.of("9781593275846"),
                Arguments.of("9781593277574")
        );
    }
}
