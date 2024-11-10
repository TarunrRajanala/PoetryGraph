package assignment4;
//Rajanala, Tarun
//trr997
//Assignment 4


import java.awt.image.DataBufferDouble;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.stream.*;


//test with edge cases , empty corpus, repeat threads

public class GraphPoet {
    private Map<String, Vertex<String>> graph;
    /**
     *
     * @param corpus text file from which to derive the poet's affinity graph
     * @throws IOException if the corpus file cannot be found or read
     */
    public GraphPoet(File corpus) throws IOException {
        /* Read in the File and place into graph here */
        graph = new HashMap<>();
        try(Scanner scanner = new Scanner(corpus)) {
            //BufferedReader reader  = new BufferedReader((new FileReader(corpus))); //get corpus line
            //String fileLine;
            String preWord = null;
            while (scanner.hasNext()) {
                String word1 = scanner.next().toLowerCase();
                if (!graph.containsKey(word1)) {
                    graph.put(word1, new Vertex<>(word1));
                }

                if (preWord != null) {
                    Vertex<String> preVert = graph.get(preWord);
                    preVert.addEdge(word1, preVert.getEdges().getOrDefault(word1, 0) + 1);
                }
                preWord = word1;
            }
        }


            /*while((fileLine = reader.readLine())!= null){       //while corpus lines are there
                String[] words = fileLine.split("\\s+");  //seperate into words by blank space


                for (String word1: words)
                {
                    String currWord = word1.toLowerCase();
                    if(!graph.containsKey(currWord)){       //if it doesn't exist, add vertex
                        graph.put(currWord, new Vertex<>(currWord));
                    }


                    if(preWord != null){                        //set previous word and add edge to current word
                        Vertex<String> preVert = graph.get(preWord);
                        preVert.addEdge(currWord, preVert.getEdges().getOrDefault(currWord,0)+1);
                    }


                    preWord = currWord;
                }
            }
            reader.close();
            */
        catch(Exception e){
            throw new IOException();
        }
    }


    /**
     * Generate a poem.
     *
     * @param input File from which to create the poem
     * @return poem (as described above)
     */
    public String poem(File input) throws IOException {
        /* Read in input and use graph to complete poem */
        //String poem = "";
        StringBuilder poem = new StringBuilder();

        try{
            BufferedReader inputReader = new BufferedReader(new FileReader(input));
            String in;

            while((in = inputReader.readLine())!=null) {    //while there are still input lines


                String[] inputWords = in.split(" "); //seperate input sentece into words

                for (int i = 0; i < inputWords.length - 1; i++) {   //get current and next word
                    String currWord = inputWords[i].toLowerCase();
                    String nextWord = inputWords[i + 1].toLowerCase();

                    poem.append(inputWords[i]).append(" ");     //add words to the poem

                    if (graph.containsKey(currWord) && graph.containsKey(nextWord)) {   //if graph contains both words
                        String bridge = null;
                        int weight = 0;
                        Vertex<String> currVert = graph.get(currWord);
                        Map<String, Integer> edges = currVert.getEdges();

                        for (String word1 : edges.keySet()) {   //for every edge in the Vertex
                            int weight1 = edges.get(word1);
                            if (graph.get(word1).getEdges().containsKey(nextWord)) {
                                int weight2 = graph.get(word1).getEdges().get(nextWord);
                                int totalWeight = weight1 + weight2; //add bridge if Vertex has the edge
                                if(totalWeight >weight){
                                    weight = totalWeight;
                                    bridge = word1;
                                }
                            }
                        }
                        if (bridge != null) {
                            poem.append(bridge).append(" ");
                        }
                    }
                }
                poem.append(inputWords[inputWords.length - 1]);
                poem.append("\n");

            }
            inputReader.close();
        }
        catch (IOException e){
            e.printStackTrace();
            return("");
        }
        return poem.toString();
    }
}
