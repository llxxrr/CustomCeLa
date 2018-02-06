package customcela.com.example.lxr.customcela;

import android.support.annotation.NonNull;

/**
 * Created by lxr on 2018/2/5.
 */

public class HaoHan implements Comparable<HaoHan>{
    private String pinyin;
    private String name;

    public HaoHan(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public int compareTo(@NonNull HaoHan haoHan) {
        return this.pinyin.compareTo(haoHan.pinyin);
    }
}
