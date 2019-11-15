package polinema.ac.id.roomdatabase.data;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import polinema.ac.id.roomdatabase.data.dao.BarangDao;
import polinema.ac.id.roomdatabase.data.entity.Barang;

@Database(entities = {Barang.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BarangDao barangDao();
}
