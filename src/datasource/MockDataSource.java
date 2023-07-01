package datasource;

import model.Tool;
import model.ToolType;

import java.util.ArrayList;
import java.util.List;

public class MockDataSource {
    private static MockDataSource instance;
    private final List<Tool> tools;

    private MockDataSource() {
        tools = new ArrayList<>();

        initDataSource();
    }

    private void initDataSource() {
        ToolType ladder = new ToolType("Ladder", 1.99, true, true, false);
        ToolType chainsaw = new ToolType("Chainsaw", 1.49, true, false, true);
        ToolType jackhammer = new ToolType("Jackhammer", 2.99, true, false, false);

        tools.add(new Tool("CHNS", chainsaw, "Stihl"));
        tools.add(new Tool("LADW", ladder, "Werner"));
        tools.add(new Tool("JAKD", jackhammer, "DeWalt"));
        tools.add(new Tool("JAKR", jackhammer, "Ridgid"));
    }

    public static MockDataSource getInstance() {
        if (instance == null) {
            instance = new MockDataSource();
        }

        return instance;
    }

    public Tool findTool(String toolCode) {
        return tools.stream()
                .filter(tool -> tool.getToolCode().equals(toolCode))
                .findFirst()
                .orElse(null);
    }
}