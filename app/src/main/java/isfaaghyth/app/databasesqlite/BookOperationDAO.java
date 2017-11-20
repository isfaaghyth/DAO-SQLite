package isfaaghyth.app.databasesqlite;

import android.content.Context;

import java.util.List;

/**
 * Created by isfaaghyth on 11/17/17.
 * github: @isfaaghyth
 */

public class BookOperationDAO implements BookDAO {

    private SQLiteHelper sqLiteHelper;

    public BookOperationDAO(Context context) {
        sqLiteHelper = new SQLiteHelper(context);
    }

    @Override public List<BookModel> getData() {
        return sqLiteHelper.getData();
    }

    @Override public void removeDataById(int _id) {
        sqLiteHelper.removeDataById(_id);
    }

    @Override public void insertData(BookModel data) {
        sqLiteHelper.insertData(new BookModel()
        .set_id(getData().size() + 1)
        .setTitle(data.getTitle())
        .setAuthor(data.getAuthor()));
    }

}
