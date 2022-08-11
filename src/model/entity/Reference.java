package model.entity;

public class Reference {
    private String id;
    private String author;
    private Integer year;
    private String title;
    private String rating;
    private String journal;
    private String lastupdate;
    private String type;
    private Integer categoryid;
    private RefCategory cid;/*这个是分类实体类*/
    private Integer isdelete;
    private String group;
    private String url;
    private String citation;
    private String source;
    private String from;
    private String operator;
    private String localsource;

    public Reference() {
    }

    public Reference(String author, Integer year, String title, String rating, String journal, String lastupdate, String type, Integer categoryid, Integer isdelete, String group, String url) {
        this.author = author;
        this.year = year;
        this.title = title;
        this.rating = rating;
        this.journal = journal;
        this.lastupdate = lastupdate;
        this.type = type;
        this.categoryid = categoryid;
        this.isdelete = isdelete;
        this.group = group;
        this.url = url;
    }

    public Reference(String author, Integer year, String title, String rating, String journal, String lastupdate, String type) {
        this.author = author;
        this.year = year;
        this.title = title;
        this.rating = rating;
        this.journal = journal;
        this.lastupdate = lastupdate;
        this.type = type;
    }

    public Reference(String author, Integer year, String title, String rating, String journal, String lastupdate, String type, Integer categoryid, String url) {
        this.author = author;
        this.year = year;
        this.title = title;
        this.rating = rating;
        this.journal = journal;
        this.lastupdate = lastupdate;
        this.type = type;
        this.categoryid = categoryid;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getJournal() {
        return journal;
    }

    public void setJournal(String journal) {
        this.journal = journal;
    }

    public String getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(String lastupdate) {
        this.lastupdate = lastupdate;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getCategoryid() {
        return categoryid;
    }

    public void setCategoryid(Integer categoryid) {
        this.categoryid = categoryid;
    }

    public RefCategory getCid() {
        return cid;
    }

    public void setCid(RefCategory cid) {
        this.cid = cid;
    }

    public Integer getIsdelete() {
        return isdelete;
    }

    public void setIsdelete(Integer isdelete) {
        this.isdelete = isdelete;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getCitation() {
        return citation;
    }

    public void setCitation(String citation) {
        this.citation = citation;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getLocalsource() {
        return localsource;
    }

    public void setLocalsource(String localsource) {
        this.localsource = localsource;
    }

    @Override
    public String toString() {
        return "Reference{" +
                "id='" + id + '\'' +
                ", author='" + author + '\'' +
                ", year=" + year +
                ", title='" + title + '\'' +
                ", rating='" + rating + '\'' +
                ", journal='" + journal + '\'' +
                ", lastupdate='" + lastupdate + '\'' +
                ", type='" + type + '\'' +
                ", categoryid=" + categoryid +
                ", cid=" + cid +
                ", isdelete=" + isdelete +
                ", group='" + group + '\'' +
                ", url='" + url + '\'' +
                ", citation='" + citation + '\'' +
                ", source='" + source + '\'' +
                ", from='" + from + '\'' +
                ", operator='" + operator + '\'' +
                ", localsource='" + localsource + '\'' +
                '}';
    }
}