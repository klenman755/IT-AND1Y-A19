package monos.myapplication.repository.local;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import java.util.List;

import monos.myapplication.model.BillItem;
import monos.myapplication.model.OpenBill;

public class LocalRepository {
    private OpenBillDao openBillDao;
    private BillItemDao billItemDao;
    private LiveData<List<OpenBill>> allOpenBills;

    private LiveData<List<BillItem>> currentBillItems;

    public LocalRepository(Application application) {
        OrderDatabase database = OrderDatabase.getInstance(application);
        openBillDao = database.openBillDao();
        billItemDao = database.billItemDao();
        allOpenBills = openBillDao.getAllOpenBills();

       currentBillItems = billItemDao.getAllBillItemsOfBill(0);


    }



    public void insertOpenBill(OpenBill note) {
        new InsertOpenBillAsyncTask(openBillDao).execute(note);


    }

    public void updateOpenBill(OpenBill note) {
        new UpdateOpenBillAsyncTask(openBillDao).execute(note);
    }

    public void deleteOpenBill(OpenBill note) {
        new DeleteOpenBillAsyncTask(openBillDao).execute(note);
    }

    public void deleteAllOpenBills() {
        new DeleteAllOpenBillsAsyncTask(openBillDao).execute();
    }

    public LiveData<List<OpenBill>> getAllOpenBills() {
        return allOpenBills;
    }

    public void insertBillItem(BillItem note) {
        new InsertBillItemAsyncTask(billItemDao).execute(note);
    }

    public void updateBillItem(BillItem note) {
        new UpdateBillItemAsyncTask(billItemDao).execute(note);
    }

    public void deleteBillItem(BillItem note) {
        new DeleteBillItemAsyncTask(billItemDao).execute(note);
    }



    public LiveData<List<BillItem>> getAllBillItems() {

        return currentBillItems;
    }



    public void getAllBillItemsOfBill(OpenBill openBill ) {

       currentBillItems =(billItemDao.getAllBillItemsOfBill(openBill.getId()));
    }

    private static class InsertOpenBillAsyncTask extends AsyncTask<OpenBill, Void, Void> {
        private OpenBillDao openBillDao;

        private InsertOpenBillAsyncTask(OpenBillDao openBillDao) {
            this.openBillDao = openBillDao;
        }

        @Override
        protected Void doInBackground(OpenBill... notes) {
            this.openBillDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateOpenBillAsyncTask extends AsyncTask<OpenBill, Void, Void> {
        private OpenBillDao openBillDao;

        private UpdateOpenBillAsyncTask(OpenBillDao openBillDao) {
            this.openBillDao = openBillDao;
        }

        @Override
        protected Void doInBackground(OpenBill... notes) {
            openBillDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteOpenBillAsyncTask extends AsyncTask<OpenBill, Void, Void> {
        private OpenBillDao openBillDao;

        private DeleteOpenBillAsyncTask(OpenBillDao openBillDao) {
            this.openBillDao = openBillDao;
        }

        @Override
        protected Void doInBackground(OpenBill... notes) {
            openBillDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllOpenBillsAsyncTask extends AsyncTask<Void, Void, Void> {
        private OpenBillDao openBillDao;

        private DeleteAllOpenBillsAsyncTask(OpenBillDao openBillDao) {
            this.openBillDao = openBillDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            openBillDao.deleteAllOpenBills();
            return null;
        }
    }

    private static class InsertBillItemAsyncTask extends AsyncTask<BillItem, Void, Void> {
        private BillItemDao billItemDao;

        private InsertBillItemAsyncTask(BillItemDao openBillDao) {
            this.billItemDao = openBillDao;
        }

        @Override
        protected Void doInBackground(BillItem... notes) {
            Log.d("TAG", notes[0].toString());
            Log.d("TAG",billItemDao.toString());

            billItemDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateBillItemAsyncTask extends AsyncTask<BillItem, Void, Void> {
        private BillItemDao billItemDao;

        private UpdateBillItemAsyncTask(BillItemDao openBillDao) {
            this.billItemDao = openBillDao;
        }

        @Override
        protected Void doInBackground(BillItem... notes) {
            billItemDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteBillItemAsyncTask extends AsyncTask<BillItem, Void, Void> {
        private BillItemDao billItemDao;

        private DeleteBillItemAsyncTask(BillItemDao openBillDao) {
            this.billItemDao = openBillDao;
        }

        @Override
        protected Void doInBackground(BillItem... notes) {
            billItemDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllBillItemsAsyncTask extends AsyncTask<Void, Void, Void> {
        private BillItemDao billItemDao;

        private DeleteAllBillItemsAsyncTask(BillItemDao openBillDao) {
            this.billItemDao = openBillDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            billItemDao.deleteAllBillItems();
            return null;
        }
    }


}
