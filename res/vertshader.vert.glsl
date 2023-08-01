#version 460 core

layout (location = 0) in vec2  a_pos;
layout (location = 1) in vec4  a_color;
layout (location = 2) in vec2  a_uv;
layout (location = 3) in float a_tex_index;
out vec4  v_color;
out vec2  v_uv;
out float v_tex_index;
uniform mat4 u_proj;

void main() {
    gl_Position = u_proj * vec4(a_pos,0.0,1.0);
    v_color = a_color;
    v_uv = a_uv;
    v_tex_index = a_tex_index;
}