package monos.myapplication.model;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;

@Entity(tableName = "open_bill_table")
public class OpenBill implements Serializable {

    private String openedAt;
    private String nameOfBill;
    private int tableNumber;
    @Ignore
    private ArrayList<BillItem> billItems;
    @PrimaryKey(autoGenerate = true)
    private Integer id;


    public OpenBill(String openedAt, String nameOfBill, int tableNumber) {
        this.openedAt = openedAt;
        this.nameOfBill = nameOfBill;
        this.tableNumber = tableNumber;
        billItems = new ArrayList<>();

    }

    public String getOpenedAt() {
        return openedAt;
    }

    public String getNameOfBill() {
        return nameOfBill;
    }

    public int getTableNumber() {
        return tableNumber;
    }



    public void setBillItems(ArrayList<BillItem> billItems) {
        this.billItems = billItems;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public ArrayList<BillItem> getBillItems() {
        return this.billItems;
    }

    public Integer getId() {
        return id;
    }

    public void setOpenedAt(String openedAt) {
        this.openedAt = openedAt;
    }

    public void setNameOfBill(String nameOfBill) {
        this.nameOfBill = nameOfBill;
    }

    public void setTableNumber(int tableNumber) {
        this.tableNumber = tableNumber;
    }

    @Override
    public String toString() {
        return "OpenBill{" +
                "openedAt='" + openedAt + '\'' +
                ", nameOfBill='" + nameOfBill + '\'' +
                ", tableNumber=" + tableNumber +
                ", billItems=" + billItems +
                ", id=" + id +
                '}';
    }
}
