package sample;

import javax.annotation.Generated;

/**
 * Created by RdDvls on 9/28/16.
 */
public class ToDoItem {
    public int id;
    public String text;
    public boolean isDone;

    public ToDoItem(String text) {
        this.text = text;
        this.isDone = false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }


    public ToDoItem(int id, String text, boolean isDone) {
        this.text = text;
        this.isDone = isDone;
        this.id = id;
    }
}
