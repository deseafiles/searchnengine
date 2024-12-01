package search.Utils;

import java.util.ArrayList;
import java.util.List;
import search.Models.Node;
import search.Models.RbTree;

public class PrintJson {
public static List<String> printRedBlackTreeResults(RbTree rbTree, String searchParam) {
    List<Node> result = rbTree.search(searchParam);
    List<Node> resultByKey = rbTree.searchByKey(searchParam);
    List<Node> resultByValue = rbTree.searchByValue(searchParam);
    ArrayList<String> combinedResults = new ArrayList<>();
    GameGimmick gimmick = new GameGimmick();

    // Memastikan bahwa setidaknya ada satu hasil pencarian
    if ((result != null && !result.isEmpty()) || 
        (resultByKey != null && !resultByKey.isEmpty()) || 
        (resultByValue != null && !resultByValue.isEmpty())) {

        // Menambahkan hasil dari pencarian umum
        if (result != null && !result.isEmpty()) {
            for (Node node : result) {
                String key = node.getKey().toString();
                String value;
                if (node.gimmick != null && key.equals(searchParam)) {
                    value = gimmick.gimmick(key);
                } else {
                    value = node.getValue() != null ? node.getValue().toString() : "null";
                }
                String resultString = "> " + key + "\n " + value;
                if (key.equals(searchParam) && !combinedResults.contains(resultString)) {
                    combinedResults.add(resultString);
                    break;
                }
            }
        }

        // Menambahkan hasil pencarian berdasarkan kunci
        if (resultByKey != null && !resultByKey.isEmpty()) {
            for (Node node : resultByKey) {
                String key = node.getKey().toString();
                String value = node.getValue() != null ? node.getValue().toString() : "null";
                String resultString = "> " + key + "\n " + value;
                if (!combinedResults.contains(resultString)) {
                    combinedResults.add(resultString);
                }
            }
        }

        // Menambahkan hasil pencarian berdasarkan nilai
        if (resultByValue != null && !resultByValue.isEmpty()) {
            for (Node node : resultByValue) {
                String key = node.getValue().toString();
                String value = node.getValue() != null ? node.getValue().toString() : "null";
                String resultString = "> " + key + "\n " + value;
                if (!combinedResults.contains(resultString)) {
                    combinedResults.add(resultString);
                }
            }
        }
    }

    return combinedResults;

        // if (resultByKey != null && !resultByKey.isEmpty()) {
        //     for (Node node : resultByKey) {
        //         String key = node.getKey().toString();
        //         String value = node.getValue() != null ? node.getValue().toString() : "null";
                
        //         String resultString = "> " + key + "\n " + value;

        //         if (!combinedResults.contains(resultString)) {
        //             combinedResults.add(resultString); 
        //         }
        //     }
        // }

    // public static List<String> printRedBlackTreeResults(RbTree rbTree, String searchParam) {
    //     List<Node> result = rbTree.search(searchParam);  
    //     List<Node> resultByKey = rbTree.searchByKey(searchParam);
    //     ArrayList<String> combinedResults = new ArrayList<>();
    //     GameGimmick gimmick = new GameGimmick();

    //     if (result != null && !result.isEmpty()) {
    //         for (Node node : result) {
    //             String key = node.getKey().toString(); 
    //             String value;

    //             if (node.gimmick != null && key.equals(searchParam)) {
    //                 value = gimmick.gimmick(key);
    //             } else {
    //                 value = node.getValue() != null ? node.getValue().toString() : "null"; 
    //             }

    //             String resultString = "> " + key + "\n " + value;

    //             if (!combinedResults.contains(resultString)) {
    //                 combinedResults.add(resultString); 
    //             }
    //         }
    //     }
    //     return combinedResults; 
    }
}