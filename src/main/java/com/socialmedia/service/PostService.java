package com.socialmedia.service;

import com.socialmedia.dao.PostDao;
import com.socialmedia.dao.PostDaoImpl;
import com.socialmedia.model.Post;
import org.bson.types.ObjectId;

import java.util.List;

public class PostService {
    private final PostDao postDao = new PostDaoImpl();

    public void createPost(int userId, String authorName, String content) {
        Post post = new Post();
        post.setAuthorId(userId);
        post.setAuthorName(authorName);
        post.setContent(content);
        postDao.createPost(post);
        System.out.println("Post created successfully");
    }

    public void viewFeed() {
        List<Post> posts = postDao.getRecentPosts(10);
        if (posts.isEmpty()) {
            System.out.println(" No posts yet!");
        } else {
            System.out.println("\n Recent Posts:");
            for (Post p : posts) {
                System.out.println("\n-----------------------------");
                System.out.println("Author :" + p.getAuthorName());
                System.out.println("Messages :" + p.getContent());
                System.out.println("-----------------------------");
            }
        }
    }

    public void likePost(ObjectId postId, int userId) {
        postDao.likePost(postId, userId);
        System.out.println("Post liked!");
    }

    public void commentPost(ObjectId postId, int userId, String userName, String comment) {
        postDao.addComment(postId, userId, userName, comment);
        System.out.println("Comment added!");
    }
}
