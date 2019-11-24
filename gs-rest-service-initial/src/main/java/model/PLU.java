package model;

public class PLU {

    private String name;
    private double unitPrice;
    private Integer id;
    private int pluGroupId;

    public PLU(String name, double unitPrice, Integer id, int pluGroupId) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.id = id;
        this.pluGroupId = pluGroupId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setPluGroupId(int pluGroupId) {
        this.pluGroupId = pluGroupId;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public Integer getId() {
        return id;
    }

    public int getPluGroupId() {
        return pluGroupId;
    }
}
