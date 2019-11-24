package monos.myapplication.repository.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

import monos.myapplication.model.OpenBill;

@Dao
public interface OpenBillDao {

    @Insert
    void insert(OpenBill bill);

    @Update
    void update(OpenBill bill);

    @Delete
    void delete(OpenBill bill);

    @Query("DELETE FROM open_bill_table")
    void deleteAllOpenBills();

    @Query("DELETE FROM open_bill_table WHERE id = :idToDelete")
    void deleteOpenBill(int idToDelete);

    @Query("SELECT * FROM open_bill_table ORDER BY id ASC")
    LiveData<List<OpenBill>> getAllOpenBills();


}
