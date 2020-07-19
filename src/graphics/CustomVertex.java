package graphics;
import java.util.EnumMap;

import org.joml.Vector2f;
import org.joml.Vector3f;


public class CustomVertex
{
	private Vector3f position = new Vector3f(0.0f);
	private Vector2f texCoord = new Vector2f(0.0f);
	private Vector3f color = new Vector3f(0.0f);

	public enum VertexAttrib
	{
		POSITION,
		TEXCOORD,
		COLOR
	}
	public static EnumMap<VertexAttrib, Integer> ATTRIB_MAP = new EnumMap<VertexAttrib, Integer>(VertexAttrib.class)
	{
		{
			put(VertexAttrib.POSITION, 0);
			put(VertexAttrib.TEXCOORD, 1);
			put(VertexAttrib.COLOR, 2);
		}
	};
	public static EnumMap<VertexAttrib, Integer> DIMENSION_MAP = new EnumMap<VertexAttrib, Integer>(VertexAttrib.class)
	{
		{
			put(VertexAttrib.POSITION, 3);
			put(VertexAttrib.TEXCOORD, 2);
			put(VertexAttrib.COLOR, 3);
		}
	};

	public static final int FLOAT_SIZE = 4;
	public static final int SIZE = DIMENSION_MAP.values().stream().mapToInt(v->v).sum();
	public static final int STRIDE = SIZE * FLOAT_SIZE;

	public CustomVertex(Vector3f position, Vector2f texCoord, Vector3f color)
	{
		this.setCustomVertex(position, texCoord, color);
	}

	public CustomVertex()
	{

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

	public void setCustomVertex(Vector3f position, Vector2f texCoord, Vector3f color)
	{
		this.position = position;
		this.texCoord = texCoord;
		this.color = color;
	}

}
