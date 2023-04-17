import java.io.Serializable;

public class Message implements Serializable {
    private String article;
    private String method;
    private String category;
    private String object;
    private int pageofNumber;
    private int newspaperselection;
    private String parameter = "";

    public Message(String pmethod, int pnewspaperselection,String pObject, String pArticle, String pCategory, int pPageofNumber) {
        this.article = pArticle;
        this.method = pmethod;
        this.category = pCategory;
        this.object = pObject;
        this.pageofNumber = pPageofNumber;
        this.newspaperselection = pnewspaperselection;
    }







    public Message() {

    }

    public void setMethod(String method) {
        this.method = method;
    }


    public String getMethod()
    {
        return method;
    }

    public String getParameter()
    {
        return parameter;
    }

    public void setArticle(String article) {
        this.article = article;
    }

    public String getArticle() {
        return article;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getPageofNumber() {
        return this.pageofNumber;
    }

    public void setPageofNumber(int pageofNumber) {
        this.pageofNumber = pageofNumber;
    }

    public int getNewspaperselection() {
        return newspaperselection;
    }

    public void setNewspaperselection(int newspaperselection) {
        this.newspaperselection = newspaperselection;
    }

    public String getObject() {
        return object;
    }

    public void setObject(String object) {
        this.object = object;
    }
}

