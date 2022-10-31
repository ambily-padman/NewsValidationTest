package com.news.utils.utilities;

import com.news.api.APIHandler;
import com.news.utils.data.BingNews;
import com.news.utils.data.GoogleNewsData;
import io.restassured.response.Response;

public class APIUtils {

    /**
     * This method will check the semantic similarity between the guardian news and google search headlines results
     *
     * @param guardianHeadline
     * @param googleNewsData
     * @return
     * @throws InterruptedException
     */
    public boolean isTheNewsSemanticallyMatchesWithGoogleResults(String guardianHeadline, GoogleNewsData googleNewsData) throws InterruptedException {
        APIHandler apiHandler = new APIHandler();
        int semanticMatchCount = 0;
        for (int i = 0; i < googleNewsData.getNewsList().size(); i++) {
            Response response = apiHandler.postWithJsonPayLoad("/paraphrase-multilingual-mpnet-base-v2/semantic-similarity",
                    createJsonPayLoad(guardianHeadline, googleNewsData.getNewsList().get(i).getNewsDescription()));
            Thread.sleep(6000);// This wait is to give some extra time gap between the call so that we get less 429
            System.out.println("NLP CLoud Response: " + response.getStatusCode());
            if (response.getStatusCode() == 200) {
                Float score = response.jsonPath().get("score");
                if (score >= 0.5)
                    semanticMatchCount++;

            }
        }
        System.out.println("Semantic similarity match count: " + semanticMatchCount);
        return semanticMatchCount >= 3;
    }

    /**
     * This method will check the semantic similarity between the guardian news and bing search headlines results
     *
     * @param guardianHeadline
     * @param bingNews
     * @return
     * @throws InterruptedException
     */

    public boolean isTheNewsSemanticallyMatchesWithBingResults(String guardianHeadline, BingNews bingNews) throws InterruptedException {
        APIHandler apiHandler = new APIHandler();
        int semanticMatchCount = 0;
        for (int i = 0; i < bingNews.getNews().size(); i++) {
            Response response = apiHandler.postWithJsonPayLoad("/paraphrase-multilingual-mpnet-base-v2/semantic-similarity",
                    createJsonPayLoad(guardianHeadline, bingNews.getNews().get(i)));
            Thread.sleep(6000);// This wait is to give some extra time gap between the call so that we get less 429
            System.out.println("NLP CLoud Response: " + response.getStatusCode());
            if (response.getStatusCode() == 200) {
                Float score = response.jsonPath().get("score");
                if (score >= 0.5)
                    semanticMatchCount++;

            }
        }
        System.out.println("Semantic similarity match count: " + semanticMatchCount);
        return semanticMatchCount >= 3;
    }

    private String createJsonPayLoad(String guardiaNews, String otherNews) throws InterruptedException {
        Thread.sleep(1000);
        return "{\"sentences\":[\"" + guardiaNews + "\",\"" + otherNews + "\"]}";
    }


}
