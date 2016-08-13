package com.archer.counterwatch;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    ArrayList<Hero> heroes = new ArrayList<>();
    ArrayList<Link> links = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        try {
            readHeroFile(heroes);
            readLinkFile(links);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Graph graph = new Graph(heroes.size());
        for(int i = 0;i<heroes.size();i++)
        {
            graph.vertexSet(i,heroes.get(i));
        }
        for(int i = 0;i<links.size();i++)
        {
            graph.addEdge(links.get(i).getSource(),links.get(i).getTarget(),links.get(i).getWeight());
        }

    }
    public static void readHeroFile(ArrayList obj) throws IOException {

        File file = new File("hero.txt");
        if (!file.exists()) {
            Log.e("File", "File not found: ");
        }
        Scanner inputFile = new Scanner(file);
        inputFile.useDelimiter(System.getProperty("line.separator"));   //separate reading the file line by line
        while (inputFile.hasNext()) {       //loop until no more line to read
            String line = inputFile.nextLine();     //store the entire line
            if(line.equals(""))     //if empty line, get out of the loop
                break;
            Hero newHero = getHeroInfo(line);   //create a new city by extracting info from theline
            obj.add(newHero);   //add the hero to the arraylist
        }
        inputFile.close();
    }
    public static void readLinkFile(ArrayList obj) throws IOException{
        File file = new File("counter.txt");
        if (!file.exists()) {
            Log.e("File", "File not found: ");
        }
        Scanner inputFile = new Scanner(file);
        inputFile.useDelimiter(System.getProperty("line.separator"));   //separate reading the file line by line
        while (inputFile.hasNext()) {       //loop until no more line to read
            String line = inputFile.nextLine();     //store the entire line
            if(line.equals(""))     //if empty line, get out of the loop
                break;
            Link newLink = getLinkInfo(line);   //create a new city by extracting info from theline
            obj.add(newLink);   //add the link to the arraylist
        }
        inputFile.close();
    }
    public static Hero getHeroInfo(String line)
    {
        Scanner inputFile = new Scanner(line);
        inputFile.useDelimiter("\\s{2,}");      //split on more than 2 white space only
        int number = Integer.parseInt(inputFile.next().trim());
        //trim extra spaces
        //assign extracted info from the line to corresponding info
        String name = inputFile.next();
        return new Hero(number,name); //return a new Hero
    }
    public static Link getLinkInfo(String line)
    {
        Scanner inputFile = new Scanner(line);
        inputFile.useDelimiter("\\s{2,}");      //split on more than 2 white space only
        int source = Integer.parseInt(inputFile.next().trim());
        int target = Integer.parseInt(inputFile.next().trim());
        int weight = Integer.parseInt(inputFile.next().trim());
        return new Link(source,target,weight); //return a new Link
    }
}
