package monos.myapplication.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

import monos.myapplication.model.BillItem;
import monos.myapplication.model.OpenBill;
import monos.myapplication.repository.local.LocalRepository;

public class DataViewModelLocal extends AndroidViewModel {
    private LocalRepository repository;
    private LiveData<List<OpenBill>> allBills;
    private LiveData<List<BillItem>> allItems;


    public DataViewModelLocal(@NonNull Application application) {
        super(application);
        repository = new LocalRepository(application);
        allBills = repository.getAllOpenBills();
        allItems = repository.getAllBillItems();


    }

    public void insertOpenBill(OpenBill note) {
        repository.insertOpenBill(note);
    }

    public void updateOpenBill(OpenBill note) {
        repository.updateOpenBill(note);
    }

    public void deleteOpenBill(OpenBill note) {
        repository.deleteOpenBill(note);
    }


    public LiveData<List<OpenBill>> getAllOpenBills() {

        return allBills;
    }

    public void insertBillItem(BillItem note) {
        repository.insertBillItem(note);
    }

    public void updateBillItem(BillItem note) {
        repository.updateBillItem(note);
    }

    public void deleteBillItem(BillItem note) {
        repository.deleteBillItem(note);
    }


    public LiveData<List<BillItem>> getAllBillItems() {
        return allItems;
    }


    public void getCurrentBillItems(OpenBill openBill) {
        repository.getAllBillItemsOfBill(openBill);
        allItems = repository.getAllBillItems();


    }


    public void adjustItemAmount(int clickedItemIndex, boolean b) {
        BillItem bi = allItems.getValue().get(clickedItemIndex);
        if (b) {
            bi.setAmount(bi.getAmount() + 1);
        } else {
            bi.setAmount(bi.getAmount() - 1);
        }
        bi.setTotalPrice(bi.getUnitPrice() * bi.getAmount());
        updateBillItem(bi);
    }
}
