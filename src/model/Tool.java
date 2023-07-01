package model;

public class Tool {
    private final String toolCode;
    private final ToolType toolType;
    private final String brand;

    public Tool(String toolCode, ToolType toolType, String brand) {
        this.toolCode = toolCode;
        this.toolType = toolType;
        this.brand = brand;
    }

    public String getToolCode() {
        return toolCode;
    }

    public ToolType getToolType() {
        return toolType;
    }

    public String getBrand() {
        return brand;
    }
}