package unsw.dungeon;

import java.util.ArrayList;



public class Composite implements Component { //for goal
	
	private Boolean status;
	//private String name;
	private ArrayList<Component> components;
	private String method;
	private Composite upComposite;
	
	public Composite(String method, Composite composite) {
		this.status = false;
		//this.name = name;
		this.components = new ArrayList<Component>();
		this.method = method;
		this.upComposite = composite;
	}
	
	@Override
	public Boolean getStatus() {return status;}
	
	
	public void finishGoal() {this.status = true;}
	
	public void add (Component component) {
		components.add(component);
	}
	
	@Override
	public void checkName(String name) {
		for (Component component : components) {
			component.checkName(name);
		}
	}
	
	public void getAnswer() {
		if (method.equals("AND")) {
			AND();
		} else { // OR
			OR();
		}
	
	}
	
	/*
	 * check for multiple goal condition AND
	 */
	public void AND() {
		int check = 1;
		for (Component component: components) {
			if (!component.getStatus()) {
				check = 0;
				break;
			}
		}
		if (check == 1) {
			this.status = true;
			if (upComposite != null) {
				upComposite.getAnswer();
			}
		}
	}
	
	/*
	 * check for multiple goal condition OR
	 */
	public void OR() {
		for (Component component: components) {
			if (component.getStatus()) {
				this.status = true;
				if (upComposite != null) {
					upComposite.getAnswer();
				}
				return;
			}
		}
	}
	
}
