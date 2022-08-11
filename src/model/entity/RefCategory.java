package model.entity;

public class RefCategory {
    private String id;
    private String name;
    private Integer isdelete;

    public RefCategory(){

    }

    public RefCategory(String id, String name, Integer isdelete) {
        this.id = id;
        this.name = name;
        this.isdelete = isdelete;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    @Override
    public String toString() {
        return "RefCategory{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", isdelete=" + isdelete +
                '}';
    }
}
