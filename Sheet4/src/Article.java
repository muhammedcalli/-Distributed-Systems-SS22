public class Article{
    private String articleName;
    private String category;
    private int pageNumber;

    public Article(String articleName, String category, int pageNumber){
        this.articleName = articleName;
        this.category = category;
        this.pageNumber = pageNumber;
    }
    public void setArticleName(String articleName){
        this.articleName = articleName;
    }
    public String getArticleName(){
        return articleName;
    }
    public String getCategory(){
        return category;
    }
    public int getPageNumber(){
        return pageNumber;

    }
}
