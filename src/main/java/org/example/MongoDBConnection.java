package org.example;

import com.mongodb.ConnectionString;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;

public class MongoDBConnection {

    private static final String CONNECTION_STRING = "mongodb://localhost:27017";
    private static final String DATABASE_NAME = "sea_waste_db";

    private com.mongodb.client.MongoClient mongoClient;
    private MongoDatabase database;

    public MongoDBConnection() {
        // Connect to MongoDB
        mongoClient = MongoClients.create(new ConnectionString(CONNECTION_STRING));
        database = mongoClient.getDatabase(DATABASE_NAME);
        System.out.println("Connected to MongoDB: " + DATABASE_NAME);
    }

    public MongoDatabase getDatabase() {
        return database;
    }

    public void close() {
        // Close MongoDB connection
        mongoClient.close();
        System.out.println("MongoDB connection closed.");
    }
}
