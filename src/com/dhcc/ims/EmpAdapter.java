package com.dhcc.ims;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.dhcc.entity.Person;




public class EmpAdapter extends BaseAdapter {
	private Context context;
	private  List<Person> emps;
	
	public EmpAdapter(Context context,
			List<Person> listItem){
		super();
		this.context=context;
		this.emps=listItem;
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return emps.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return emps.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	public void remove(int position) {
		emps.remove(position);
		 //System.out.println(emps.size());
		this.notifyDataSetChanged();
	}
	public void add(Person p) {
		emps.add(p);
		
		 //System.out.println(emps.size());
		this.notifyDataSetChanged();
	}
	public void update(int position, Person person){
		emps.set(position, person);
		this.notifyDataSetChanged();
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder viewholder=null;
		if(convertView==null){
			viewholder=new ViewHolder();
			convertView=View.inflate(context, R.layout.person_item, null);
			//viewholder.id=(TextView) convertView.findViewById(R.id.item1);
			viewholder.name=(TextView) convertView.findViewById(R.id.item1);
			viewholder.dept=(TextView) convertView.findViewById(R.id.item2);
			viewholder.gender=(TextView) convertView.findViewById(R.id.item3);
			viewholder.phone=(TextView) convertView.findViewById(R.id.item4);
		    convertView.setTag(viewholder); 
		}else{
			viewholder=(ViewHolder) convertView.getTag();
		}
		Person p=emps.get(position);
		System.out.println(p);
		
		//viewholder.id.setText(p.getId()+"");//setText()方法中要求String类型的
		viewholder.name.setText(p.getName());
		viewholder.dept.setText(p.getDept());
		viewholder.gender.setText(p.getGender());
		viewholder.phone.setText(p.getPhone());
		return convertView;
	}
	
	class ViewHolder{
		//TextView id;
		TextView name;
		TextView dept;
		TextView gender;
		TextView phone;
	}

}
