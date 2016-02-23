
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
import java.util.ArrayList;
/**
 *
 * @author Ryan Juda
 */
public class Game {

        Set<Integer> keys = new TreeSet<>();
        public Camera cam;
        public Program prog;
        float prev;
        Mesh column;
        UnitSquare usq;
        Framebuffer fbo1;
        Framebuffer fbo2;
        Texture2D dummytex = new SolidTexture(GL_UNSIGNED_BYTE,0,0,0,0);
        SDL_Event ev=new SDL_Event();
        //Player player;
        float elapsed;
        RandomSpawn gen = new RandomSpawn();
        Frisbee frisbee;
        ArrayList PList;
        String state;


       
    public Game(){
        PList = gen.generate("player", 1, 0.0f, 0.0f, 10);
        column = new Mesh("assets/ground256.obj.mesh");
        //usq = new UnitSquare();

        fbo1 = new Framebuffer(512,512);
        fbo2 = new Framebuffer(512,512);

        prog = new Program("vs.txt","fs.txt");
        prog.use();
        //Player player = new Player(0, 0, 0);
        
        cam = new Camera();
        cam.lookAt( ((Player)PList.get(0)).pos, new vec3(0,3,0), new vec3(0,1,0) );
        
        //player = new Player();
        prev = (float)(System.nanoTime()*1E-9);
        state = "Ohio";
        
        SDL_ShowCursor(0);
        SDL_SetRelativeMouseMode(1);
        
        
        
        
        
    }
    
    
    public void run(){
            
            while(true){
                int rv = SDL_PollEvent(ev);
                if( rv == 0 )
                    break;
                //System.out.println("Event "+ev.type);
                if( ev.type == SDL_QUIT )
                    System.exit(0);
                if( ev.type == SDL_MOUSEMOTION ){
                    cam.pitch(9f*elapsed);
                    System.out.println("Mouse motion "+ev.motion.x+" "+ev.motion.y+" "+ev.motion.xrel+" "+ev.motion.yrel);
                    
                }
                
                if( ev.type == SDL_KEYDOWN ){
                    //System.out.println("Key press "+ev.key.keysym.sym+" "+ev.key.keysym.sym);
                    keys.add(ev.key.keysym.sym);
                }
                if( ev.type == SDL_KEYUP ){
                    keys.remove(ev.key.keysym.sym);
                
                }
            }

            
            float now = (float)(System.nanoTime()*1E-9);
            float elapsed = now-prev;

            prev=now;
            Player p;
            for(int i = 0; i < PList.size(); i++){
                p = ((Player)PList.get(i));
                if (frisbee != null){
                if (frisbee.pos.y <= .3){
                    p.frisbee = null;
                }
                }
                p.control(keys, cam, elapsed);
                if (p.fris){
                    
                    frisbee = p.frisbee;
                    p.fris = false;
                    
                    
                }
            }
            
            //player.control(keys, cam, elapsed);
            if( keys.contains(SDLK_ESCAPE))
                System.exit(0);

            //the fbo stuff is for later...
            //fbo1.bind();
            
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

            prog.setUniform("lightPos",new vec4(50,50,50, 1) );
            cam.draw(prog);
            prog.setUniform("worldMatrix",mat4.identity());
            column.draw(prog);
            if (frisbee != null){
                
                frisbee.spawn(prog);
                frisbee.update();
                
            }
            
            //fbo1.unbind();

            //this is also for later...
/*
            glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
            blurprog.use();
            blurprog.setUniform("diffuse_texture",fbo1.texture);
            usq.draw(blurprog);
            blurprog.setUniform("diffuse_texture",dummytex);
*/

            //SDL_GL_SwapWindow(win);


        }//endwhile
    
    
    }

