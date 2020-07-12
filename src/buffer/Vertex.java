package buffer;
import org.joml.Vector3f;
import org.joml.Vector2f;


public class Vertex
{
	private Vector3f vertex;
	private Vector2f texcoord;
	private Vector3f color;

	public Vertex(Vector3f vertex, Vector3f color)
	{
		this.vertex = vertex;
		this.color = color;
	}

	public Vertex()
	{
		this(new Vector3f(0.0f), new Vector3f(0.0f));
	}


}
