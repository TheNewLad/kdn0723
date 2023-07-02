package datasource;

import static util.DayChecker.DayType.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import model.Tool;
import model.ToolType;
import util.DayChecker.DayType;

public class ToolStore {
  private static ToolStore instance;
  private final List<Tool> tools;

  private ToolStore() {
    tools = new ArrayList<>();

    initDataSource();
  }

  public static ToolStore getInstance() {
    if (instance == null) {
      instance = new ToolStore();
    }

    return instance;
  }

  private void initDataSource() {
    ToolType ladder =
        new ToolType(
            "Ladder",
            1.99,
            new HashSet<DayType>() {
              {
                add(WEEKDAY);
                add(WEEKEND);
              }
            });

    ToolType chainsaw =
        new ToolType(
            "Chainsaw",
            1.49,
            new HashSet<DayType>() {
              {
                add(WEEKDAY);
                add(HOLIDAY);
              }
            });

    ToolType jackhammer =
        new ToolType(
            "Jackhammer",
            2.99,
            new HashSet<DayType>() {
              {
                add(WEEKDAY);
              }
            });

    tools.add(new Tool("CHNS", chainsaw, "Stihl"));
    tools.add(new Tool("LADW", ladder, "Werner"));
    tools.add(new Tool("JAKD", jackhammer, "DeWalt"));
    tools.add(new Tool("JAKR", jackhammer, "Ridgid"));
  }

  public Tool findTool(String toolCode) {
    return tools.stream()
        .filter(tool -> tool.getToolCode().equals(toolCode))
        .findFirst()
        .orElse(null);
  }
}
