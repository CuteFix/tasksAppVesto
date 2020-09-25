import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.*;
import java.util.*;

public class Parse{
    public static void main(String[] args) throws IOException {
        Document document = Jsoup.connect("https://www.udemy.com/course/learn-flutter-dart-to-build-ios-android-apps/?utm_source=adwords&utm_medium=udemyads&utm_campaign=LongTail_la.EN_cc.ROW&utm_content=deal4584&utm_term=_._ag_77879424134_._ad_437497333833_._kw__._de_c_._dm__._pl__._ti_dsa-1007766171312_._li_1012861_._pd__._&matchtype=b&gclid=EAIaIQobChMIuoqwtOOB7AIVBd-yCh0FewRKEAMYASAAEgJ5APD_BwE/")
                .get();

        List<AttributeAll> attributeListCor = new ArrayList<>();

        List<Attribute> attributeList = new ArrayList<>();
        Elements spanElements = document.getElementsByAttributeValueEnding("class", "2k1DQ");
        spanElements.forEach(spanElement->{
            Element nameElements = spanElement;
            String name = nameElements.text();
            attributeList.add(new Attribute(name));
        });

        List<AttributeTime> attributeListTime = new ArrayList<>();
        Elements spanTElements = document.getElementsByAttributeValueEnding("class", "126oS");
        spanTElements.forEach(spanTElement->{
            Element timeElements = spanTElement;
            String time = timeElements.text();
            attributeListTime.add(new AttributeTime(time));
        });

        String keyword = "question";
        for(int i = 0; i < attributeList.size(); i++){
            AttributeTime a = attributeListTime.get(i);
            String StringTime = a.toString();
            int indexJava = StringTime.indexOf(keyword);
            Attribute b = attributeList.get(i);
            String resultText = b.toString();

            if(indexJava == - 1){
                String result = StringTime.replaceAll("([[:][\\s]])","");
                int  resultInt = Integer.parseInt(result);
                AttributeAll objt = new AttributeAll(resultText,resultInt,StringTime);
                attributeListCor.add(objt);
            }
            else{

            }
        }
        Collections.sort(attributeListCor);
        PrintStream out = new PrintStream(new FileOutputStream("output.txt"));
        System.setOut(out);
        attributeList.forEach(System.out::println);//parse
        attributeListCor.forEach(System.out::println);//parse by date
    }
}

class AttributeTime{
    private String time;
    public AttributeTime(String time) {
        this.time = time;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
    @Override
    public String toString() {
        return "" +
                "" + time +" ";
    }
}
class Attribute{
    private String name;
    public Attribute(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "" +
                "" + name +" ";
    }
}
class AttributeAll implements Comparable<AttributeAll>{
    private String name;
    private int dur;
    private String date;
    public AttributeAll(String name, int dur, String date) {
        this.name = name;
        this.dur = dur;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDur() {
        return dur;
    }

    public void setDur(int dur) {
        this.dur = dur;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "" +
                "" + name +" " + date ;
    }

    @Override
    public int compareTo(AttributeAll o) {
        return this.getDur()-o.getDur();
    }
}
