package gsi.disasters;

/**
 * Class that represents a Resource
 * @author julio camarero
 * @version 1.0
 */
public class Resource {

	/**
	 * Resource id
	 **/
	private int id;

	/**
	 * assigned Resource id
	 **/
	private int idAssigned;
	
	/**
	 * type of resource (firemen, policemen, ambulance)
	 **/
	private String type;
	
	/**
	 * Name of the resource
	 **/
	private String name;
	
		
	/**
	 * Address (to represent resources in a map) 
	 */
	private String address;
	/**
	 * Longitude
	 */
	private double longitud;
	/**
	 * Latitud
	 */
	private double latitud;
	
	
     
    /**
	 * Info about the resource
	 */
     private String info;
    /**
	 * Description for the resource
	 */
     private String description;
	/**
	 * state of the resource (usually active)
	 */
	
     private String state;
	
	/**
	 * Size of the Disaster
	 */
	
	
	/**
	 * id of the user who added the resource
	 */
	private int user;

	/**
	 * @param id
	 * @param idAssigned
	 * @param type
	 * @param name
	 * @param address
	 * @param longitud
	 * @param latitud
	 * @param info
	 * @param description
	 */
	public Resource(int id, String type, String name,String info,String description,
			  int idAssigned ) {
		
		this.id = id;
		this.idAssigned = idAssigned;
		this.type = type;
		this.name = name;
		
		this.info = info;
		this.description = description;
		this.state = "active";
		this.user = 1;
               // System.out.println("## New Resource: "+this.type+" - "+this.name+" (id:"+this.id+") assigned:"+this.idAssigned+"##");
	}

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @return the idAssigned
	 */
	public int getIdAssigned() {
		return idAssigned;
	}

	/**
	 * @param idAssigned the idAssigned to set
	 */
	public void setIdAssigned(int idAssigned) {
		this.idAssigned = idAssigned;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}

	
	/**
	 * @return the info
	 */
	public String getInfo() {
		return info;
	}

	/**
	 * @param info the info to set
	 */
	public void setInfo(String info) {
		this.info = info;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}

	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * @return the user
	 */
	public int getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(int user) {
		this.user = user;
	}
	
	
	
	
	
	
	}