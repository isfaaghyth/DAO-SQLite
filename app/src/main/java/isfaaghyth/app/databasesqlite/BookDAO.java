package isfaaghyth.app.databasesqlite;

import java.util.List;

/**
 * Created by isfaaghyth on 11/17/17.
 * github: @isfaaghyth
 */

public interface BookDAO {
    List<BookModel> getData();
    void removeDataById(int _id);
    void insertData(BookModel data);
}
