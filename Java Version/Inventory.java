import java.util.ArrayList;

public class Inventory
{
	private ArrayList<Object> objects;

	public Inventory()
	{
		objects = new ArrayList<Object>();
	}

	public void addObject(Object object)
	{
		objects.add(object);
	}

	public void removeObect(int index)
	{
		// remember to add validation on the index
		objects.remove(index);
	}
}