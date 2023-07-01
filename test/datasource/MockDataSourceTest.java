package datasource;

import model.Tool;
import model.ToolType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MockDataSourceTest {
    MockDataSource mockDataSource;

    @BeforeEach
    public void setUp() {
        mockDataSource = MockDataSource.getInstance();
    }

    @Test
    public void Should_ReturnCorrectTool_When_ToolCodeIsValid() {
        Tool actualTool = mockDataSource.findTool("CHNS");
        assertAll(
                () -> assertEquals("CHNS", actualTool.getToolCode()),
                () -> assertEquals("Chainsaw", actualTool.getToolType().getType()),
                () -> assertEquals(1.49, actualTool.getToolType().getDailyCharge()),
                () -> assertTrue(actualTool.getToolType().isWeekdayCharge()),
                () -> assertFalse(actualTool.getToolType().isWeekendCharge()),
                () -> assertTrue(actualTool.getToolType().isHolidayCharge()),
                () -> assertEquals("Stihl", actualTool.getBrand())
        );
    }

    @Test
    public void Should_ReturnNull_When_ToolCodeIsInvalid() {
        Tool actualTool = mockDataSource.findTool("INVALID");
        assertNull(actualTool);
    }
}