package game;


/**
 * ContainerItem.java
 * 
 * An Item that is associated with a Containers. It can contain other items.
 *
 * @author ASher Bernardi
 * Wheaton College, CS 245, Spring 2017
 * Project 4
 */
public class ContainerItem extends Item{
	Container container;
	
	public ContainerItem(Container container) {
		super(container.name(), false);
		this.container = container;
		super.addCommand("open " + container.name());
	}
	
	public Container getContainer() { return container; }

}
