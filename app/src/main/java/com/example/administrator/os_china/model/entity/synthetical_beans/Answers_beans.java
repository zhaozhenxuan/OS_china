package com.example.administrator.os_china.model.entity.synthetical_beans;

import java.util.List;

/**
 * Created by Administrator on 2017/5/15 0015.
 */

public class Answers_beans {

    private String pagesize;
    private String postCount;
    private List<PostBean> posts;

    public String getPagesize() {
        return pagesize;
    }

    public void setPagesize(String pagesize) {
        this.pagesize = pagesize;
    }

    public String getPostCount() {
        return postCount;
    }

    public void setPostCount(String postCount) {
        this.postCount = postCount;
    }

    public List<PostBean> getPosts() {
        return posts;
    }

    public void setPosts(List<PostBean> posts) {
        this.posts = posts;
    }

    public static class PostBean {
        private String id;
        private String portrait;
        private String author;
        private String authorid;
        private String title;
        private String body;
        private String answerCount;
        private String viewCount;
        private String pubDate;
        private String answer;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getAuthorid() {
            return authorid;
        }

        public void setAuthorid(String authorid) {
            this.authorid = authorid;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getBody() {
            return body;
        }

        public void setBody(String body) {
            this.body = body;
        }

        public String getAnswerCount() {
            return answerCount;
        }

        public void setAnswerCount(String answerCount) {
            this.answerCount = answerCount;
        }

        public String getViewCount() {
            return viewCount;
        }

        public void setViewCount(String viewCount) {
            this.viewCount = viewCount;
        }

        public String getPubDate() {
            return pubDate;
        }

        public void setPubDate(String pubDate) {
            this.pubDate = pubDate;
        }

        public String getAnswer() {
            return answer;
        }

        public void setAnswer(String answer) {
            this.answer = answer;
        }
    }
}
