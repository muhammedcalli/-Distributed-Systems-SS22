import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Newspaper {
    private String name;
    List<Article> articles = new ArrayList<>();

    public Newspaper(String n){
        name = n;
    }
    public String getName(){
        return name;
    }
    public void addArticle(String n, String c, int p){
        Article article = new Article(n,c, p);
        articles.add(article);
    }

    public List<Article> getArticles() {
        return articles;
    }

    public List<String> getNames(){
        List<String> names = new ArrayList<>();
        for (Article article: getArticles()
        ) {
            names.add(article.getArticleName());
        }
        return names;
    }

    public Article searchArticle (String articleName){
        Iterator<Article> iterator = articles.iterator();
        while (iterator.hasNext()) {
            Article par = iterator.next();
            if (articleName.equals(par.getArticleName())) {
                return par;
            }
        }
        return null;
    }


    public String getNewspaperName() {return name;}
}
