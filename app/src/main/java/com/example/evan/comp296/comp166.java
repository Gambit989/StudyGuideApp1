package com.example.evan.comp296;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.TextView;
import android.text.method.ScrollingMovementMethod;

/**
 * Created by Evan on 12/14/16.
 */

public class comp166 extends AppCompatActivity {


    TextView info;

    String info_info;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.comp_166);

        getSupportActionBar().setHomeButtonEnabled(true);




        info = (TextView) findViewById(R.id.textView_placeholder);


        info.setText("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec vitae consequat elit. Mauris placerat porta diam, id venenatis massa molestie congue. Quisque dignissim, magna ut interdum volutpat, risus leo aliquet erat, a pretium urna augue at metus. Suspendisse commodo vulputate metus. In nec risus leo. Donec faucibus, purus a dictum bibendum, ex nulla sollicitudin nisi, sit amet blandit augue odio maximus magna. Nunc ullamcorper pharetra rutrum.\n" +
                "\n" +
                "Suspendisse sed mi ut odio facilisis placerat eu placerat elit. Cras sit amet quam ac nibh pretium semper malesuada sed nunc. Mauris at tincidunt lectus. Pellentesque vitae est eu enim convallis gravida. Nullam semper vestibulum purus, ut elementum augue rhoncus vel. Phasellus bibendum turpis ut odio tincidunt, nec ultricies enim tincidunt. In velit magna, porttitor a enim vitae, fringilla sollicitudin diam. Nam nec neque odio. In placerat ultrices rhoncus.\n" +
                "\n" +
                "Etiam tristique mauris felis, non malesuada justo ullamcorper vel. Etiam accumsan varius tristique. Nunc vitae mattis metus, eget semper leo. Mauris in quam leo. Donec condimentum, mi id aliquet placerat, elit leo tincidunt tortor, sit amet lacinia magna libero eget ligula. Maecenas tortor mauris, iaculis et ullamcorper eu, tristique ut tortor. Ut pulvinar suscipit sapien, quis cursus est gravida sed. Proin aliquam viverra eros eget convallis.\n" +
                "\n" +
                "Praesent luctus tellus sit amet tortor pretium iaculis id ac magna. Sed ac quam sit amet risus bibendum fringilla ac nec nisl. Fusce porttitor sed magna ut gravida. Sed sed quam sollicitudin, tincidunt eros eu, laoreet lectus. Proin scelerisque odio nunc, eu rhoncus neque gravida a. Donec semper purus eu ante sagittis accumsan. Cras hendrerit turpis purus, sed sagittis arcu convallis at. Vestibulum consequat augue quam, at molestie nisl tempor interdum. Nunc at leo orci. Mauris lacinia turpis ac tellus lobortis, nec tristique mauris finibus. Pellentesque mollis est et nunc efficitur viverra. Maecenas ac metus eget ipsum hendrerit tristique quis ac augue.\n" +
                "\n" +
                "Donec aliquet lectus lacus, sit amet pretium eros sollicitudin et. Praesent id nulla sed ipsum efficitur porta. Suspendisse malesuada odio at sodales feugiat. Etiam in lacus pharetra nisl tempor mollis quis in sapien. Curabitur sed sapien id ligula aliquet vulputate non non felis. Vestibulum molestie dolor pharetra, congue erat vel, rhoncus risus. Vestibulum euismod ut tortor sed dapibus. Donec ut sem pretium, auctor nunc a, consectetur augue. Maecenas at gravida velit, ut vulputate lorem. Nulla ut convallis dui.");




        info.setMovementMethod(new ScrollingMovementMethod());



    }








}
