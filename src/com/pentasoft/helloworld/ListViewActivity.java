package com.pentasoft.helloworld;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import net.tsz.afinal.FinalDb;
import net.tsz.afinal.annotation.view.ViewInject;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.pentasoft.config.Configs.DaoConfig;
import com.pentasoft.db.model.Article;
import com.pentasoft.db.service.ArticleServices;

public class ListViewActivity extends Activity {

	@ViewInject(id = R.id.button1, click = "button1_Click")
	Button button;
	@ViewInject(id = R.id.listView1)
	ListView list;

	public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		setContentView(R.layout.listview);
        //��XML�е�ListView����ΪItem������
		list = (ListView) findViewById(R.id.listView1);

		this.BindList();
	}

	private void BindList() {
		FinalDb db = FinalDb.create(this, DaoConfig.getDbName());
		List<Article> articleList = new ArticleServices(db).getRemoteArticle();
		setTitle(articleList.size() + "");

		for (Article article : articleList) {
			db.save(article);
		}

		// ���ɶ�̬���飬����ת������
        ArrayList<HashMap<String, String>> mylist = new ArrayList<HashMap<String, String>>();
		for (int i = 0; i < articleList.size(); i++)
		{
			Article article = articleList.get(i);
			HashMap<String, String> map = new HashMap<String, String>();
			map.put("ItemTitle", article.getTitle());
			map.put("ItemText", article.getSummary());
			map.put("ItemDate", article.getDateCreated().toLocaleString());
			mylist.add(map);
		}
		for (int i = 0; i < 3; i++)
        {
        	HashMap<String, String> map = new HashMap<String, String>();
        	map.put("ItemTitle", "This is Title.....");
        	map.put("ItemText", "This is text.....");
        	mylist.add(map);
        }
        //����������������===��ListItem
        SimpleAdapter mSchedule = new SimpleAdapter(this, //ûʲô����
        		                                    mylist,//������Դ 
        		                                    R.layout.listitem,//ListItem��XMLʵ��
        		                                    
        		                                    //��̬������ListItem��Ӧ������        
        		                                    new String[] {"ItemTitle", "ItemText"}, 
        		                                    
        		                                    //ListItem��XML�ļ����������TextView ID
        		                                    new int[] {R.id.ItemTitle,R.id.ItemText});

        //��Ӳ�����ʾ
        list.setAdapter(mSchedule);

	}

	public void button1_Click(View v) {

	}

}