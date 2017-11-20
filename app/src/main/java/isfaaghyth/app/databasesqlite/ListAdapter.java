package isfaaghyth.app.databasesqlite;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

/**
 * Created by isfaaghyth on 11/17/17.
 * github: @isfaaghyth
 */

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.Holder> {

    private List<BookModel> books;
    private ItemListener listener;

    public ListAdapter(List<BookModel> books, ItemListener listener) {
        this.books = books;
        this.listener = listener;
    }

    @Override public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new Holder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false));
    }

    @Override public void onBindViewHolder(Holder holder, final int position) {
        holder.txtTitle.setText(books.get(position).getTitle());
        holder.txtAuthor.setText(books.get(position).getAuthor());

        holder.item.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                listener.onClick(v, position);
            }
        });
    }

    @Override public int getItemCount() {
        return books.size();
    }

    class Holder extends RecyclerView.ViewHolder {

        LinearLayout item;
        TextView txtTitle, txtAuthor;

        Holder(final View itemView) {
            super(itemView);
            item = (LinearLayout) itemView.findViewById(R.id.item);
            txtTitle = (TextView) itemView.findViewById(R.id.txt_title);
            txtAuthor = (TextView) itemView.findViewById(R.id.txt_author);
        }
    }
}
