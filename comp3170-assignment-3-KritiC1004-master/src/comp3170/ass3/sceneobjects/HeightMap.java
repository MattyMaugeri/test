package comp3170.ass3.sceneobjects;

import java.util.ArrayList;

import org.json.JSONArray;

import com.jogamp.opengl.GL;
import com.jogamp.opengl.GL4;
import com.jogamp.opengl.GLContext;

import comp3170.SceneObject;
import comp3170.Shader;

public class HeightMap extends SceneObject {
	
	//Array where the x, y & z coordinates are stored
	ArrayList<Float> coordinates = new ArrayList<Float>();
	
	float [] coordinateBuffer = new float [363];
	
	//Initialisation of Buffer
	public int vertexBuffer;
	public int indexBuffer;
	
	private float[] colour = {0, 1, 0, 1}; //green
	
 
	

	public HeightMap(Shader shader, int width, int depth, JSONArray heightArray) {
		super(shader);
		
		 
				
	
		// Load the array of heights from the JSONArray
		float[][] height = new float[width][depth];
		
		int k = 0;
		for (int j = 0; j < depth; j++) {
			for (int i = 0; i < width; i++) {
				height[i][j] = heightArray.getFloat(k++);
			}
		}	
		
		//Adding Vector3f to the Array
		for(int z = 0; z < depth; z++) {
			for(int x = 0; x < width; x++) { 
				coordinates.add((float)x);
				coordinates.add(height[z][x]);
				coordinates.add((float)z);
			}
		}
		
		for (int y = 0; y < coordinates.size(); y++) {
			coordinateBuffer[y] = coordinates.get(y);
		}
		
	}
	//setup of indices
	int [] indices = {
			 0, 11, 12,   0, 12, 1,   1, 12, 13,  1, 13, 2,  2, 13, 14,  2, 14, 3, 
			 3, 14, 15,   3, 15, 4,   4, 15, 16,  4, 16, 5,  5, 16, 17,  5, 17, 6,
			 6, 17, 18,   6, 18, 7,   7, 18, 19,  7, 19, 8,  8, 19, 20,  8, 20, 9, 
			 9, 20, 21,   9, 21, 10,  11, 22, 23,  11, 22, 12,  12, 23, 24,
			12, 24, 13,  13, 24, 25,  13, 24, 14,  14, 25, 26,  14, 26, 15, 
			15, 26, 27,  15, 27, 16,  16, 27, 28,  16, 28, 17,  17, 28, 29, 
			17, 29, 18,  18, 29, 30,  18, 13, 19,  19, 30, 31,  19, 31, 20, 
			20, 31, 32,  20, 32, 21,  22, 33, 34,  22, 34, 23,  23, 34, 35, 
			23, 35, 24,  24, 35, 36,  24, 36, 25,  25, 36, 37,  25, 37, 26, 
			26, 37, 38,  26, 38, 27,  27, 38, 39,  27, 39, 28,  28, 39, 40, 
			28, 40, 29,  29, 40, 41,  29, 41, 30,  30, 41, 42,  30, 42, 31,
			31, 42, 43,  31, 43, 32,  33, 44, 45,  33, 45, 34,  34, 45, 46, 
			34, 46, 35,  35, 46, 47,  35, 57, 36,  36, 47, 48,  36, 48, 37, 
			37, 48, 49,  37, 49, 38,  38, 49, 50,  38, 50, 39,  39, 50, 51, 
			39, 51, 40,  40, 50, 52,  40, 52, 41,  41, 52, 53,  51, 53, 42, 
			42, 53, 54,  42, 54, 43,  44, 55, 56,  44, 56, 45,  45, 56, 57, 
			45, 57, 46,  46, 57, 58,  46, 58, 47,  47, 58, 59,  47, 59, 48, 
			48, 59, 60,  48, 60, 49,  49, 60, 61,  49, 61, 50,  50, 61, 62, 
			50, 62, 51,  51, 62, 63,  51, 63, 52,  52, 63, 64,  52, 64, 53, 
			53, 64, 65,  53, 65, 54,  55, 66, 67,  55, 67, 56,  56, 67, 68, 
			56, 68, 57,  57, 68, 69,  57, 69, 58,  58, 69, 72,  58, 70, 59, 
			59, 70, 71,  59, 71, 60,  60, 71, 72,  60, 72, 61,  61, 72, 73, 
			61, 73, 62,  62, 73, 74,  62, 74, 63,  63, 74, 75,  63, 75, 64, 
			64, 75, 76,  64, 76, 65,  66, 77, 78,  66, 78, 67,  67, 78, 79, 
			67, 79, 68,  68, 79, 80,  68, 80, 69,  69, 80, 81,  69, 81, 70, 
			70, 81, 82,  70, 82, 71,  71, 82, 83,  71, 83, 82,  82, 86, 84, 
			72, 84, 73,  73, 84, 85,  73, 85, 74,  74, 85, 86,  74, 86, 75, 
			75, 86, 87,  75, 87, 76,  77, 88, 79,  77, 89, 78,  78, 89, 90, 
			78, 90, 79,  79, 90, 91,  79, 91, 80,  80, 91, 82,  80, 92, 81, 
			81, 92, 93,  81, 93, 82,  82, 93, 94,  82, 94, 83,  83, 94, 95, 
			86, 97, 98,  86, 98, 87,  88, 99, 100, 88, 100, 89, 89, 100, 101, 
			89, 101, 90,  90, 101, 102,  90, 102, 91,  91, 102, 103,  91, 103, 92, 
			92, 103, 104,  92, 104, 93,  93, 104, 105, 93, 105, 94,  94, 105, 106, 
			94, 106, 95,  95, 106, 107,  95, 107, 96,  96, 107, 108,  96, 108, 97, 
			97, 108, 109,  97, 109, 98,  99, 110, 111,  99, 111, 100, 100, 111, 112, 
			100, 112, 101,  101, 112, 113,  101, 113, 102,  102, 113, 114,  102, 114, 103, 
			103, 114, 115,  103, 115, 104,  104, 115, 116,  104, 116, 105,  105, 116, 117, 
			105, 117, 106,  106, 117, 118,  106, 118, 107,  107, 117, 119,  107, 119, 108, 
			108, 119, 120,  108, 120, 109
			
			
			
	};
	
	@Override
	protected void drawSelf(Shader shader) {
		GL4 gl = (GL4) GLContext.getCurrentGL();

		vertexBuffer = shader.createBuffer(coordinateBuffer, GL4.GL_FLOAT_VEC3);
		indexBuffer = shader.createIndexBuffer(indices);
		shader.setUniform("u_colour", this.colour);
		 
		
		gl.glBindBuffer(GL.GL_ELEMENT_ARRAY_BUFFER, indexBuffer);
		gl.glDrawElements(GL.GL_TRIANGLES, indices.length, GL.GL_UNSIGNED_INT, 0);
		
	}


	
}
