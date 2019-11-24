package monos.myapplication.repository.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import monos.myapplication.model.BillItem;
@Dao
public interface BillItemDao {

    @Insert
    void insert(BillItem billItem);

    @Update
    void update(BillItem billItem);

    @Delete
    void delete(BillItem billItem);

    @Query("DELETE FROM bill_item_table")
    void deleteAllBillItems();

    @Query("SELECT * FROM bill_item_table ORDER BY id ASC")
    LiveData<List<BillItem>> getAllBillItems();

    @Query("SELECT * FROM bill_item_table where openBillID = :openBillId ORDER BY id ASC")
    LiveData<List<BillItem>> getAllBillItemsOfBill(int openBillId);
}
