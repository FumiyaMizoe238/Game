package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.lwjgl.BufferUtils;

import graphics.CustomVertex;

public class Helper
{
	public static FloatBuffer convertToBuffer(CustomVertex[] data)
	{
		FloatBuffer buff = BufferUtils.createFloatBuffer(data.length * CustomVertex.SIZE);
		for(CustomVertex cv : data)
		{
			//positionの格納
			buff.put(cv.getPosition().x);
			buff.put(cv.getPosition().y);
			buff.put(cv.getPosition().z);

			//texCoordの格納
			buff.put(cv.getTexCoord().x);
			buff.put(cv.getTexCoord().y);

			//colorの格納
			buff.put(cv.getColor().x);
			buff.put(cv.getColor().y);
			buff.put(cv.getColor().z);
		}
		buff.flip();

		return buff;
	}

	public static IntBuffer convertToBuffer(Integer[] data)
	{
		IntBuffer buff = BufferUtils.createIntBuffer(data.length);
		for(int v : data)
		{
			buff.put(v);
		}
		buff.flip();

		return buff;
	}

	public static String convertFileToString(String fileName)
	{
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(fileName)))
		{
			StringBuilder sb = new StringBuilder();
			String line = "";
			while((line = reader.readLine()) != null)
			{
				sb.append(line).append("\n");
			}

			return sb.toString();
		}
		catch(IOException e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
