package bornfly.myrevive;

/**
 * Created by Administrator on 2017/5/21 0021.
 */

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.Preference;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.ToggleButton;

import java.util.Calendar;


public class PlanTimeSet extends Activity {
    SharedPreferences preferences;
    SharedPreferences.Editor editor;
    TextView show;
    RadioGroup status;
    ToggleButton toggle;
    Switch switcher;
    Button dateBn;
    Button timeBn;
    Button dateBn2;
    Button timeBn2;
    EditText showsDate;
    EditText showsTime;
    EditText showeTime;
    EditText showeDate;
    EditText planname;
    RadioButton rb;
    Button OK;
    Button NotOK;
    String[] items = new String[] {
            "振动",
            "短信息",
            "响铃" };

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.plan_items);
        preferences = getSharedPreferences("planset",MODE_PRIVATE);
        editor = preferences.edit();
        planname = (EditText)findViewById(R.id.planName);
        dateBn = (Button)findViewById(R.id.dateBn);
        timeBn = (Button)findViewById(R.id.timeBn);
        dateBn2 = (Button)findViewById(R.id.dateBn2);
        timeBn2 = (Button)findViewById(R.id.timeBn2);
        toggle = (ToggleButton)findViewById(R.id.toggle);
        switcher = (Switch)findViewById(R.id.switcher);
        final LinearLayout test = (LinearLayout)findViewById(R.id.test);
        show = (TextView) findViewById(R.id.show);
        status = (RadioGroup) findViewById(R.id.status);
        OK=(Button)findViewById(R.id.planOK);

        OK.setOnClickListener(new OnClickListener(){
            @Override
            public void onClick(View source) {
                editor.putString("statue",rb.getText().toString());
                editor.putString("sDate",showsDate.getText().toString());
                editor.putString("sTime",showsTime.getText().toString());
                editor.putString("eDate",showeDate.getText().toString());
                editor.putString("eTime",showeTime.getText().toString());
                editor.putString("remenderWay",show.getText().toString());
                editor.putString("repeat",toggle.getText().toString());
                editor.putString("planname",planname.getText().toString());
                editor.commit();

                //editor.putString();
            }
        });
        status.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId)
            {
                int radioButtonId = group.getCheckedRadioButtonId();
                //根据ID获取RadioButton的实例
                 rb = (RadioButton)PlanTimeSet.this.findViewById(radioButtonId);
            }
        });
        CompoundButton.OnCheckedChangeListener listener = new CompoundButton.OnCheckedChangeListener()
        {
            @Override
            public void onCheckedChanged(CompoundButton button
                    , boolean isChecked)
            {
                if(isChecked)
                {
                    // 设置LinearLayout垂直布局
                    test.setOrientation(LinearLayout.HORIZONTAL);
                    toggle.setChecked(true);
                    switcher.setChecked(true);
                }
                else
                {
                    // 设置LinearLayout水平布局
                    test.setOrientation(LinearLayout.HORIZONTAL);
                    toggle.setChecked(false);
                    switcher.setChecked(false);
                }
            }
        };
        toggle.setOnCheckedChangeListener(listener);
        switcher.setOnCheckedChangeListener(listener);
        //为“设置日期”按钮绑定监听器
        dateBn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View source)
            {
                Calendar c = Calendar.getInstance();
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                new DatePickerDialog(PlanTimeSet.this,
                        // 绑定监听器
                        new DatePickerDialog.OnDateSetListener()
                        {
                            @Override
                            public void onDateSet(DatePicker dp, int year,
                                                  int month, int dayOfMonth)
                            {
                                showsDate = (EditText) findViewById(R.id.showstartdate);
                                showsDate.setText(year + "年" + (month + 1)
                                        + "月" + dayOfMonth + "日");
                            }
                        }
                        //设置初始日期
                        , c.get(Calendar.YEAR)
                        , c.get(Calendar.MONTH)
                        , c.get(Calendar.DAY_OF_MONTH)).show();

            }
        });

        //为“设置时间”按钮绑定监听器
        timeBn.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View source)
            {
                Calendar c = Calendar.getInstance();
                // 创建一个TimePickerDialog实例，并把它显示出来
                new TimePickerDialog(PlanTimeSet.this,
                        // 绑定监听器
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            @Override
                            public void onTimeSet(TimePicker tp, int hourOfDay,
                                                  int minute)
                            {
                                 showsTime = (EditText) findViewById(R.id.showstarttime);
                                showsTime.setText( hourOfDay + "时"
                                        + minute + "分");
                            }
                        }
                        //设置初始时间
                        , c.get(Calendar.HOUR_OF_DAY)
                        , c.get(Calendar.MINUTE)
                        //true表示采用24小时制
                        , true).show();

            }
        });
        dateBn2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View source)
            {
                Calendar c = Calendar.getInstance();
                // 直接创建一个DatePickerDialog对话框实例，并将它显示出来
                new DatePickerDialog(PlanTimeSet.this,
                        // 绑定监听器
                        new DatePickerDialog.OnDateSetListener()
                        {
                            @Override
                            public void onDateSet(DatePicker dp, int year,
                                                  int month, int dayOfMonth)
                            {
                               showeDate = (EditText) findViewById(R.id.showenddate);
                                showeDate.setText(year + "年" + (month + 1)
                                        + "月" + dayOfMonth + "日");
                            }
                        }
                        //设置初始日期
                        , c.get(Calendar.YEAR)
                        , c.get(Calendar.MONTH)
                        , c.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        //为“设置时间”按钮绑定监听器
        timeBn2.setOnClickListener(new OnClickListener()
        {
            @Override
            public void onClick(View source)
            {
                Calendar c = Calendar.getInstance();
                // 创建一个TimePickerDialog实例，并把它显示出来
                new TimePickerDialog(PlanTimeSet.this,
                        // 绑定监听器
                        new TimePickerDialog.OnTimeSetListener()
                        {
                            @Override
                            public void onTimeSet(TimePicker tp, int hourOfDay,
                                                  int minute)
                            {
                                showeTime = (EditText) findViewById(R.id.showendtime);
                                showeTime.setText( hourOfDay + "时"
                                        + minute + "分");
                            }
                        }
                        //设置初始时间
                        , c.get(Calendar.HOUR_OF_DAY)
                        , c.get(Calendar.MINUTE)
                        //true表示采用24小时制
                        , true).show();
            }
        });
    }
    public void singleChoice(View source)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                // 设置对话框标题
                .setTitle("选择提醒方式")
                // 设置图标
                .setIcon(R.mipmap.ic_launcher2)
                // 设置单选列表项，默认选中第二项（索引为1）
                .setSingleChoiceItems(items, 1, new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        show.setText( items[which] );
                    }
                });
        // 为AlertDialog.Builder添加“确定”按钮
        setPositiveButton(builder);
        // 为AlertDialog.Builder添加“取消”按钮
        setNegativeButton(builder)
                .create()
                .show();
    }

    private AlertDialog.Builder setPositiveButton(
            AlertDialog.Builder builder)
    {
        // 调用setPositiveButton方法添加“确定”按钮
        return builder.setPositiveButton("确定", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });
    }
    private AlertDialog.Builder setNegativeButton(
            AlertDialog.Builder builder)
    {
        // 调用setNegativeButton方法添加“取消”按钮
        return builder.setNegativeButton("取消", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {

            }
        });



    }
}


