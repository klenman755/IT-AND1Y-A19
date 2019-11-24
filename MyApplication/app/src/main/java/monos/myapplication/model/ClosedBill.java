package monos.myapplication.model;

import java.math.BigDecimal;

public class ClosedBill {
    private String closedBy;
    private BigDecimal fullPrice;
    private int openBillId;



    public ClosedBill(String closedBy, BigDecimal fullPrice, int openBillId) {
        this.closedBy = closedBy;
        this.fullPrice = fullPrice;
        this.openBillId = openBillId;
    }



    public ClosedBill() {
    }



    public String getClosedBy() {
        return closedBy;
    }
    public void setClosedBy(String closedBy) {
        this.closedBy = closedBy;
    }
    public BigDecimal getFullPrice() {
        return fullPrice;
    }
    public void setFullPrice(BigDecimal fullPrice) {
        this.fullPrice = fullPrice;
    }
    public int getOpenBillId() {
        return openBillId;
    }
    public void setOpenBillId(int openBillId) {
        this.openBillId = openBillId;
    }
}
