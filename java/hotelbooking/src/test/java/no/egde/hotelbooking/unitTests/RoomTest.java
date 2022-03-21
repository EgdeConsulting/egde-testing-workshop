package no.egde.hotelbooking.unitTests;

import no.egde.hotelbooking.models.Room;
import no.egde.hotelbooking.models.RoomType;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class RoomTest {

    @Test
    public void shouldCreateRoom() {
        RoomType type = RoomType.BASIC;
        Room room = new Room(type);
        RoomType actualType = room.getRoomType();
        assertThat(type).isEqualTo(actualType);
    }
}
