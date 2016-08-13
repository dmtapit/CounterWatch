package com.archer.counterwatch;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;


import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Scanner;


public class MainActivity extends AppCompatActivity {

    ArrayList<Hero> heroes = new ArrayList<>();
    ArrayList<Link> links = new ArrayList<>();

    static ImageButton[] images = new ImageButton[22];
    Button button;

    static int imageCounter = 0;

    private static final int[] BUTTON_IDS = {
            R.id.ana,
            R.id.bastion,
            R.id.dva,
            R.id.genji,
            R.id.hanzo,
            R.id.junkrat,
            R.id.lucio,
            R.id.mccree,
            R.id.mei,
            R.id.mercy,
            R.id.pharah,
            R.id.reaper,
            R.id.reinhardt,
            R.id.roadhog,
            R.id.soldier76,
            R.id.symmetra,
            R.id.torbjorn,
            R.id.tracer,
            R.id.widowmaker,
            R.id.winston,
            R.id.zarya,
            R.id.zenyatta,
    };

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
        //put heroes and links to graph
        final Graph graph = new Graph(heroes.size());
        for(int i = 0;i<heroes.size();i++)
            graph.vertexSet(i,heroes.get(i));
        for(int i = 0;i<links.size();i++)
            graph.addEdge(links.get(i).getSource(),links.get(i).getTarget(),links.get(i).getWeight());


        button = (Button) findViewById(R.id.reset);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                for (int k = 0 ; k < graph.getSize(); k++) {
                    View v = findViewById(graph.getVertex()[k].imageButtonID);
                    v.setVisibility(v.VISIBLE);
                }

            }
        });
        int counter = 0;
        for(int i:BUTTON_IDS){
            images[counter] = (ImageButton) findViewById(i);
            images[counter].setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   int id = view.getId();
                    int index = 999;
                    for (int j = 0; j < graph.getSize(); j++) {
                        if (id == graph.getVertex()[j].imageButtonID) {
                            index = j;
                        }
                    }
                    for (int k = 0 ; k < graph.getSize(); k++) {
                        if (graph.getEdges()[index][k] == 0) {
                            // Set button invisible
                            View v = findViewById(graph.getVertex()[k].imageButtonID);
                            v.setVisibility(v.GONE);
                        }else{
                            View v = findViewById(graph.getVertex()[k].imageButtonID);
                            v.setVisibility(v.VISIBLE);
                        }
                    }
                }
            });
            counter++;
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

        return new Hero(number,name,BUTTON_IDS[imageCounter++]); //return a new Hero
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
