package net.impjq.tabview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class TabMore extends Activity{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        
        TextView textView=new TextView(this);
        textView.setTextColor(R.color.white);
        textView.setText(R.string.tab_more);
        
        setContentView(R.layout.main);
    }
}
