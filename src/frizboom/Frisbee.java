
package frizboom;

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
public class Frisbee {
    vec3 pos;
    vec3 vel;
    float speed = .5f;
    float angle;
    Mesh frisbee;
    String texture = "assets/Frisbee.obj.mesh";
    physics phys = new physics();
    boolean dead = false;
    float grav;
    //Program prog;
    
    public Frisbee(vec3 position, vec3 veloc){
        vel = veloc.mul(speed);
        vel = phys.gravity(vel, .12f);
        pos = position;
        frisbee = new Mesh(texture);
        if (pos.y < 5){
            dead = true;
        } 
    
    }
    public void update(){
       if (pos.y > 0.3)
       {
            grav -= .00004f;
            pos = phys.move(pos, vel);
            vel = phys.gravity(vel, grav);
       }
    }

    
    
    public void spawn(Program prog){
        prog.setUniform("worldMatrix",translation(pos));
        frisbee.draw(prog);
    
    }
    
    
}
