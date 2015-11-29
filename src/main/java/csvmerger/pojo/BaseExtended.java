package csvmerger.pojo;

public class BaseExtended {
    private String baseId;
    private String baseAmount1;
    private String baseAmount2;

    public BaseExtended() {}

    public String getBaseId() {
        return baseId;
    }

    public void setBaseId(String baseId) {
        this.baseId = baseId;
    }

    public String getBaseAmount1() {
        return baseAmount1;
    }

    public void setBaseAmount1(String baseAmount1) {
        this.baseAmount1 = baseAmount1;
    }

    public String getBaseAmount2() {
        return baseAmount2;
    }

    public void setBaseAmount2(String baseAmount2) {
        this.baseAmount2 = baseAmount2;
    }

    @Override
    public String toString() {
        return "BaseExtended{" +
                "baseId='" + baseId + '\'' +
                ", baseAmount1='" + baseAmount1 + '\'' +
                ", baseAmount2='" + baseAmount2 + '\'' +
                '}';
    }
}
