
package frizboom;
import framework.Mesh;
import framework.math3d.vec3;
import framework.math3d.mat4;
import java.util.Set;
import java.util.TreeSet;
import static JGL.JGL.*;
import static JSDL.JSDL.*;
import static framework.math3d.math3d.mul;
import static framework.math3d.math3d.sub;
import static framework.math3d.math3d.translation;
import framework.math3d.vec2;
import framework.math3d.vec4;
import framework.*;
/**
 *
 * @author Ryan Juda 
 */
public class Player{
    float angle;
    vec3 pos;
    Frisbee frisbee;
    Boolean fris = false;
    vec3 facing;
    

 
    public Player(float posx, float posy, float posz){
        pos = new vec3(posx, posy, posz);
        
    }
    
    public void toss(){  
        if (frisbee == null){
            frisbee = new Frisbee(pos, facing);
            fris = true;
        }
    }
    
    public void control(Set<Integer> keys, Camera cam, float elapsed){
        //char out;
        
        facing = ((vec4)cam.getW()).xyz();
        if( keys.contains(SDLK_w ))
            cam.walk(9f*elapsed);
        if( keys.contains(SDLK_s))
            cam.walk(-9f*elapsed);
        if( keys.contains(SDLK_a))
            cam.turn(2.4f*elapsed);
        if( keys.contains(SDLK_d))
            cam.turn(-2.4f*elapsed);
        if( keys.contains(SDLK_r))
            cam.tilt(2.4f*elapsed);
        if( keys.contains(SDLK_t))
            cam.tilt(-2.4f*elapsed);
        if( keys.contains(SDLK_e))
            this.toss();
        /*if( keys.contains(SDLK_f ))
            cam.jump(-3f*elapsed);
        if( keys.contains(SDLK_g ))
            cam.jump(3f*elapsed);*/
        pos = cam.getEye().xyz();
        }
    
    public void draw(Program prog){
        
    }
    
    }
    