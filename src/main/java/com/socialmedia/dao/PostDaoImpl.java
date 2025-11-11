package com.socialmedia.dao;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;
import com.socialmedia.config.MongoConfig;
import com.socialmedia.model.Post;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import static com.mongodb.client.model.Sorts.descending;

public class PostDaoImpl implements PostDao {
    private static final Logger logger = LoggerFactory.getLogger(PostDaoImpl.class);
    private final MongoCollection<Document> collection;

    public PostDaoImpl() {
        MongoDatabase db = MongoConfig.getDatabase();
        this.collection = db.getCollection("posts");
    }

    @Override
    public ObjectId createPost(Post post) {
        Document doc = new Document("authorId", post.getAuthorId())
                .append("authorName", post.getAuthorName())
                .append("content", post.getContent())
                .append("tags", post.getTags())
                .append("likes", post.getLikes())
                .append("comments", post.getComments())
                .append("createdAt", post.getCreatedAt());
        collection.insertOne(doc);
        logger.info("Post created for user {}", post.getAuthorName());
        return doc.getObjectId("_id");
    }

    @Override
    public List<Post> getRecentPosts(int limit) {
        List<Post> posts = new ArrayList<>();
        for (Document doc : collection.find().sort(descending("createdAt")).limit(limit)) {
            Post post = new Post();
            post.setId(doc.getObjectId("_id"));
            post.setAuthorId(doc.getInteger("authorId"));
            post.setAuthorName(doc.getString("authorName"));
            post.setContent(doc.getString("content"));
            posts.add(post);
        }
        return posts;
    }

    @Override
    public boolean likePost(ObjectId postId, int userId) {
        try {
            collection.updateOne(Filters.and(Filters.eq("_id", postId), Filters.nin("likes", userId)),
                    Updates.push("likes", userId));
            return true;
        } catch (Exception e) {
            logger.error("Error liking post", e);
            return false;
        }
    }

    @Override
    public boolean addComment(ObjectId postId, int userId, String userName, String text) {
        Map<String, Object> comment = new HashMap<>();
        comment.put("userId", userId);
        comment.put("userName", userName);
        comment.put("text", text);
        comment.put("ts", new Date());
        try {
            collection.updateOne(Filters.eq("_id", postId),
                    Updates.push("comments", comment));
            return true;
        } catch (Exception e) {
            logger.error("Error adding comment", e);
            return false;
        }
    }
}

