package tumblr.mimic.com.bean;

/**
 * Created by Ayush on 9/21/2016.
 */

public class PostBean {
    String postTitle;
    String postImage;

    public PostBean(String postTitle, String postImage) {
        this.postTitle = postTitle;
        this.postImage = postImage;
    }

    public String getPostTitle() {
        return postTitle;
    }

    public void setPostTitle(String postTitle) {
        this.postTitle = postTitle;
    }

    public String getPostImage() {
        return postImage;
    }

    public void setPostImage(String postImage) {
        this.postImage = postImage;
    }
}