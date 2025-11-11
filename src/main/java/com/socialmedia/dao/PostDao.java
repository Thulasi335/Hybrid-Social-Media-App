package com.socialmedia.dao;

import com.socialmedia.model.Post;
import org.bson.types.ObjectId;
import java.util.List;

public interface PostDao {
    ObjectId createPost(Post post);
    List<Post> getRecentPosts(int limit);
    boolean likePost(ObjectId postId, int userId);
    boolean addComment(ObjectId postId, int userId, String userName, String text);
}


