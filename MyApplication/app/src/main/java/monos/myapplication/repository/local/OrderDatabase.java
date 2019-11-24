package monos.myapplication.repository.local;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import monos.myapplication.model.BillItem;
import monos.myapplication.model.OpenBill;


@Database(entities = {OpenBill.class, BillItem.class}, version = 2)
public abstract  class OrderDatabase extends RoomDatabase {
    private static OrderDatabase instance;

    public abstract OpenBillDao openBillDao();
    public abstract BillItemDao billItemDao();

    public static synchronized OrderDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    OrderDatabase.class, "order_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    //in case i wanted to populate the database on start
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);

        }
    };

}
