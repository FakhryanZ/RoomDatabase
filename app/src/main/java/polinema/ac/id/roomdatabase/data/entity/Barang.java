package polinema.ac.id.roomdatabase.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "barangTable")
public class Barang implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int idBarang;

    @ColumnInfo(name = "namaBarang")
    public String namaBarang;

    @ColumnInfo(name = "merkBarang")
    public String merkBarang;

    @ColumnInfo(name = "hargaBarang")
    public String hargaBarang;

    public int getIdBarang() {
        return idBarang;
    }

    public void setIdBarang(int idBarang) {
        this.idBarang = idBarang;
    }

    public String getNamaBarang() {
        return namaBarang;
    }

    public void setNamaBarang(String namaBarang) {
        this.namaBarang = namaBarang;
    }

    public String getMerkBarang() {
        return merkBarang;
    }

    public void setMerkBarang(String merkBarang) {
        this.merkBarang = merkBarang;
    }

    public String getHargaBarang() {
        return hargaBarang;
    }

    public void setHargaBarang(String hargaBarang) {
        this.hargaBarang = hargaBarang;
    }
}
