package isfaaghyth.app.databasesqlite;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements ItemListener {

    private RecyclerView lstItem;
    private ListAdapter adapter;
    private BookDAO bookDAO;

    @Override protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //init bookDAO
        bookDAO = new BookOperationDAO(this);
        //init adapter
        adapter = new ListAdapter(bookDAO.getData(), this);
        lstItem = (RecyclerView) findViewById(R.id.lst_item);
        lstItem.setLayoutManager(new LinearLayoutManager(this));
        lstItem.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        registerForContextMenu(lstItem);
    }

    @Override public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.mn_add:
                startActivity(new Intent(this, CreateActivity.class));
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        menu.setHeaderTitle("Pilih menu");
        menu.add(0, v.getId(), 0, "Hapus");
        menu.add(0, v.getId(), 0, "Ubah");
    }

    @Override public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        if (item.getTitle() == "Hapus") {
            bookDAO.removeDataById(bookDAO.getData().get(info.position).get_id());
            adapter.notifyDataSetChanged();
        } else if (item.getTitle() == "Ubah") {}
        return super.onContextItemSelected(item);
    }

    @Override public void onClick(View v, int position) {
        registerForContextMenu(v);
    }
}
