package ru.teamscore.randomizer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class RandomizerServiceTest {

    @Test
    void valueOf_whenInvalidType_throwIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> RandomizerService.valueOf(0),
            "Invalid generator type");
        assertThrows(IllegalArgumentException.class, () -> RandomizerService.valueOf(4),
            "Invalid generator type");
    }

    @Test
    void valueOf_whenValidType_returnRandomizerService() {
        assertDoesNotThrow(() -> RandomizerService.valueOf(1));
        assertDoesNotThrow(() -> RandomizerService.valueOf(2));
        assertDoesNotThrow(() -> RandomizerService.valueOf(3));
    }

    @Test
    void generateJson_returnsValidJson() {
        RandomizerService service = RandomizerService.valueOf(1);
        String json = service.generateJson(10);
        assertNotNull(json);
        assertTrue(json.startsWith("["));
        assertTrue(json.endsWith("]"));
    }
}
