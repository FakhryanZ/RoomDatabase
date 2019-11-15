package polinema.ac.id.roomdatabase.adapters;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import java.util.ArrayList;

import polinema.ac.id.roomdatabase.R;
import polinema.ac.id.roomdatabase.activities.RoomCreate;
import polinema.ac.id.roomdatabase.data.AppDatabase;
import polinema.ac.id.roomdatabase.data.entity.Barang;

public class AdapterBarangRecyclerView extends RecyclerView.Adapter<AdapterBarangRecyclerView.ViewHolder> {
    private ArrayList<Barang> daftarBarang;
    private Context context;
    private AppDatabase db;

    public AdapterBarangRecyclerView(ArrayList<Barang> barangs, Context ctx){
        daftarBarang = barangs;
        context = ctx;
        db = Room.databaseBuilder(context.getApplicationContext(),
                AppDatabase.class,
                "barangdb").allowMainThreadQueries().build();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView tvTitle;
        CardView cvMain;
        ViewHolder(View v){
            super(v);
            tvTitle = v.findViewById(R.id.tv_namaBarang);
            cvMain = v.findViewById(R.id.cv_main);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_barang, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final String name = daftarBarang.get(position).getNamaBarang();

        holder.cvMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Barang barang = db.barangDao().selectBarangDetail(daftarBarang.get(position).getIdBarang());
//                context.startActivity(RoomReadSingle.getActIntent((Activity) context).putExtra("data", barang));
            }
        });
        holder.cvMain.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                final Dialog dialog = new Dialog(context);
                dialog.setContentView(R.layout.view_dialog);
                dialog.setTitle("Pilih aksi");
                dialog.show();

                Button editButton = dialog.findViewById(R.id.btnEditData);
                Button delButton = dialog.findViewById(R.id.btnDeletedata);

                editButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                onEditBarang(position);
                            }
                        }
                );

                delButton.setOnClickListener(
                        new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                                onDeleteBarang(position);
                            }
                        }
                );
                return true;
            }
        });
        holder.tvTitle.setText(name);
    }

    private void onDeleteBarang(int position){
        db.barangDao().deleteBarang(daftarBarang.get(position));
        daftarBarang.remove(position);
        notifyItemRemoved(position);
        notifyItemRangeRemoved(position, daftarBarang.size());
    }

    private void onEditBarang(int position){
        context.startActivity(RoomCreate.getActIntent((Activity)
                context).putExtra("data", daftarBarang.get(position)));
    }

    @Override
    public int getItemCount() {
        return daftarBarang.size();
    }
}
