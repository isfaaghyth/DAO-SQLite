package isfaaghyth.app.databasesqlite;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by isfaaghyth on 11/17/17.
 * github: @isfaaghyth
 */

public class CreateActivity extends AppCompatActivity {

    private EditText edtTitle, edtAuthor;
    private Button btnSave;

    private BookDAO bookDAO;

    @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create);
        bookDAO = new BookOperationDAO(this);

        edtTitle = (EditText) findViewById(R.id.edt_title);
        edtAuthor = (EditText) findViewById(R.id.edt_author);
        btnSave = (Button) findViewById(R.id.btn_save);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                bookDAO.insertData(new BookModel()
                        .setTitle(edtTitle.getText().toString())
                        .setAuthor(edtAuthor.getText().toString()));
                startActivity(new Intent(CreateActivity.this, MainActivity.class));
                finish();
            }
        });
    }

}
