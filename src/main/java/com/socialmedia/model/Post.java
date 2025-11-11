package com.socialmedia.model;

import org.bson.types.ObjectId;
import java.util.*;

public class Post {
    private ObjectId id;
    private String authorName;
    private int authorId;
    private String content;
    private List<String> tags = new ArrayList<String>();
    private List<Integer> likes = new ArrayList<Integer>();
    private List<Map<String, ObjectId>> comments = new ArrayList<>();
    private Date createdAt = new Date();

    public ObjectId getId(){
        return id;
    }
    public void setId(ObjectId id){
        this.id = id;
    }
    public String getAuthorName(){
        return authorName;
    }
    public void setAuthorName(String authorName){
        this.authorName = authorName;
    }
    public int getAuthorId(){
        return authorId;
    }
    public void setAuthorId(int authorId){
        this.authorId = authorId;
    }
    public String getContent(){
        return content;
    }
    public void setContent(String content){
        this.content = content;
    }
    public List<String> getTags(){
        return  tags;
    }
    public void setTags(List<String> tags){
        this.tags = tags;
    }
    public List<Integer> getLikes(){
        return likes;
    }
    public void setLikes(List<Integer> likes){
        this.likes = likes;
    }
    public List<Map<String, ObjectId>> getComments(){
        return comments;
    }
    public void setComments(List<Map<String, ObjectId>> comments){
        this.comments = comments;
    }
    public Date getCreatedAt(){
        return createdAt;
    }
    public void setCreatedAt(Date createdAt){
        this.createdAt = createdAt;
    }
}
