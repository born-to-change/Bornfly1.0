package bornfly.myrevive;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import static android.widget.AdapterView.OnItemClickListener;
import static android.widget.AdapterView.OnItemSelectedListener;

import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.tops.notes.AtyEditNote;
import com.tops.notes.NotesActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class PlanActivity extends Activity
{
    private Button addPlan;
    private String[] descs = new String[]
            { "计划1", "计划2"
                    , "计划3", "计划4"};

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_table);
        addPlan=(Button)findViewById(R.id.addplanitem);
        addPlan.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(PlanActivity.this,PlanTimeSet.class);
            startActivity(intent);
        }
    });

        // 创建一个List集合，List集合的元素是Map
        List<Map<String, Object>> listItems =
                new ArrayList<Map<String, Object>>();
        for (int i = 0; i < descs.length; i++)
        {
            Map<String, Object> listItem = new HashMap<String, Object>();
            listItem.put("desc", descs[i]);
            listItems.add(listItem);
        }
        // 创建一个SimpleAdapter
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, listItems,
                R.layout.planitems,
                new String[] { "name",  "desc"},
                new int[] { R.id.name, R.id.desc });
        ListView list = (ListView) findViewById(R.id.planlist);
        // 为ListView设置Adapter
        list.setAdapter(simpleAdapter);

        // 为ListView的列表项的单击事件绑定事件监听器
        list.setOnItemClickListener(new OnItemClickListener()
        {
            // 第position项被单击时激发该方法
            @Override
            public void onItemClick(AdapterView<?> parent, View view,
                                    int position, long id)
            {
                System.out.println(descs[position]
                        + "被单击了");
            }
        });
        // 为ListView的列表项的选中事件绑定事件监听器
        list.setOnItemSelectedListener(new OnItemSelectedListener()
        {
            // 第position项被选中时激发该方法
            @Override
            public void onItemSelected(AdapterView<?> parent, View view,
                                       int position, long id)
            {
                System.out.println(descs[position]
                        + "被选中了");
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent)
            {
            }
        });

    }
}
