package unsw.dungeon;

public class Goal implements Component{

	private String name;
	private Boolean status;
	private Composite upComposite;
	
	public Goal(String name, Composite composite) {
		this.name = name;
		this.status = false;
		this.upComposite = composite;
	}
	
	@Override
	public Boolean getStatus() {return status;}
	
	
	public String getName() {return name;}
	
	public void finishGoal() {
		this.status = true;
		//System.out.println("I changed");
		if (upComposite != null) {
			//System.out.println("tell the one up I changed");
			upComposite.getAnswer();
		}
		//System.out.println("9999999");
	}
	
	@Override
	public void checkName(String name) {
		System.out.println("checking name " + name + " " + this.name);
		
		if (this.name.equals(name)) {
			finishGoal();
			System.out.println("I am thw goal who need to change");
		}
		System.out.println("change done");
	}
	
}
