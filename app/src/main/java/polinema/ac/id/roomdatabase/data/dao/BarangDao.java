package polinema.ac.id.roomdatabase.data.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import polinema.ac.id.roomdatabase.data.entity.Barang;

@Dao
public interface BarangDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Long insertBarang(Barang barang);

    @Update
    int updateBarang(Barang barang);

    @Delete
    int deleteBarang(Barang barang);

    @Query("SELECT * FROM barangTable")
    Barang[] selectALlBarangs();
}
