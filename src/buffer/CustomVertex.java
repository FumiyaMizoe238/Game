package buffer;
import java.util.stream.IntStream;

import org.joml.Vector2f;
import org.joml.Vector3f;


public class CustomVertex
{
	private Vector3f position;
	private Vector2f texCoord;
	private Vector3f color;

	public static final int[] DIMENSION = new int[]{3,2,3};
	public static final int SIZE = IntStream.of(DIMENSION).sum();

	public CustomVertex(Vector3f position, Vector2f texCoord, Vector3f color)
	{
		this.position = position;
		this.texCoord = texCoord;
		this.color = color;
	}

	public CustomVertex()
	{
		this(new Vector3f(0.0f), new Vector2f(0.0f), new Vector3f(0.0f));
	}

	public Vector3f getPosition()
	{
		return this.position;
	}

	public Vector2f getTexCoord()
	{
		return this.texCoord;
	}

	public Vector3f getColor()
	{
		return this.color;
	}

	public void setPosition(Vector3f vertex)
	{
		this.position = vertex;
	}

	public void setTexCoord(Vector2f texCoord)
	{
		this.texCoord = texCoord;
	}

	public void setColor(Vector3f color)
	{
		this.color = color;
	}
}
