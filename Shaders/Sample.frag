#version 460

in vec3 outColor;
in vec2 outTexCoord;

out vec4 fragColor;

void main()
{
	fragColor = vec4(outColor, 1.0);
}