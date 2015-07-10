package com.dhcc.ims;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.dhcc.entity.Person;
import com.dhcc.webservice.mobilecom;

public class Activity_one extends Activity{

	private mobilecom comm = new mobilecom();
	// ListView lvPerson;
	private ListView lvPerson = null;
	private JSONArray jsonArray=null;
	List<Person> emps=null;
	private EmpAdapter empAdapter;
	private Person addPerson;
	private Person upPerson;
	private int upPosition;
	private static final int VIEW_DETAIL = 0;// �鿴����
	private static final int VIEW_DELETE = 1;// ɾ������
	private static final int VIEW_ADD = 2;// ��Ӳ���
	private static final int VIEW_UPDATE = 3;// �޸Ĳ���
	
	private static final int ACTION_UPDATE = 4;
	private static final int ACTION_ADD = 5;
	private static final int ACTION_DETAIL= 6;


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_one);
		
		initView();
		setListener();
	}

	

	private void initView() {
		lvPerson = (ListView) findViewById(R.id.lvPerson);
		String param = "";
		

		try { // ���� ������ ���� ���� Method/query
			//comm.ThreadHttp("com.android.container.DeptAction", "deleteById",
					//param, "method", this, 0, handler);
			comm.ThreadHttp("com.android.container.DeptAction", "getAllEmploy",
					param, "method", this, 0, handler);

			// HttpGetPostCls.LinkData("web.DHCNurPdaTestTa","getWardPats",
			// param ,"Method", this);
			
		} catch (Exception e) {

			e.printStackTrace();
		}

		
	}

	Handler handler = new Handler() {

		public void handleMessage(Message paramMessage) {
			if (paramMessage.what == 0) {
				if (comm.RetData.indexOf("error") != -1) {
					Toast.makeText(getApplicationContext(), "�������!", 1000).show();
					return;
				}
				if (!comm.RetData.equals("")) {
					// LoginUser.WebUrl=comm.RetData;
					// String result=comm.RetData;
					// ArrayList<HashMap<String, Object>> listItem = new
					// ArrayList<HashMap<String, Object>>();

					// listItem= parseJson(comm.RetData);
					 emps = new ArrayList<Person>();
					 String result = comm.RetData;
					try {
						jsonArray = new JSONArray(result);

						for (int i = 0; i < jsonArray.length(); i++) {

							HashMap<String, Object> map = new HashMap<String, Object>();
							Person p=new Person();
							int id=Integer.parseInt(jsonArray.getJSONObject(i).getString("id"));
							p.setId(id);
							p.setName(jsonArray.getJSONObject(i).getString("name"));
							p.setDept(jsonArray.getJSONObject(i).getString("dept"));
							
							String gender = "";
							if (jsonArray.getJSONObject(i).getString("type")
									.equals("1")) {
								gender = "��";
							} else {
								gender = "Ů";
							}
							p.setGender(gender);
							p.setPhone(jsonArray.getJSONObject(i).getString("phone"));
							p.setTime(jsonArray.getJSONObject(i).getString("time"));
							emps.add(p);
						}
						empAdapter=new EmpAdapter(Activity_one.this,emps);
						/*SimpleAdapter adapter = new SimpleAdapter(
								MainActivity.this, listItem,
								R.layout.person_item, new String[] { "id",
										"name", "dept", "gender", "phone" },
								new int[] { R.id.item1, R.id.item2, R.id.item3,
										R.id.item4, R.id.item5 });*/
	
						lvPerson.setAdapter(empAdapter);
					} catch (JSONException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				}

			}
			if(paramMessage.what == VIEW_DELETE){//ɾ���ɹ�
				Toast.makeText(getApplicationContext(), "ɾ���ɹ�!", 1000).show();
				return;
			}
			if(paramMessage.what == VIEW_ADD){//��ӳɹ�
				if (comm.RetData.indexOf("error") != -1) {
					Toast.makeText(getApplicationContext(), "�������!", 1000).show();
					return;
     		}
				if (!comm.RetData.equals("")){
					int id=Integer.parseInt(comm.RetData);
					addPerson.setId(id);
					empAdapter.add(addPerson);
					Toast.makeText(getApplicationContext(), "��ӳɹ�!", 1000).show();
				}
			}
			if(paramMessage.what == VIEW_UPDATE){//���³ɹ�
				empAdapter.update(upPosition,upPerson);
				Toast.makeText(getApplicationContext(), "ˢ�³ɹ�!", 1000).show();
				return;
			}
		}
	};
	
	private void setListener() {
		this.lvPerson.setOnItemLongClickListener(new OnItemLongClickListener() {

		public boolean onItemLongClick(AdapterView<?> parent,View v, final int position, long id) {
				AlertDialog.Builder builder = new AlertDialog.Builder(Activity_one.this);
				builder.setTitle("��ѡ�����²���").setItems(new String[] { "�鿴����", "ɾ������", "���Ӳ���",
												"�޸Ĳ���" },new OnClickListener() {
											
				public void onClick(DialogInterface dialog,int which) {
												
					switch (which) {
						case VIEW_DETAIL:
							//showDetail(position);
							Intent intentDetail = new Intent();
							intentDetail.setClass(Activity_one.this,DetailActivity.class);
						
							intentDetail.putExtra("person",emps.get(position));
							startActivity(intentDetail);
					
						break;
						case VIEW_DELETE:
										
							deleteItem(position);
							empAdapter.remove(position);
						break;
						case VIEW_ADD:
							Intent intent1 = new Intent(Activity_one.this,AddActivity.class);
							//�������Activity
							//MainActivity.this.startActivity(intent1);
							//������Activity
							//MainActivity.this.finish();
							startActivityForResult(intent1, ACTION_ADD);
						break;
						case VIEW_UPDATE:
							upPosition = position;
							Intent intent = new Intent();
							intent.setClass(Activity_one.this,UpdateActivity.class);
						
							intent.putExtra("person",emps.get(position));
							startActivityForResult(	intent,ACTION_UPDATE);
						break;
					}
				}

			

				private void showDetail(int position) {
												// TODO Auto-generated method
												// stub
					AlertDialog.Builder builder = new AlertDialog.Builder(Activity_one.this);
						/*General general = generals.get(position);
						builder.setTitle(general.getName())
								.setMessage(general.getDetail())
								.setPositiveButton("����", null);
					AlertDialog dialog = builder.create();
								dialog.show();*/
				}
										});
					AlertDialog dialog = builder.create();
								dialog.show();
						return true;
					}
				});

	}
	private void deleteItem(int position) {
		int id=emps.get(position).getId();
		String param="&id="+id;
		comm.ThreadHttp("com.android.container.DeptAction", "deleteById",param, "method", this, VIEW_DELETE, handler);
		
		//initView();
	}
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		String param="";
		if(resultCode != RESULT_OK) {
			return;
		}
		switch (requestCode) {
		case ACTION_UPDATE:
			Person up = (Person) data.getSerializableExtra("person");
			upPerson=up;
			//generalAdapter.update(mPosition, general);
			param = "&param={'id':'"+up.getId()+"','name':'"+up.getName()+"','gender':'"+up.getGender()
					+"','dept':'"+up.getDept()+"','phone':'"+up.getPhone()+"','time':'"+up.getTime()+"'}";
			comm.ThreadHttp("com.android.container.DeptAction", "update",param, "method", this, VIEW_UPDATE, handler);
			break;
		case ACTION_ADD:
			Person p = (Person) data.getSerializableExtra("person");
			p.setTime("2013");
			addPerson=p;
		
			param = "&param={'name':'"+p.getName()+"','gender':'"+p.getGender()
						+"','dept':'"+p.getDept()+"','phone':'"+p.getPhone()+"','time':'2013'}";
			
				// TODO Auto-generated catch block
			
			comm.ThreadHttp("com.android.container.DeptAction", "insert",param, "method", this, VIEW_ADD, handler);
			
			break;
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu){
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	

}
