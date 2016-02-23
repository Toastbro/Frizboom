package frizboom;
import framework.math3d.*;
import framework.math3d.vec3;
import java.util.Random;
import java.util.ArrayList;

//Brad Skufca
public class RandomSpawn<E> {
    int maxGenned;
    ArrayList out = new ArrayList();
    Random rand;
    public RandomSpawn(){
        rand = new Random();
    }
    
    public ArrayList generate(String object, int number, float x, float z, int edge){
        switch(object){
            case("player"):{
                for(int i = 0; i < number; i++){
                    float newx = x + rand.nextInt(edge);
                    if (rand.nextBoolean())
                        newx = -newx;
                    float newz = z + rand.nextInt(edge);
                    if (rand.nextBoolean())
                        newz = -newz;
                    Player play = new Player(newx, 3, newz);
                    out.add(play);
                }
        }
    }
        return out;
    }
}
