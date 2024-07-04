package org.example;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.Scanner;

public class WasteMonitoring {
    private MongoCollection<Document> wasteCollection;
    private Scanner scanner;

    public WasteMonitoring(MongoDatabase database, Scanner scanner) {
        this.wasteCollection = database.getCollection("waste");
        this.scanner = scanner;
    }

    public void monitorWaste() {
        System.out.print("Enter waste data: ");
        String wasteData = scanner.nextLine();

        Document query = new Document("data", wasteData);
        Document existingWaste = wasteCollection.find(query).first();

        if (existingWaste != null) {
            System.out.println("This waste data has already been entered.");
            return;
        }

        Document waste = new Document("data", wasteData);
        wasteCollection.insertOne(waste);
        System.out.println("Waste monitoring completed.");
    }

    public void updateWaste() {
        System.out.print("Enter waste data to update: ");
        String oldData = scanner.nextLine();
        System.out.print("Enter new waste data: ");
        String newData = scanner.nextLine();

        Document query = new Document("data", oldData);
        Document update = new Document("$set", new Document("data", newData));
        wasteCollection.updateOne(query, update);
        System.out.println("Waste update completed.");
    }

    public void deleteWaste() {
        System.out.print("Enter waste data to delete: ");
        String wasteData = scanner.nextLine();

        Document query = new Document("data", wasteData);
        wasteCollection.deleteOne(query);
        System.out.println("Waste deletion completed.");
    }

    public void viewAllWaste() {
        for (Document doc : wasteCollection.find()) {
            System.out.println(doc.toJson());
        }
        System.out.println("Viewing all waste data completed.");
    }
}
