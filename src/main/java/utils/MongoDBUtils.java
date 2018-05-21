package utils;

import com.mongodb.*;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;
import org.testng.Reporter;



/**
 * Created by thinkpad on 2018-03-27.
 */
public class MongoDBUtils {
    /*************************************
     * mongo增加记录，返回记录_id
     * @param host mongo主机地址
     * @param port mongo主机端口
     * @param database 库名
     * @param col collection名
     * @param doc 需加入记录bson.Document
     * @return 返回_id
     ************************************/
    public static String AddDoc(String host, int port,String database,String col,Document doc){
        try{
            MongoClient mongoClient = new MongoClient(host, port);
            MongoDatabase db = mongoClient.getDatabase(database);
            MongoCollection<Document> collection = db.getCollection(col);
            collection.insertOne(doc);
            ObjectId id = (ObjectId)doc.get( "_id" );
            return id.toString();
        }catch (Exception e){
            System.out.println("mongodb 执行添加出错：" + e.toString());
            Reporter.log("mongodb 执行添加出错：" + e.toString());
            return "";
        }
    }

    /*************************************
     * 按_id删除记录
     * @param host mongo主机地址
     * @param port mongo主机端口
     * @param database 库名
     * @param col collection名
     * @param id 需删除的记录id
     * @return
     ************************************/
    public static Boolean Remove(String host, int port,String database,String col,String id){
        try{
            MongoClient mongoClient = new MongoClient(host, port);
            MongoDatabase db = mongoClient.getDatabase(database);
            MongoCollection<Document> collection = db.getCollection(col);
            Document doc = new Document();
            ObjectId Oid = new ObjectId(id);
            doc.append("_id",Oid);
            collection.findOneAndDelete(doc);
            return true;
        }catch (Exception e){
            System.out.println("mongodb 执行删除出错：" + e.toString());
            Reporter.log("mongodb 执行删除出错：" + e.toString());
            return false;
        }

    }

   /* public static void main(String[] args) {
        MongoClient mongoClient = new MongoClient( "10.1.1.154" , 27017 );
        MongoDatabase db = mongoClient.getDatabase("cache");

        //db.createCollection("user");
        MongoCollection<Document> collection = db.getCollection("user");

        Document document = new Document("titlellll", "MongoDB").
                append("description", "database").
                append("likes", 100).
                append("by", "Fly");
        List<Document> documents = new ArrayList<Document>();
        documents.add(document);
        collection.insertMany(documents);
        ObjectId id = (ObjectId)document.get( "_id" );
        System.out.println("......"+ id.toString());
    }*/
}
