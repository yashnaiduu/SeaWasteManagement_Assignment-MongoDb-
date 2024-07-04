package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class UserAuthentication {
    private MongoCollection<Document> usersCollection;
    private Scanner scanner;

    public UserAuthentication(MongoDatabase database, Scanner scanner) {
        this.usersCollection = database.getCollection("users");
        this.scanner = scanner;
    }

    public void registerUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Document user = new Document("username", username).append("password", password);
        usersCollection.insertOne(user);
    }

    public boolean loginUser() {
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        Document query = new Document("username", username).append("password", password);
        Document user = usersCollection.find(query).first();
        return user != null;
    }
}
