package monos.myapplication.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;



@Entity(tableName = "bill_item_table"


       )
public class BillItem implements Serializable {

    private String name;
    private double unitPrice;
    private int amount;
    private double totalPrice;
    @PrimaryKey(autoGenerate = true)
    private int id;
    private int openBillID;


    public BillItem(String name, double unitPrice, int amount, double totalPrice) {
        this.name = name;
        this.unitPrice = unitPrice;
        this.amount = amount;
        this.totalPrice = totalPrice;

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getName() {
        return name;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public int getAmount() {
        return amount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setOpenBillID(int openBillID) {
        this.openBillID = openBillID;
    }

    public int getOpenBillID() {
        return openBillID;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "BillItem{" +
                "name='" + name + '\'' +
                ", unitPrice=" + unitPrice +
                ", amount=" + amount +
                ", totalPrice=" + totalPrice +
                ", id=" + id +
                ", openBillID=" + openBillID +
                '}';
    }
}
