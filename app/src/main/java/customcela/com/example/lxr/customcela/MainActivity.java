package customcela.com.example.lxr.customcela;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import com.github.promeg.pinyinhelper.Pinyin;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
 /*
 * 1,自绘右边字母栏
 * 2.加载左边listview数据源（2.1每一个String转拼音通过tinypinyin
 *                           2.2通过拼音排序
 *                           2.3 排序后的集合设置适配器)
 * 3.字母栏监听回掉，拿到点击的字母
 * 4.与集合拼音首字母比较，如果相同，定位到集合下标处，停止；到最后一个都没有，吐司不存在
 * */
    private List<HaoHan> peoplelist = new ArrayList<>();
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ListView listView = findViewById(R.id.list);
        LetterList letter = findViewById(R.id.letter);
        //所有的名字全部转成拼音的集合
       // StringBuffer pinYin = new StringBuffer();
        String[] names = Cheeses.NAMES;
        peoplelist.clear();
        for (int i = 0; i < names.length; i++) {
            //每一个名字
            String name = names[i];
            HaoHan haoHan = new HaoHan(name);
            //每一个名字转成拼音的数组
            StringBuffer pinYin1 = new StringBuffer();
            for (int j = 0; j < name.length(); j++) {
                char c = name.charAt(j);
                if (Pinyin.isChinese(c)) {
                    pinYin1.append(Pinyin.toPinyin(c));
                }
            }
            haoHan.setPinyin(pinYin1.toString());
            peoplelist.add(haoHan);
        }
        //将集合进行排序
        Collections.sort(peoplelist);
        listView.setAdapter(new MyAdapter(this, peoplelist));
        letter.setListner(new LetterList.OnClicklistner() {
            @Override
            public void setOncicklistner(String s) {
                for (int i = 0; i < peoplelist.size(); i++) {
                    String s1= peoplelist.get(i).getPinyin().charAt(0) + "";
                    if (s.equals(s1)){
                       listView.setSelection(i);
                       toustUtil(s);
                       break;
                    }else if (i==peoplelist.size()-1){
                        toustUtil("对不起，没有");
                    }
                }
            }
        });

    }

    private void toustUtil(String s) {
        if (mToast == null) {
            mToast = Toast.makeText(this, s, Toast.LENGTH_SHORT);
        } else {
            mToast.setText(s);
        }
        mToast.show();
    }
}

