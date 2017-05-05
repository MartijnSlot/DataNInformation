package nl.utwente.di.bookQuote;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by martijn.slot on 25/04/2017.
 */
public class Quoter {

    public Map<String, Double> books = new HashMap<>();

    public void fillMap(){
        books.put("1", 10.0);
        books.put("2", 45.0);
        books.put("3", 20.0);
        books.put("4", 35.0);
        books.put("5", 50.0);
    }

    public double getBookPrice(String s){
        return books.get(s);
    }
}
