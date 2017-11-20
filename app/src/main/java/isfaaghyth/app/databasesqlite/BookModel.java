package isfaaghyth.app.databasesqlite;

/**
 * Created by isfaaghyth on 11/17/17.
 * github: @isfaaghyth
 */

public class BookModel {
    private int _id;
    private String title;
    private String author;

    public int get_id() {
        return _id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BookModel set_id(int _id) {
        this._id = _id;
        return this;
    }

    public BookModel setTitle(String title) {
        this.title = title;
        return this;
    }

    public BookModel setAuthor(String author) {
        this.author = author;
        return this;
    }

    @Override public String toString() {
        return "_id: " + _id +
                "\ntitle: " + title +
                "\nauthor: " + author;
    }
}
