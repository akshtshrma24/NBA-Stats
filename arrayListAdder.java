import java.util.ArrayList;
import java.util.Collections;


public class arrayListAdder
{
    public ArrayList<String> arrayList;

    public arrayListAdder()
    {
        arrayList = new ArrayList<>();
    }
    public void add(String s)
    {
        arrayList.add(s);
    }
    public void sort()
    {
        Collections.sort(arrayList);
    }
    public String toString()
    {
        String temp2 = "";
        for(String temp: arrayList) {
            temp2+= temp;
        }
        return temp2;
    }

}
