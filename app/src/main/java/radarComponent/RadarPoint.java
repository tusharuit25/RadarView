package radarComponent;

/**
 * Created by Tushar on 08/03/17.
 */


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by Karam Ahkouk on 04/06/15.
 */
public class RadarPoint extends View{
    float x;
    float y;
    int radius;
    double angle;
    String identifier;


    public RadarPoint(Context context,String identifier, float x, float y, int radius,double angle) {
        super(context);
        this.identifier = identifier;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.angle = angle;
    }
    public RadarPoint(Context context,String identifier, float x, float y,double angle) {
        super(context);
        this.identifier = identifier;
        this.radius = radius;
        this.x = x;
        this.y = y;
        this.angle =angle;
    }


    

}
