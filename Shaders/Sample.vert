#version 460

layout (location = 0) in vec3 position;
layout (location = 1) in vec2 texcoord;
layout (location = 2) in vec3 color;

out vec3 outColor;
out vec2 outTexCoord;

void main()
{
	outColor = color;
	outTexCoord = texcoord;
	gl_Position = vec4(position, 1.0);
}