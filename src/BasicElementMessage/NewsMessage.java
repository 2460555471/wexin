package BasicElementMessage;

import java.util.List;

public class NewsMessage extends BaseMessage
{
	private int ArticleCount;			//文章数量说明
	private List<News> Articles;	//文章列表
	
	public int getArticleCount() {
		return ArticleCount;
	}
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}
	public List<News> getArticles() {
		return Articles;
	}
	public void setArticles(List<News> articles) {
		Articles = articles;
	}
}
