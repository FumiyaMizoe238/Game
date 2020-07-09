package graphics;
import static org.lwjgl.opengl.GL20.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Shader
{
	private int vertShader;
	private int fragShader;
	private int program;

	public Shader()
	{
		this.vertShader = 0;
		this.fragShader = 0;
		this.program = 0;
	}

	public boolean readShaderFile(String vertShaderFile, String fragShaderFile)
	{
		return false;
	}

	public void begin()
	{
		glUseProgram(this.program);
	}

	public void end()
	{
		glUseProgram(0);
	}

	private String fileToString(String filename)
	{
		try(BufferedReader reader = Files.newBufferedReader(Paths.get(filename)))
		{
			StringBuilder sb = new StringBuilder();
			String line = "";
			while((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}

			return sb.toString();
		}
		catch(IOException e)
		{
			e.printStackTrace();
		}

		return null;
	}

}
