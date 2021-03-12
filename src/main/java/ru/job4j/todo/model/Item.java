package ru.job4j.todo.model;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class Item {
    private int id;
    private String description;
    private final Date date = new Date();
    private final String created = changeFormatDate();
    private boolean done;

    public String changeFormatDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss  dd.MM.yyyy");
        return dateFormat.format(created);
    }

    public Item() {
    }

    public Item(String description, boolean done) {
        this.description = description;
        this.done = done;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getDate() {
        return date;
    }

    public String getCreated() {
        return created;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Item item = (Item) o;
        return Objects.equals(description, item.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(description);
    }

    @Override
    public String toString() {
        return "Item{"
                + "id="
                + id
                + ", description='"
                + description
                + '\''
                + ", created='"
                + created
                + '\''
                + ", done="
                + done
                + '}';
    }
}
