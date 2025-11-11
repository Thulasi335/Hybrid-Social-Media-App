package com.socialmedia.config;
import com.mongodb.client.*;

public class MongoConfig {

    private static final MongoClient client = MongoClients.create("mongodb://localhost:27017");
    private static final MongoDatabase db = client.getDatabase("socialmongo");

    public static MongoDatabase getDatabase() {
        return db;
    }
}
