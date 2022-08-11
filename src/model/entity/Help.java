package model.entity;

public class Help {
    private String id;
    private String helpcontent;

    public Help() {
    }

    public Help(String id, String helpcontent) {
        this.id = id;
        this.helpcontent = helpcontent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHelpcontent() {
        return helpcontent;
    }

    public void setHelpcontent(String helpcontent) {
        this.helpcontent = helpcontent;
    }

    @Override
    public String toString() {
        return "Help{" +
                "id='" + id + '\'' +
                ", helpcontent='" + helpcontent + '\'' +
                '}';
    }
}
