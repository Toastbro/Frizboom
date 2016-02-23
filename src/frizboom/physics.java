/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package frizboom;
import framework.math3d.*;
/**
 *
 * @author Kelby Schick
 */
public class physics
{
    public vec3 move(vec3 pos, vec3 vel)
    {
        vec3 temp = pos.sub(vel);
        return temp;
    }
    
    public vec3 gravity(vec3 vel, float gravity)
    {
        vec3 temp = vel;
        temp.y = temp.y - gravity;
        return temp;
    }
}
