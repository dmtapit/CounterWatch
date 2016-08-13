package com.archer.counterwatch;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
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
    public void readHeroFile(ArrayList obj) throws IOException {

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(getAssets().open("hero.txt")));
            String line;
            while((line = reader.readLine()) != null){
                if(line.equals(""))     //if empty line, get out of the loop
                    break;
                Hero newHero = getHeroInfo(line);   //create a new city by extracting info from theline
                obj.add(newHero);   //add the hero to the arraylist
            }

        }catch (IOException e){
            Log.e("File", "File not found");
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                Log.e("File", "File not closed ");
                }
            }
        }
    }
    public void readLinkFile(ArrayList obj) throws IOException{

        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new InputStreamReader(getAssets().open("counter.txt")));
            String line;
            while((line = reader.readLine()) != null){
                if(line.equals(""))     //if empty line, get out of the loop
                    break;
                Link newLink = getLinkInfo(line);   //create a new city by extracting info from theline
                obj.add(newLink);   //add the LINK to the arraylist
            }
        }catch (IOException e){
            Log.e("File", "File not found");
        }finally {
            if(reader != null){
                try{
                    reader.close();
                }catch (IOException e){
                    Log.e("File", "File not closed ");
                }
            }
        }
    }
    public static Hero getHeroInfo(String line)
    {
        String[] parts = line.split("-");
        int number = Integer.parseInt(parts[0]);
        String name = parts[1];
        return new Hero(number,name); //return a new Hero
    }
    public static Link getLinkInfo(String line)
    {
        Scanner inputFile = new Scanner(line);
        inputFile.useDelimiter("\\t");      //split on more than 2 white space only
        int source = Integer.parseInt(inputFile.next().trim());
        int target = Integer.parseInt(inputFile.next().trim());
        int weight = Integer.parseInt(inputFile.next().trim());
        return new Link(source,target,weight); //return a new Link
    }
}
