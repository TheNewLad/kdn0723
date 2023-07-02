package datasource;

import static org.junit.jupiter.api.Assertions.*;

import model.Tool;
import model.ToolType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.DayChecker.DayType;

public class ToolStoreTest {
    ToolStore toolStore;

    @BeforeEach
    public void setUp() {
        toolStore = ToolStore.getInstance();
    }

    @Test
    public void Should_ReturnCorrectTool_When_ToolCodeIsValid() {
        Tool actualTool = toolStore.findTool("CHNS");
        ToolType actualToolType = actualTool.getToolType();

        assertAll(
                () -> assertEquals("CHNS", actualTool.getToolCode()),
                () -> assertEquals("Chainsaw", actualToolType.getType()),
                () -> assertEquals(1.49, actualToolType.getDailyCharge()),
                () -> assertTrue(actualToolType.isChargeableDay(DayType.WEEKDAY)),
                () -> assertFalse(actualToolType.isChargeableDay(DayType.WEEKEND)),
                () -> assertTrue(actualToolType.isChargeableDay(DayType.HOLIDAY)),
                () -> assertEquals("Stihl", actualTool.getBrand())
        );
    }

    @Test
    public void Should_ReturnNull_When_ToolCodeIsInvalid() {
        Tool actualTool = toolStore.findTool("INVALID");
        assertNull(actualTool);
    }
}