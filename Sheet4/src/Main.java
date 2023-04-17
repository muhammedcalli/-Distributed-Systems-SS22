public class Main {
    public static void main (String[] args){
        Newspaper  newspaper = new Newspaper("Konyali");
        System.out.printf("The Newspapers name is %s.%n", newspaper.getName());
        newspaper.addArticle("kkk", "aa", 15);
        newspaper.addArticle("bbb", "bb", 22);
        newspaper.addArticle("ccc", "cc", 6);
        newspaper.addArticle("fu", "k", 22);

        newspaper.searchArticle("aaa");


       int largestPage = 0;
        for (Article t : newspaper.getArticles()) {
            if (t.getPageNumber() > largestPage)
            largestPage = t.getPageNumber();
        }
        System.out.println("Account Largest Page: " + largestPage);

        //Test Search
        Article article = newspaper.searchArticle("fu");
        System.out.println(article);




    }


}
