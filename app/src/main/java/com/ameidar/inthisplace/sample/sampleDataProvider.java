package com.ameidar.inthisplace.sample;

import com.ameidar.inthisplace.model.DataItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class sampleDataProvider {

    public static List<DataItem> dataItemList ;
    public static Map<String , DataItem> dataItemMap;

    static {
        dataItemList = new ArrayList<>(  );
        dataItemMap = new HashMap<>(  );

        addItem(new DataItem(null, "Jesus Walked on the Water. (John 6) part 1", "Capernaum",
                "Capernaum, Sea of Galilee, large christian wall art, Jesus, Christmas svg, Holy Land Picture, Biblical Landscape",
                1, 39.72, "Capernaum1.jpg"));

        addItem(new DataItem(null, "Jesus Walked on the Water. (John 6) part 2", "Capernaum",
                "Sea of Galilee, large christian wall art, Christian Wall Art, Large Print, Jesus Picture, Holy Land Picture, Biblical Landscape",
                1, 39.72, "Capernaum2.jpg"));
        addItem(new DataItem(null, "Jesus Walked on the Water. (John 6) part 3", "Capernaum",
                "Capernaum, Sea of Galilee Picture, Bible Verse Wall Art, Christian Wall Art, Large Print, Holy Land Picture, Biblical Landscape",
                1, 39.72, "Capernaum3.jpg"));
        addItem(new DataItem(null, "Jesus taught the eight Beatitudes. (Mathew 5) ", "Mount of beatitudes",
                "Sea of Galilee , large christian wall art, Jesus is the reason for the season, Christmas svg, Holy Land Picture, Biblical Landscape",
                1, 39.72, "Mountofbeatitudes1.jpg"));
        addItem(new DataItem(null, "Jerusalem", "Jerusalem",
                "Mount of beatitudes, Bible Verse , Christian Wall Art, Jesus is the reason for the season, pictures of jesus christ",
                1, 39.72, "Jerusalem1.jpg"));
        addItem(new DataItem(null, "Jesus was Buried. (Mathew 27)", "Jerusalem",
                "Sea of Galilee Picture, Bible Verse Wall Art, Christian Wall Art, Large Print, Jesus Picture, Holy Land Picture, Biblical Landscape",
                1, 39.72, "Jerusalem2.jpg"));
        addItem(new DataItem(null, "The Soldiers Mocked Jesus. (Mark 15)", "Jerusalem",
                "Capernaum, Sea of Galilee, Bible Verse Wall Art, Jesus is the reason for the season, Christmas svg, Holy Land Picture, Biblical Landscape,",
                1, 39.72, "Jerusalem3.jpg"));
        addItem(new DataItem(null, "Jesus Reinstated Peter. (John 21)", "Tabgha",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Tabgha.jpg"));
        addItem(new DataItem(null, "Jesus Began His Ministry. (Mathew 4)", "Holy Land Picture",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Holyland1.jpg"));
        addItem(new DataItem(null, "Jesus was Baptized. (Mathew 3)", "Holy Land Picture",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Holyland2.jpg"));
        addItem(new DataItem(null, "Laban and Jacob Made a Covenant. (Genesis 31)", "Holy Land Picture",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Holyland3.jpg"));
        addItem(new DataItem(null, "The Daily Offerings. (Exodus 29)", "Holy Land Picture",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Holyland4.jpg"));
        addItem(new DataItem(null, "Jesus Stilled the Storm. (Mark 4)", "Sea of Galilee",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Seaofgalilee1.jpg"));
        addItem(new DataItem(null, "Jesus fed the Five Thousand. (Mark 6)", "Sea of Galilee",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Seaofgalilee2.jpg"));
        addItem(new DataItem(null, "Jesus Called his First Disciples. (Matthew 4)", "Sea of Galilee",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Seaofgalilee3.jpg"));
        addItem(new DataItem(null, "Jesus Stilled the Storm part 2 (Mark 4)", "Sea of Galilee",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Seaofgalilee4.jpg"));
        addItem(new DataItem(null, "Jesus Called His First Disciples. (Luke 5)", "Sea of Galilee",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Seaofgalilee5.jpg"));
        addItem(new DataItem(null, "Jesus Stilled the Storm part 3 (Mark 4)", "Sea of Galilee",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Seaofgalilee6.jpg"));
        addItem(new DataItem(null, "Jesus Walked on the Water. (John 6)", "Sea of Galilee",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Seaofgalilee7.jpg"));
        addItem(new DataItem(null, "Jesus Stilled the Storm part 4 (Mark 4)", "Sea of Galilee",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Seaofgalilee8.jpg"));
        addItem(new DataItem(null, "Jesus fed the Five Thousand. (Mark 6)", "Sea of Galilee",
                "This listing is a professional quality print on a Fine Art photo paper. The print is accompanied by a card that provides the scriptures in which the biblical event actually took place. making it perfect for gifting.",
                1, 39.72, "Seaofgalilee9.jpg"));



    }

    private static void addItem(DataItem item)
    {
        dataItemList.add( item );
        dataItemMap.put( item.getItemId() , item );

    }

}
