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
        //绑定XML中的ListView，作为Item的容器
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

		// 生成动态数组，并且转载数据
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
        //生成适配器，数组===》ListItem
        SimpleAdapter mSchedule = new SimpleAdapter(this, //没什么解释
        		                                    mylist,//数据来源 
        		                                    R.layout.listitem,//ListItem的XML实现
        		                                    
        		                                    //动态数组与ListItem对应的子项        
        		                                    new String[] {"ItemTitle", "ItemText"}, 
        		                                    
        		                                    //ListItem的XML文件里面的两个TextView ID
        		                                    new int[] {R.id.ItemTitle,R.id.ItemText});

        //添加并且显示
        list.setAdapter(mSchedule);

	}

	public void button1_Click(View v) {

	}

}